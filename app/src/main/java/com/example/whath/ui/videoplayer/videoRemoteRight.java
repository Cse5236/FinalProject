package com.example.whath.ui.videoplayer;

/**
 * Created by whath on 2017/11/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.whath.ui.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class videoRemoteRight extends Activity
{
    private VideoView vView;
    private String vSource;
    private int current=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {


        final AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        //sets the Bundle
        super.onCreate(savedInstanceState);
        //sets the context
        setContentView(R.layout.main);

        //get the VideoView from the layout file
        vView = (VideoView)findViewById(R.id.vview);

        FrameLayout.LayoutParams lp1=new FrameLayout.LayoutParams(2000,FrameLayout.LayoutParams.MATCH_PARENT, Gravity.RIGHT);
        //FrameLayout.LayoutParams lp2=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        //View video_play_layout_head;
        // View video_play_layout_
        //video_play_layout_head.setVisibility(View.GONE);
        //video_play_layout_playvideo.setVisibility(View.VISIBLE);
        //video_play_layout_page.setVisibility(View.GONE);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        vView.setLayoutParams(lp1);
        vView.setVisibility(View.VISIBLE);
        //videoView.setLayoutParams(lp2);


        // if a view already exists then immediately terminate the new one



        videoRemoteRight.this.getWindow().getDecorView();

        FrameLayout decor = (FrameLayout)getWindow().getDecorView();


        //use this to get touch events
        vView.requestFocus();

        //loads video from remote server
        //set the video path
        vSource ="http://www.html5videoplayer.net/videos/toystory.mp4";
        //set the video URI, passing the vSource as a URI
        vView.setVideoURI(Uri.parse(vSource));

        //enable this if you want to enable video controllers, such as pause and forward
        vView.setMediaController(new MediaController(this));

        //plays the movie
        vView.start();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("duration");
        final DatabaseReference myVolumn = database.getReference("volumn");

        myRef.setValue(5000);
        //
        //vView.seekTo(5000);//进度调整
        //
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                int duration_down = (int) dataSnapshot.getValue(int.class);
                Log.d(TAG, "Duration is: " + duration_down);
                // 加载Web地址
                vView.seekTo(duration_down);//进度调整
//                new Handler().postDelayed(new Runnable(){
//                    public void run() {
//                        //execute the task
//                        int volumn_down = (int) dataSnapshot.getValue(int.class);
//                        Log.d(TAG, "Volumn is: " + volumn_down);
//                        //mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM,volumn_down,1); //中间的参数是音量大小
//                        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volumn_down,1); //中间的参数是音量大小
//                    }
//                }, 5000);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read duration.", error.toException());
            }
        });

//        //myVolumn.setValue(2);
        myVolumn.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int volumn_down = (int) dataSnapshot.getValue(int.class);
                Log.d(TAG, "Volumn is: " + volumn_down);
                //mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM,volumn_down,1); //中间的参数是音量大小
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volumn_down,1); //中间的参数是音量大小
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read volumn.", error.toException());
            }
        });

        vView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//视频准备好就起飞
            public void onPrepared(MediaPlayer mp) {
                //int duration = mp.getDuration();
                int duration = vView.getCurrentPosition();//获取现在的进度位置
                //myRef.setValue(duration);
                //Toast.makeText(videoRemote.this, String.valueOf(duration), Toast.LENGTH_SHORT).show();
            }
        });

        Button mButtonSync;
        mButtonSync = (Button) findViewById(R.id.button_sync);
        mButtonSync.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                vView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//视频准备好就起飞
//                    public void onPrepared(MediaPlayer mp) {
//                        int duration = vView.getCurrentPosition();//获取现在的进度位置
//                        myRef.setValue(duration);
//                        Toast.makeText(videoRemote.this, String.valueOf(duration), Toast.LENGTH_SHORT).show();
//                    }
//                });
                int durationint = vView.getCurrentPosition();//获取现在的进度位置
                //int currentVolumn = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM );//取得系统音量 maybe可以试试STREAM_SYSTEM
                int currentVolumn = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//取得系统音量 maybe可以试试STREAM_SYSTEM
                Log.d(TAG, "durationint = "+durationint);
                myRef.setValue(durationint);
                myVolumn.setValue(currentVolumn);
                //Toast.makeText(videoRemote.this, String.valueOf(durationint), Toast.LENGTH_SHORT).show();
            }
        });


        //mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM,1,1); //中间的参数是音量大小

        /*vView.seekTo(50*1000);//进度调整
        Toast.makeText(videoRemote.this, "Start", Toast.LENGTH_SHORT).show();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        current = vView.getCurrentPosition();
        int current2=0;
        current2=vView.getDuration();
       // Toast.makeText(videoRemote.this, String.valueOf(current), Toast.LENGTH_SHORT).show();
       */
    }
}