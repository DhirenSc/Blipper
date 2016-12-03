package com.example.nirbhay.blipper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by nirbhay on 11/26/16.
 */
public class Walkthrough extends AppIntro {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Welcome to Blipper", "Inform your caregivers and neighbours about an emergency on the press of a single Button!", R.drawable.family_, Color.parseColor("#3181DA")));
        addSlide(AppIntroFragment.newInstance("SOS!", "Easily add and view your preferable neighbours, relatives, doctors and hospitals for first hand help!", R.drawable.house_, Color.parseColor("#3181DA")));
        addSlide(AppIntroFragment.newInstance("Get Notified!", "Recieve Real Time Notifications along with tracking to Victim's Last Known Location!", R.drawable.notification_, Color.parseColor("#42A5F5")));
        addSlide(AppIntroFragment.newInstance("Keep a Note!", "View Emergencies and keep a track of Stats with ease.", R.drawable.stats_, Color.parseColor("#64B5F6")));
        setDepthAnimation();
        setScrollDurationFactor(4);
        showSkipButton(true);

    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        Intent intent = new Intent(Walkthrough.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(Walkthrough.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}



