package com.example.nirbhay.blipper;

/**
 * Created by nirbhay on 11/16/16.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Nirbhay Pherwani on 16/11/2016
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    SharedPreferences sharedpreferences;

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        registerToken(token);
    }

    private void registerToken(String token) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request = new Request.Builder()
                .url("http://109.73.164.163/Blipper/register.php")
                .post(body)
                .build();

        sharedpreferences = getSharedPreferences("user_PREFERNCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("device_key_x",token);
        editor.commit();


        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}