    package com.example.programadviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.String;

    public class MainActivity extends AppCompatActivity {
        private final ProgramExpert expert= new ProgramExpert();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickButton(View Button){
        Spinner spinner = findViewById(R.id.feature);
        String feature = spinner.getSelectedItem().toString();
        String language = expert.getLanguage(feature);
        TextView textVIew = findViewById(R.id.language);
        textVIew.setText(language);
    }
}
