package com.example.nirbhay.blipper;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


/**
 * Created by Nirbhay Pherwani on 16/11/2016
 */

public class SignUpActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_form);

        TextView myTextView2=(TextView)findViewById(R.id.label_points);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Pacifico.ttf");
        myTextView2.setTypeface(typeFace);


    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);

    }


    public void registerUser(View view){

        EditText name = (EditText) findViewById(R.id.name_reg);
        String name_ = name.getText().toString();

        EditText phone_no = (EditText) findViewById(R.id.phone_reg);
        String phone_ = phone_no.getText().toString();

        EditText password = (EditText) findViewById(R.id.password_reg);
        String password_ = password.getText().toString();

        EditText reppass = (EditText) findViewById(R.id.reppassword_reg);
        String reppass_ = reppass.getText().toString();

        if(password_.equals(reppass_) && !name_.equals("") && !password_.equals("") && !phone_.equals("")) {

            boolean b = Pattern.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$", phone_);
            if(b)
                new RegisterUserActivity(this).execute(name_, password_, phone_);
            else
            {
                Toast toast= Toast.makeText(this, "Invalid Phone Number!", Toast.LENGTH_SHORT);
                toast.setMargin(150,150);
                toast.show();
            }
        }
        else if(name_.equals("")||password_.equals("")||phone_.equals("")){
            Toast toast= Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT);
            toast.setMargin(150,150);
            toast.show();
        }
        else if(!password_.equals(reppass_)){
            Toast toast= Toast.makeText(this, "Passwords Don't Match!", Toast.LENGTH_SHORT);
            toast.setMargin(150,150);
            toast.show();

        }


    }


}
