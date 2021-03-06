package com.example.nirbhay.blipper;
/**
 * Created by Nirbhay Pherwani on 16/11/2016
 */

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

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
    private Context context;
    //flag 0 means get and 1 means post.(By default it is get.)
    public InsertNewNeighbour(Context context) {
        this.context = context;


    }

    protected void onPreExecute(){
        /*We are facing a problem here and also same username is being added as a neighbour*/
        SuperActivityToast.create(this.context, new Style(), Style.TYPE_PROGRESS_BAR)
                .setProgressBarColor(Color.WHITE)
                .setText("Adding New Neighbour")
                .setDuration(Style.DURATION_MEDIUM)
                .setFrame(Style.FRAME_KITKAT)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_LIGHT_BLUE))
                .setAnimations(Style.ANIMATIONS_SCALE).show();

        Log.e("onPreExecutive", "called");
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


        if(result != null && !result.isEmpty()){

            if(result.trim().equals("Success")) {

                Intent intent = new Intent(this.context, MainActivity.class);
                this.context.startActivity(intent);
                SuperActivityToast.create(this.context, new Style(), Style.TYPE_STANDARD)
                        .setText("New Neighbour Added")
                        .setDuration(Style.DURATION_LONG)
                        .setFrame(Style.FRAME_KITKAT)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_LIGHT_GREEN))
                        .setAnimations(Style.ANIMATIONS_SCALE).show();
            }
            if(result.trim().equals("Present")){
                SuperActivityToast.create(this.context, new Style(), Style.TYPE_STANDARD)
                        .setText("This neighbour already exists")
                        .setDuration(Style.DURATION_MEDIUM)
                        .setFrame(Style.FRAME_KITKAT)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                        .setAnimations(Style.ANIMATIONS_SCALE).show();
            }



        }
        else{
            SuperActivityToast.create(this.context, new Style(), Style.TYPE_STANDARD)
                    .setText("Unable to connect to the server!")
                    .setDuration(Style.DURATION_MEDIUM)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                    .setAnimations(Style.ANIMATIONS_SCALE).show();
        }



    }
}

