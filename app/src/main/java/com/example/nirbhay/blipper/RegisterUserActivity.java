package com.example.nirbhay.blipper;

/**
 * Created by Nirbhay Pherwani on 16/11/2016
 */

import android.app.AlertDialog;
import android.os.AsyncTask;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class RegisterUserActivity  extends AsyncTask<String,Void,String> {
    ProgressDialog am;
    private Context context;
    //flag 0 means get and 1 means post.(By default it is get.)
    public RegisterUserActivity(Context context) {
        this.context = context;


    }

    protected void onPreExecute(){
        am = new ProgressDialog(this.context, AlertDialog.THEME_HOLO_DARK);
        am.setTitle("Registering User");
        am.setMessage("Registering ... ");
        am.show();
        Log.e("onPreExecutive", "called" + am);
    }

    @Override
    protected String doInBackground(String... arg0) {


        try{

            String name = arg0[0];
            String pass = arg0[1];
            String phone_no = arg0[2];


            String link="http://109.73.164.163/Blipper/user_registeration.php";
            String data  = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
            data += "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
            data += "&" + URLEncoder.encode("phone_no", "UTF-8") + "=" + URLEncoder.encode(phone_no, "UTF-8");



            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result){
        am.dismiss();
        String[] results;

        if(result != null && !result.isEmpty()){
            results = result.split("@@@@");
            if(results[0].equals("Success")) {
                LoginActivity.userid = results[1];
                Intent intent = new Intent(this.context, LoginActivity.class);
                this.context.startActivity(intent);
                Toast toast= Toast.makeText(this.context, "Registered Successfully!", Toast.LENGTH_SHORT);
                toast.setMargin(150,150);
                toast.show();

            }
            else if(results[0].equals("Available")){
                Toast toast= Toast.makeText(this.context, "Username or Phone Number has already been taken!", Toast.LENGTH_SHORT);
                toast.setMargin(150,150);
                toast.show();
            }


        }
        else{
            Toast toast= Toast.makeText(this.context, "Unable to connect to the server!", Toast.LENGTH_SHORT);
            toast.setMargin(150,150);
            toast.show();
        }



    }
}

