package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CommentSendActivity extends AppCompatActivity {
    public static final String MESSAGE_KEY="keymessager";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_send);
    }
    public void onSendMessage(View Button) {
        EditText editText= findViewById(R.id.input);
        String message=editText.getText().toString();
        Intent intent = new Intent(this,CommentReceiveActivity.class);
        intent.putExtra(MESSAGE_KEY,message);

        startActivity(intent);

    }
    public void onSendMessage1(View Button) {
        EditText editText= findViewById(R.id.input);
        String message=editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,message);
//        String chooserTitle = getString(R.string.choser);
//        Intent chosenIntent= Intent.createChooser(intent,chooserTitle);
        startActivity(intent);

    }
}
