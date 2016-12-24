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


public class GetNeighbourDetails  extends AsyncTask<String,Void,String> {
    SharedPreferences sharedpreferences;
    ProgressDialog am;
    private Context context;
    //flag 0 means get and 1 means post.(By default it is get.)
    public GetNeighbourDetails(Context context) {
        this.context = context;


    }

    protected void onPreExecute(){
        SuperActivityToast.create(this.context, new Style(), Style.TYPE_PROGRESS_BAR)
                .setText("Retrieving User Details")
                .setDuration(Style.DURATION_MEDIUM)
                .setFrame(Style.FRAME_KITKAT)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_LIGHT_BLUE))
                .setAnimations(Style.ANIMATIONS_SCALE).show();
        Log.e("onPreExecutive", "called");
    }

    @Override
    protected String doInBackground(String... arg0) {


        try{
            String uname = arg0[0];

            String link="http://109.73.164.163/Blipper/find_user.php";
            String data  = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8");


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
        String[] results;

        if(result != null && !result.isEmpty()){
            results = result.split("@@@@");


            if(results[0].trim().equals("Success")) {
                Intent intent = new Intent(this.context, AddNeighbour.class);
                intent.putExtra("neigh",results[1]);
                this.context.startActivity(intent);
                SuperActivityToast.create(this.context, new Style(), Style.TYPE_STANDARD)
                        .setText("User Details")
                        .setDuration(Style.DURATION_MEDIUM)
                        .setFrame(Style.FRAME_KITKAT)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_LIGHT_BLUE))
                        .setAnimations(Style.ANIMATIONS_SCALE).show();
            }
            else{
                SuperActivityToast.create(this.context, new Style(), Style.TYPE_STANDARD)
                        .setText("There is no such username!")
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

