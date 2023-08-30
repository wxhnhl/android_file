package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View Button) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    public void onEnter(View Button) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
