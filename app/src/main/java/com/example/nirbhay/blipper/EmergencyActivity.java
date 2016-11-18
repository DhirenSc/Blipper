package com.example.nirbhay.blipper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by nirbhay on 11/18/16.
 */

public class EmergencyActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        TextView myTextView2 = (TextView) findViewById(R.id.label_points);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        myTextView2.setTypeface(typeFace);

        Intent i =getIntent();
        if(i.hasExtra("emergency")){
            MediaPlayer mMediaPlayer = new MediaPlayer();
            mMediaPlayer = MediaPlayer.create(this, R.raw.siren);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.setVolume(100,100);
            mMediaPlayer.start();
            
            
            String details = "";
            details = i.getStringExtra("emergency");
            String n_Details [];
            n_Details = details.split("////");

            TextView name = (TextView) findViewById(R.id.d_name);
            name.setText(n_Details[2]);

            TextView uname = (TextView) findViewById(R.id.d_uname);
            uname.setText(n_Details[1]);

            TextView phone = (TextView) findViewById(R.id.d_phone);
            phone.setText(n_Details[0]);

            TextView address = (TextView) findViewById(R.id.d_addr);
            address.setText(n_Details[4]);

            TextView flatno = (TextView) findViewById(R.id.d_fnum);
            flatno.setText(n_Details[3]);
            
            

        }
        if(i.hasExtra("emergency_id")){
            new GetEmergencyDetails(this).execute(i.getStringExtra("emergency_id"));
        }


    }
    @Override
    public void onBackPressed() {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);


    }
}
