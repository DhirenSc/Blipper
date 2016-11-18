package com.example.nirbhay.blipper;
/**
 * Created by Nirbhay Pherwani on 16/11/2016
 */

import android.app.AlertDialog;
import android.content.SharedPreferences;
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


public class UpdateUserProfileDetails  extends AsyncTask<String,Void,String> {
    SharedPreferences sharedpreferences;
    ProgressDialog am;
    private Context context;
    //flag 0 means get and 1 means post.(By default it is get.)
    public UpdateUserProfileDetails(Context context) {
        this.context = context;


    }

    protected void onPreExecute(){
        am = new ProgressDialog(this.context, AlertDialog.THEME_HOLO_DARK);
        am.setTitle("Validating login");
        am.setMessage("Validating ... ");
        am.show();
        Log.e("onPreExecutive", "called" + am);
    }

    @Override
    protected String doInBackground(String... arg0) {


        try{
            String id = arg0[0];
            String phone = arg0[1];
            String name = arg0[2];
            String address = arg0[3];
            String fno = arg0[4];






            String link="http://109.73.164.163/Blipper/update_user_profile.php";
            String data  = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            data += "&" + URLEncoder.encode("u_phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
            data += "&" + URLEncoder.encode("u_fname", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
            data += "&" + URLEncoder.encode("u_addr", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
            data += "&" + URLEncoder.encode("u_fno", "UTF-8") + "=" + URLEncoder.encode(fno, "UTF-8");


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


            if(results[0].trim().equals("Success")) {
                Intent intent = new Intent(this.context, MainActivity.class);
                this.context.startActivity(intent);
                Toast toast= Toast.makeText(this.context, "Profile Updated Successfully!", Toast.LENGTH_SHORT);
                toast.setMargin(150,150);
                toast.show();

            }
            else{
                Toast toast= Toast.makeText(this.context, "Unable to Update!", Toast.LENGTH_SHORT);
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

