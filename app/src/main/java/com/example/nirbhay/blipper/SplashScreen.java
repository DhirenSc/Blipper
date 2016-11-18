package com.example.nirbhay.blipper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

/**
 * Created by nirbhay on 11/1/16.
 */

public class SplashScreen extends AppCompatActivity{

    SharedPreferences sharedpreferences;
    String user_name="";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);
        Stetho.initializeWithDefaults(this);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        String Token_ = FirebaseInstanceId.getInstance().getToken();

        sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("device_key_x",Token_);
        editor.commit();

        //Added font Pacifico to Splash Screen
        TextView title = (TextView) findViewById(R.id.label_points1);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/Pacifico.ttf");
        title.setTypeface(font);



        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(4501);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    user_name = sharedpreferences.getString("user_name","NIR");

                    if(user_name.equals("") || user_name.equals("NIR") || user_name.equals("LOGOUT") ){
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else{

                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            intent.putExtra("us_id", user_name);
                            startActivity(intent);
                        }
                    }



            }
        };
        timerThread.start();


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
