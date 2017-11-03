package com.example.whath.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

        ImageButton DsButton1;
        DsButton1 = (ImageButton) findViewById(R.id.imageButton1);

        DsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Dom was chosed.");
            }
        });

        ImageButton DsButton2 = (ImageButton) findViewById(R.id.imageButton2);

        DsButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Sub was chosed.");
            }
        });
    }
}
