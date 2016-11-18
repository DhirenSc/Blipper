package com.example.nirbhay.blipper;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by nirbhay on 11/17/16.
 */

public class UpdateProfile extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_details);
        TextView myTextView2 = (TextView) findViewById(R.id.label_points);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        myTextView2.setTypeface(typeFace);
        Intent i = getIntent();

        if(i.hasExtra("user_det")){
            String details = "";
            details = i.getStringExtra("user_det");
            String profile_Details [];
            profile_Details = details.split("////");

            TextView id = (TextView) findViewById(R.id.p_uid);
            id.setText(profile_Details[0]);

            TextView uname = (TextView) findViewById(R.id.p_uname);
            uname.setText(profile_Details[2]);

            EditText phone = (EditText) findViewById(R.id.fphone);
            phone.setText(profile_Details[1]);

            EditText address = (EditText) findViewById(R.id.faddress);
            address.setText(profile_Details[5]);

            EditText flatno = (EditText) findViewById(R.id.flatnumber);
            flatno.setText(profile_Details[4]);

            EditText name = (EditText) findViewById(R.id.fname);
            name.setText(profile_Details[3]);



        }

    }
    public void updateProfile(View v){
        TextView id = (TextView) findViewById(R.id.p_uid);
        String id_ = id.getText().toString();

        EditText phone = (EditText) findViewById(R.id.fphone);
        String phone_ = phone.getText().toString();

        EditText address = (EditText) findViewById(R.id.faddress);
        String address_ = address.getText().toString();

        EditText flatno = (EditText) findViewById(R.id.flatnumber);
        String fno_ =flatno.getText().toString();

        EditText name = (EditText) findViewById(R.id.fname);
        String name_ =name.getText().toString();

        if(!name_.equals("") && !address_.equals("") && !phone_.equals("") && !flatno.equals("")) {

            boolean b = Pattern.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$", phone_);
            if(b)
            {
                new UpdateUserProfileDetails(this).execute(id_, phone_, name_, address_, fno_);

            }
            else
            {
                Toast toast= Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_SHORT);
                toast.setMargin(150,150);
                toast.show();
            }
        }
        else if(name_.equals("")||fno_.equals("")||phone_.equals("")||address_.equals("")){
            Toast toast= Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT);
            toast.setMargin(150,150);
            toast.show();
        }




    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
        startActivity(intent);

    }
}
