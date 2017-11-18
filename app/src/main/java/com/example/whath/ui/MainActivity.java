package com.example.whath.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whath.ui.accelerometer.bump;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private static EditText username, password;

    TextView txtStatus;
    LoginButton LoginFbButton;
    CallbackManager callbackManager;

    private FirebaseAuth mAuth;

    //public static final String MY_TAG = ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        Log.i("message","onCreate");
        login();

        mAuth = FirebaseAuth.getInstance();
        initializeControls();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("message","FacebookLogin_onSuccess");
                txtStatus.setText("FacebookLoginSuccess\n"+loginResult.getAccessToken());
                Toast.makeText(MainActivity.this, "FacebookLogin_onSuccess", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
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
        //FirebaseAuth.getInstance().signOut(); //logout the facebook on firebase
    }

    public void login() {
        //username = (EditText) findViewById(R.id.Text_User);
        //password = (EditText) findViewById(R.id.Text_psw);
        Button mButton1;
        mButton1 = (Button) findViewById(R.id.button_Loc);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(MainActivity.this,Newpage.class);
                startActivity(intent2);
            }
        });

        Button mButton2 = (Button) findViewById(R.id.button_Rem);

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Sub was chosed.");
                Intent intent3 = new Intent(MainActivity.this,Domsub.class);
                startActivity(intent3);
            }
        });
//
//        Button buttonlogin = (Button) findViewById(R.id.Button_Local);
//        buttonlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(password.getText().toString().equals("a") && username.getText().toString().equals("a")){
//                    Toast.makeText(MainActivity.this, "you have logged in successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this,Domsub.class);
//                    //intent.setClass(MainActivity.this,Domsub.class);
//                    startActivity(intent);
//            }else{
//                    Toast.makeText(MainActivity.this, "Incorrent username and password", Toast.LENGTH_SHORT).show();
//                    Intent intent2 = new Intent(MainActivity.this,bump.class);
//                    startActivity(intent2);
//                }
//        }
//
//    });
//        Button buttonfb= (Button) findViewById(R.id.button_lucky);
//        buttonfb.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Toast.makeText(MainActivity.this, "I'm not", Toast.LENGTH_SHORT).show();
//            }
//        }
//        );
    }

    public void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        LoginFbButton = (LoginButton) findViewById(R.id.login_fb_button);
        LoginFbButton.setReadPermissions("email", "public_profile");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("","handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("","signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("","signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
        LoginManager.getInstance().logOut();
    }
}

