package com.example.nirbhay.blipper;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by nirbhay on 11/17/16.
 */

public class AddNeighbour extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_neighbour);

        YoYo.with(Techniques.FadeIn)
                .duration(1500)
                .playOn(findViewById(R.id.RXLayout));

        TextView myTextView2=(TextView)findViewById(R.id.label_points);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Pacifico.ttf");
        myTextView2.setTypeface(typeFace);
        Intent i = getIntent();

        if(i.hasExtra("neigh")){
            CardView vDetails = (CardView) findViewById(R.id.viewDetails);
            FloatingActionButton fSubmit = (FloatingActionButton) findViewById(R.id.addNeighbour);
            String details = "";
            details = i.getStringExtra("neigh");
            String n_Details [];
            n_Details = details.split("////");
            vDetails.setVisibility(View.VISIBLE);
            fSubmit.setVisibility(View.VISIBLE);

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

            TextView uid = (TextView) findViewById(R.id.d_id);
            uid.setText(n_Details[5]);

            View myView = findViewById(R.id.RXLayout);


        }
    }
    public void goSearchUser(View v){
        EditText sData = (EditText) findViewById(R.id.searchquery);
        String username = sData.getText().toString();
        if(username.equals("")){
            Toast toast= Toast.makeText(this, "SearchBox is empty!", Toast.LENGTH_SHORT);
            toast.setMargin(150,150);
            toast.show();
        }
        else{
            new GetNeighbourDetails(this).execute(username);
        }
    }
    public void addNeighbour(View v){
        TextView uid = (TextView) findViewById(R.id.d_id);
        String neig_id = uid.getText().toString();
        TextView uname = (TextView) findViewById(R.id.d_uname);
        String n_uname = uname.getText().toString();
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
        String cur_uid = sharedpreferences.getString("user_name","USER_NA");
        if(cur_uid.equals(neig_id)){
            Toast toast= Toast.makeText(this, "Cannot Add yourself as neighbour!", Toast.LENGTH_SHORT);
            toast.setMargin(150,150);
            toast.show();
        }
        else {
            new InsertNewNeighbour(this).execute(cur_uid, neig_id, n_uname);
        }
    }
    @Override
    public void onBackPressed() {

            Intent intent = new Intent(AddNeighbour.this, MainActivity.class);
            startActivity(intent);

        }
    }

