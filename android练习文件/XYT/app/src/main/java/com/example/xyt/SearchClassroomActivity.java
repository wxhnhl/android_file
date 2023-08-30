package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.String;

public class SearchClassroomActivity extends AppCompatActivity {
    private final EmptyClassroom expert= new EmptyClassroom();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_classroom);
    }
    public void onClickButton(View Button){
        Spinner spinner = findViewById(R.id.feature);
        String feature = spinner.getSelectedItem().toString();
        String language = expert.getLanguage(feature);
        TextView textVIew = findViewById(R.id.language);
        textVIew.setText(language);
    }
}
