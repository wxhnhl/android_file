package com.example.odometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection;
    private OdometerService odometerService;
    private  boolean bound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                OdometerService.OdometerBinder binder = (OdometerService.OdometerBinder)service;
                odometerService = binder.getOdometer();
                bound=true;
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };
        displayDistance();
    }

    private final int PERMISSION_REQUEST_CODE =698;
    @Override
    public void onStart(){
        super.onStart();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_CODE);
        }else{
        Intent intent = new Intent(this,OdometerService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);}
    }


    public void onRequestPermissionResult(int requestCode,String permissions[],int[]grantResult){
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:{
                if(grantResult.length>0&&grantResult[0]==PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(this,OdometerService.class);
                    bindService(intent,connection, Context.BIND_AUTO_CREATE);
                }else{
                    Toast.makeText(this,"Location permission required",Toast.LENGTH_SHORT);
                }
            }
        }
    }
    @Override
    public void onStop(){
        super.onStop();
        if(bound){
            unbindService(connection);
            bound=false;
        }
    }
    public void displayDistance(){
        final TextView distanceView = findViewById(R.id.distance);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
               double distance = 0.0;
               if(bound&&odometerService!=null){
                   distance= odometerService.getDistance();
               }
               String distanceStr = String.format(Locale.getDefault(),"%1$,.2fKM",distance);
               distanceView.setText(distanceStr);
               handler.postDelayed(this,1000);
            }
        });
    }

}
