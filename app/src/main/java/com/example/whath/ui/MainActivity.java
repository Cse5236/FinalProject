package com.example.whath.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whath.ui.accelerometer.bump;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {
    private static EditText username, password;
    //public static final String MY_TAG = ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Log.i("message","onCreate");
        login();
        initializeControls();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("message","FacebookLogin_onSuccess");
                txtStatus.setText("FacebookLoginSuccess\n"+loginResult.getAccessToken());
                Toast.makeText(MainActivity.this, "FacebookLogin_onSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Log.i("message","FacebookLogin_onCancel");
                txtStatus.setText("FacebookLoginCancel");
                Toast.makeText(MainActivity.this, "FacebookLogin_onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("message","FacebookLogin_onError");
                txtStatus.setText("FacebookLoginError: "+error.getMessage());
                Toast.makeText(MainActivity.this, "FacebookLogin_onError", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i("message","onStart");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("message","onPause");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("message","onResume");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("message","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("message","onDestory");
    }

    public void login() {
        username = (EditText) findViewById(R.id.Text_User);
        password = (EditText) findViewById(R.id.Text_psw);
        Button buttonlogin = (Button) findViewById(R.id.Button_Login);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals("a") && username.getText().toString().equals("a")){
                    Toast.makeText(MainActivity.this, "you have logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Domsub.class);
                    //intent.setClass(MainActivity.this,Domsub.class);
                    startActivity(intent);
            }else{
                    Toast.makeText(MainActivity.this, "Incorrent username and password", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(MainActivity.this,bump.class);
                    startActivity(intent2);
                }
        }

    });
        Button buttonfb= (Button) findViewById(R.id.button_lucky);
        buttonfb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "I'm not", Toast.LENGTH_SHORT).show();
            }
        }

        );


    }

    TextView txtStatus;
    LoginButton LoginFbButton;
    CallbackManager callbackManager;

    public void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        LoginFbButton = (LoginButton) findViewById(R.id.login_fb_button);

    }


}

