package com.example.message;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiveMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        Intent intent=getIntent();
        String message= intent.getStringExtra(CreateMessageActivity.MESSAGE_KEY);
        TextView textView = findViewById(R.id.output);
        textView.setText(message);
    }
}
