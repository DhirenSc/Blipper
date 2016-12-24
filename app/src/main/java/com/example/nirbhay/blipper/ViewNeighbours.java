package com.example.nirbhay.blipper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewNeighbours extends AppCompatActivity{

    public String[] phone_no;
    public String[] n_unames;
    public String[] n_uid;
    public String[] n_uaddr;
    public String[] n_ufno;
    public String[] n_ufname;

    ListView listView;
    public static String uidN="";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_neighbours);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
        uidN = sharedpreferences.getString("user_name","USER_NA");
        getJSON();
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ViewNeighbours.this, MainActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent i = new Intent(ViewNeighbours.this, MainActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getJSON() {

        class GetJson extends AsyncTask<Void,Void,String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(s.equals("0 results")){
                    TextView k = (TextView) findViewById(R.id.tvx);
                    k.setVisibility(View.VISIBLE);

                }
                else {
                    Log.d("DATA----------->", s);
                    parseJSON(s);
                    showData();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://109.73.164.163/Blipper/viewNeighbours.php?uid="+uidN);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        if(json.equals("0 results")){
                            return json;
                        }
                        else {
                            sb.append(json + "\n");
                        }
                    }

                    Log.d("JSON---->",sb.toString().trim());
                    return sb.toString().trim();


                }catch(Exception e){
                    return null;
                }

            }

        }
        GetJson gJ = new GetJson();
        gJ.execute();

    }

    public void showData(){



        listView = (ListView) findViewById(R.id.list_neig);
        listView.setAdapter(new CustomAdapter(this, phone_no,n_unames,n_uid,n_uaddr,n_ufname,n_ufno));;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("IN check","Listener");




            }
        });

    }


    private void parseJSON(String json){
        try {


            JSONArray jsonArray = new JSONArray(json);
            phone_no = new String[jsonArray.length()];
            n_unames = new String[jsonArray.length()];
            n_uid = new String[jsonArray.length()];
            n_uaddr = new String[jsonArray.length()];
            n_ufno = new String[jsonArray.length()];
            n_ufname = new String[jsonArray.length()];
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject k = jsonArray.getJSONObject(i);
                phone_no[i] = getPhoneno(k);
                n_unames[i] = getUname(k);
                n_uid[i] = getID(k);
                n_uaddr[i] = getAddress(k);
                n_ufno[i] = getFlatNo(k);
                n_ufname[i] = getFullName(k);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String getPhoneno(JSONObject j){
        String pno = null;
        try {
            pno = j.getString(Config.TAG_PHONENO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pno;
    }
    private String getUname(JSONObject j){
        String uname = null;
        try {
            uname = j.getString(Config.TAG_USERNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return uname;
    }
    private String getID(JSONObject j){
        String x = null;
        try {
            x = j.getString(Config.TAG_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getAddress(JSONObject j){
        String x = null;
        try {
            x = j.getString(Config.TAG_ADDRESS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getFlatNo(JSONObject j){
        String x = null;
        try {
            x = j.getString(Config.TAG_FLATNO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }
    private String getFullName(JSONObject j){
        String x = null;
        try {
            x = j.getString(Config.TAG_FNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }





}
