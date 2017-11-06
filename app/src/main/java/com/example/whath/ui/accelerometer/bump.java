package com.example.whath.ui.accelerometer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.whath.ui.Domsub;
import com.example.whath.ui.R;
import com.example.whath.ui.model.Acceleration;
import com.example.whath.ui.videoplayer.WebVideoActivity;
import com.example.whath.ui.videoplayer.video_browser;

import java.util.Date;

//import duchess.fr.basicaccelerometer.R;
/**
 * Created by whath on 2017/10/21.
 */
public class bump extends AppCompatActivity implements SensorEventListener{
    //private CassandraRestApi cassandraRestApi;

    private SensorManager sm;
    public static float Gravity= (float) 9.7;
    private long lastUpdateTime;
    private static final int UPTATE_INTERVAL_TIME = 50;
    private float lastX;
    private float lastY;
    private float lastZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bump);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        startSensor();
        //initRestApi();
        //initActionButtons();
    }

    @Override
    protected void onResume()
    {
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
        startSensor();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopSensor();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopSensor();
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Acceleration capturedAcceleration = getAccelerationFromSensor(event);
        long currentUpdateTime = System.currentTimeMillis();
        // 两次检测的时间间隔
        long timeInterval = currentUpdateTime - lastUpdateTime;
        // 判断是否达到了检测时间间隔
        if (timeInterval < UPTATE_INTERVAL_TIME)
            return;
        // 现在的时间变成last时间
        lastUpdateTime = currentUpdateTime;

        // 获得x,y,z坐标
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // 获得x,y,z的变化值
        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;

        // 将现在的坐标变成last坐标
        lastX = x;
        lastY = y;
        lastZ = z;
        //sqrt 返回最近的双近似的平方根
        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
                * deltaZ)/ timeInterval * 10000;
        //Log.v("thelog", "===========log===================");
        TextView tvX= (TextView)findViewById(R.id.x);
        tvX.setText(Double.toString(x*x+y*y+z*z));
//        if ( Math.sqrt(x*x+y*y+z*z)>=12 )
//        {
            Intent intent = new Intent(bump.this,WebVideoActivity.class);
            startActivity(intent);
        //}
        //tvX.setText(Double.toString(speed));
        //updateTextView(capturedAcceleration);



        //Acceleration capturedAcceleration = getAccelerationFromSensor(event);
        //updateTextView(capturedAcceleration);
        //sendDataToCassandra(capturedAcceleration);
    }

    private void updateTextView(Acceleration capturedAcceleration) {
        /*TextView acceleration = (TextView) findViewById(R.id.acceleration);
        acceleration.setText("X:" + capturedAcceleration.getX() +
                "\nY:" + capturedAcceleration.getY() +
                "\nZ:" + capturedAcceleration.getZ() +
                "\nTimestamp:" + capturedAcceleration.getTimestamp());*/
        TextView tvX= (TextView)findViewById(R.id.x);
        TextView tvY= (TextView)findViewById(R.id.y);
        TextView tvZ= (TextView)findViewById(R.id.z);

        float x= (float) capturedAcceleration.getX();
        float y= (float) capturedAcceleration.getY();
        float z= (float) capturedAcceleration.getZ();


        tvX.setText(Float.toString(x));
        tvY.setText(Float.toString(y));
        tvZ.setText(Float.toString(z));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do nothing
    }

    /**
     * Init REST api to post data.
     */
    private void initRestApi() {
        //SharedPreferences sharedpreferences = getSharedPreferences(ConfigurationActivity.MY_CONFIG, Context.MODE_PRIVATE);
        //String restURL = sharedpreferences.getString(ConfigurationActivity.URL, ConfigurationActivity.DEFAULT_URL);
        //cassandraRestApi = CassandraRestApiClient.getClient(restURL).create(CassandraRestApi.class);
    }

    /**
     * Init start and stop buttons actions.
     */
    /*
    private void initActionButtons() {
        Button myStartButton = (Button) findViewById(R.id.button_start);
        Button myStopButton = (Button) findViewById(R.id.button_stop);

        myStartButton.setVisibility(View.VISIBLE);
        myStopButton.setVisibility(View.GONE);

        //Start button action on click
        myStartButton.setOnClickListener(v -> {
            startSensor();
            myStartButton.setVisibility(View.GONE);
            myStopButton.setVisibility(View.VISIBLE);
        });

        //Stop button action on click
        myStopButton.setOnClickListener(v -> {
            stopSensor();
            myStartButton.setVisibility(View.VISIBLE);
            myStopButton.setVisibility(View.GONE);
        });
    }
    */

    private void startSensor() {
        Sensor accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stopSensor() {
        sm.unregisterListener(this);
    }

    /**
     * Update acceleration text view with new values.
     *
     * @param capturedAcceleration
     */
    /*
    private void updateTextView(Acceleration capturedAcceleration) {
        TextView acceleration = (TextView) findViewById(R.id.acceleration);
        acceleration.setText("X:" + capturedAcceleration.getX() +
                "\nY:" + capturedAcceleration.getY() +
                "\nZ:" + capturedAcceleration.getZ() +
                "\nTimestamp:" + capturedAcceleration.getTimestamp());
    }
    */

    /**
     * Get accelerometer sensor values and map it into an acceleration model.
     *
     * @param event
     * @return an acceleration model.
     */
    private Acceleration getAccelerationFromSensor(SensorEvent event) {
        long timestamp = (new Date()).getTime() + (event.timestamp - System.nanoTime()) / 1000000L;
        return new Acceleration(event.values[0], event.values[1], event.values[2], timestamp);
    }


    /**
     * Asyncronous task to post request to a Rest API.
     */
   /* private void sendDataToCassandra(Acceleration capturedAcceleration) {


        Call<Void> call = cassandraRestApi.sendAccelerationValues(capturedAcceleration);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), getText(R.string.rest_error), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), getText(R.string.rest_failure) + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    */



}
