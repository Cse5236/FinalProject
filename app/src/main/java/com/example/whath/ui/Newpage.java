package com.example.whath.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.whath.ui.accelerometer.bump;
import com.example.whath.ui.accelerometer.bumpLoc;

import static android.content.ContentValues.TAG;

/**
 * Created by Star on 2017/10/5.
 */

public class Newpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpage);

        Button Button1;
        Button1 = (Button) findViewById(R.id.button_locvideo);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Newpage.this,bumpLoc.class);
                //Log.d(TAG,"hahaha");
                startActivity(intent2);
            }
        });

        Button Button2 = (Button) findViewById(R.id.button_webvideo);

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Newpage.this,bump.class);
                startActivity(intent3);
            }
        });
    }
}
