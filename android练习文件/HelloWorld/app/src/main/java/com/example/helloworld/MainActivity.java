package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.ConnectionService;
import android.util.Log;
import android.view.View;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onLogin(View Button){
        try {
            URL url = new URL("http://localhost:8080/yonghui/LoginServlet");
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
            InputStream inputStream = httpURLConnection.getInputStream();
            int  c=0;
            while((c=inputStream.read())!=-1)
            { System.out.println(c);
            System.out.println("success");}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
