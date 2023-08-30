package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CommentReceiveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_receive);
        Intent intent=getIntent();
        String message= intent.getStringExtra(CommentSendActivity.MESSAGE_KEY);
        TextView textView = findViewById(R.id.output);
        textView.setText(message);
    }
}
