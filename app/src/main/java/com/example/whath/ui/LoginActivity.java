package com.example.whath.ui;

/**
 * Created by Star on 2017/11/5.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whath.ui.accelerometer.bump;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    private String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(
                    @NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null) {
                    Log.d("onAuthStateChanged", "login:"+
                            user.getUid());
                    userUID =  user.getUid();
                }else{
                    Log.d("onAuthStateChanged", "logout");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    public void login(View v){
        String email = ((EditText)findViewById(R.id.email))
                .getText().toString();
        String password = ((EditText)findViewById(R.id.password))
                .getText().toString();
        Log.d("authentication", email+"/"+password);
        auth.signInWithEmailAndPassword(email, password);

        Button buttonlogin = (Button) findViewById(R.id.button_flogin);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Already login", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent3);
            }

        });
    }
}