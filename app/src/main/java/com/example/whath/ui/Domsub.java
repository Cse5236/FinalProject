package com.example.whath.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.whath.ui.accelerometer.bump;
import com.example.whath.ui.videoplayer.WebVideoActivity2;

/**
 * Created by Star on 2017/10/5.
 */

public class Domsub extends AppCompatActivity {
    //private Button DsButton1;
    //private Button DsButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.domsub);

        Button DsButton1;
        DsButton1 = (Button) findViewById(R.id.imageButton1);

        DsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Dom was chosed.");
                Intent intent2 = new Intent(Domsub.this, WebVideoActivity2.class);
                startActivity(intent2);
            }
        });

        Button DsButton2 = (Button) findViewById(R.id.imageButton2);

        DsButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Sub was chosed.");
                Intent intent3 = new Intent(Domsub.this,WebVideoActivity2.class);
                startActivity(intent3);
            }
        });
    }
}
