package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  int seconds =0;
    private boolean running = false;
    private boolean wasRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("life cycle","coCreate");
        setContentView(R.layout.activity_main);
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("life cycle","onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("life cycle","onStop");

    }
    @Override
    protected  void onPause(){
        super.onPause();
        Log.d("life cycle","onPause");
        wasRunning = running;
        running = false;
    }
    @Override
    protected  void onResume(){
        super.onResume();
        Log.d("life cycle","onResume");
        if(wasRunning)
            running = true;
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("life cycle","onStart");
    }
    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }
    private void runTimer(){
        final TextView timeView = findViewById(R.id.time_view);
        final Handler handler= new Handler();
        handler.post(new  Runnable(){
            @Override
            public void run(){
                int hours = seconds/3600;
                int minutes= (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}
