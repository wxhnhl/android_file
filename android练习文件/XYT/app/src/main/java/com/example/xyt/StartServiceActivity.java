package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
    }
    public void onClick(View view){
        Intent intent = new Intent(this,DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE,getResources().getString(R.string.response));
        startService(intent);
    }
}
