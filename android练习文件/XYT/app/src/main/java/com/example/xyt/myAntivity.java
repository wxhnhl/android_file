package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class myAntivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_antivity);
    }

    public void onSmrz(View Button) {
        Intent intent = new Intent(this,shimingrenzheng.class);
        startActivity(intent);
    }

    public void onXyjf(View Button) {
        Intent intent = new Intent(this,xinyongjjifen.class);
        startActivity(intent);
    }

    public void onWdzw(View Button) {
        Intent intent = new Intent(this,wodezuowei.class);
        startActivity(intent);
    }

}
