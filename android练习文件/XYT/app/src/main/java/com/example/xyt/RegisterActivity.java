package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public  void onClickSubmit(View view){
        String name= "您已提交！";
        Button button = findViewById(R.id.button_submit);
        button.setText(name);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
