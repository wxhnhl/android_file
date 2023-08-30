package com.example.xyt;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.core.app.ActivityCompat;

public class OdometerService extends Service {
    private double distanceMeters;
    private LocationManager locManager;
    private LocationListener locListener;

    @Override
    public void  onCreate(){
        super.onCreate();
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        String provider = locManager.getBestProvider(new Criteria(),true);
        locListener = new LocationListener() {
            private Location lastLocation;
            @Override
            public void onLocationChanged(Location location) {
                if(lastLocation==null){
                    lastLocation = location;
                }
                distanceMeters+=location.distanceTo(lastLocation);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            locManager.requestLocationUpdates(provider,1000,1,locListener);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (locManager!=null&&locListener!=null){
            locManager.removeUpdates(locListener);
        }
        locManager=null;
        locListener=null;
    }
    public class OdometerBinder extends Binder {
        OdometerService getOdometer(){
            return OdometerService.this;
        }
    }

    private  IBinder binder = new OdometerBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    //    private Random random = new Random();
    public double getDistance(){
//        return  random.nextDouble();
        return distanceMeters/1000;
    }
}
