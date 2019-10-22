package com.example.demojsonactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class CountryCodeAsync extends AsyncTask<String, Void, String>  {

    private  AsyncListener mAsynsListener;

    interface AsyncListener{
        void onCompleted(String s);
    }

    public CountryCodeAsync(Context context, AsyncListener asyncListener){
        mAsynsListener = asyncListener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String contryUrl = strings[0];
        try {
            URL url = new URL(contryUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            return convertInputStreamToString(in);
        }catch (MalformedURLException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mAsynsListener.onCompleted(s);
    }
    private String convertInputStreamToString(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e){
        }

        return builder.toString();
    }

}
