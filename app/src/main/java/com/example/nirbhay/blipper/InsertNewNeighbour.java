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


public class InsertNewNeighbour  extends AsyncTask<String,Void,String> {
    SharedPreferences sharedpreferences;
    ProgressDialog am;
    private Context context;
    //flag 0 means get and 1 means post.(By default it is get.)
    public InsertNewNeighbour(Context context) {
        this.context = context;


    }

    protected void onPreExecute(){
        am = new ProgressDialog(this.context, AlertDialog.THEME_HOLO_DARK);
        am.setTitle("Adding New Neighbour");
        am.setMessage("Adding ... ");
        am.show();
        Log.e("onPreExecutive", "called" + am);
    }

    @Override
    protected String doInBackground(String... arg0) {


        try{
            String current_uid = arg0[0];
            String n_uid = arg0[1];
            String n_uname = arg0[2];


            String link="http://109.73.164.163/Blipper/add_neighbour.php";
            String data  = URLEncoder.encode("current_uid", "UTF-8") + "=" + URLEncoder.encode(current_uid, "UTF-8");
            data += "&" + URLEncoder.encode("n_uid", "UTF-8") + "=" + URLEncoder.encode(n_uid, "UTF-8");
            data += "&" + URLEncoder.encode("n_uname", "UTF-8") + "=" + URLEncoder.encode(n_uname, "UTF-8");



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

        if(result != null && !result.isEmpty()){

            if(result.trim().equals("Success")) {
                Toast toast= Toast.makeText(this.context, "New Neighbour Added!", Toast.LENGTH_SHORT);
                toast.setMargin(150,150);
                toast.show();
                Intent intent = new Intent(this.context, MainActivity.class);
                this.context.startActivity(intent);
            }
            if(result.trim().equals("Present")){
                Toast toast= Toast.makeText(this.context, "This Neighbour Already Exist!", Toast.LENGTH_SHORT);
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

