package com.example.whath.ui.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.whath.ui.Domsub;
import com.example.whath.ui.R;
import com.example.whath.ui.accelerometer.bump;

/**
 * Created by whath on 2017/11/8.
 */

public class LocDis extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.locdis);

        Button mButton1;
        mButton1 = (Button) findViewById(R.id.button_Remote);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(LocDis.this,Domsub.class);
                startActivity(intent2);
            }
        });

        Button mButton2 = (Button) findViewById(R.id.button_Local);

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Sub was chosed.");
                Intent intent3 = new Intent(LocDis.this,bump.class);
                startActivity(intent3);
            }
        });
    }

}
