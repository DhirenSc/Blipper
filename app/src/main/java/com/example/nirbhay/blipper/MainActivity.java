package com.example.nirbhay.blipper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedpreferences;
    public static String id_user="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView myTextView2=(TextView)findViewById(R.id.textk);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Pacifico.ttf");
        myTextView2.setTypeface(typeFace);


        Intent x = getIntent();
        if(x.hasExtra("us_id")){
            sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
            String token = sharedpreferences.getString("device_key_x","KEY_NA");
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("user_name",x.getStringExtra("us_id"));
            editor.commit();
            if(x.hasExtra("uname")) {
                editor.putString("last_uname", x.getStringExtra("uname"));
                editor.commit();
            }
            if(x.hasExtra("fname")) {
                editor.putString("last_fname", x.getStringExtra("fname"));
                editor.commit();
            }
            new UpdateUserOnDevice(this).execute(token,x.getStringExtra("us_id"));

        }

        sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
        id_user = sharedpreferences.getString("user_name","USERID");


        sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
        String U_NAME = sharedpreferences.getString("last_uname","USERNAME").toString();
        String F_NAME = sharedpreferences.getString("last_fname","FULLNAME").toString();
        Log.e(U_NAME,"k1");
        Log.e(F_NAME,"k2");

        NavigationView navigationViewx = (NavigationView) findViewById(R.id.nav_view);
        navigationViewx.setNavigationItemSelectedListener(this);
        View header=navigationViewx.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView t1 = (TextView) header.findViewById(R.id.User_Name) ;
        TextView t2 = (TextView) header.findViewById(R.id.Full_Name) ;

        t1.setText(U_NAME);
        t2.setText(F_NAME);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_view_em) {
            Intent intent = new Intent(MainActivity.this, ViewEmergencies.class);
            startActivity(intent);
        } else if (id == R.id.nav_add_neig) {
            Intent intent = new Intent(MainActivity.this, AddNeighbour.class);
            startActivity(intent);

        }  else if (id == R.id.nav_view_neig) {

            Intent intent = new Intent(MainActivity.this, ViewNeighbours.class);
            startActivity(intent);

        } else if (id == R.id.nav_upd_det) {

            sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
            String uid = sharedpreferences.getString("user_name","USER_NA");
            new GetUserProfileDetails(this).execute(uid);

        } else if (id == R.id.nav_logout) {
            sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("user_name","LOGOUT");
            editor.commit();
            String token = sharedpreferences.getString("device_key_x","KEY_NA");
            new UpdateUserOnDevice(this).execute(token,"NONE_LOGGED_IN");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void add_a_Neighbour(View v){
        Intent i = new Intent(MainActivity.this, AddNeighbour.class);
        startActivity(i);
    }
    public void updateP(View v){
        sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
        String uid = sharedpreferences.getString("user_name","USER_NA");
        new GetUserProfileDetails(this).execute(uid);
    }
    public void notify_NEIGHBOURS(View v) {
        NotifyNeighBours();
    }
    private void NotifyNeighBours() {

        class GetJson extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast toast= Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
                toast.setMargin(150,150);
                toast.show();


            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://109.73.164.163/Blipper/push_notification.php?uid="+MainActivity.id_user);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;
                    while((result = bufferedReader.readLine())!= null){
                        return result;
                    }


                   return "No Response from Server!";
                }catch(Exception e){
                    return null;
                }

            }

        }
        GetJson gJ = new GetJson();
        gJ.execute();

    }
}
