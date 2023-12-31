package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends AppCompatActivity {

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

//        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<Drink>(this, android.R.layout.simple_list_item_1, Drink.drinks);
        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
//        listDrinks.setAdapter(listAdapter);
        SQLiteOpenHelper starBuzzDatabaseHelper = new StarBuzzDatabaseHelper(this);
        try{SQLiteDatabase db = starBuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query("DRINK",new String[]{"_id","NAME"},
                    null,null,null,null,null);

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_expandable_list_item_1, cursor,new String[]{"NAME"},
                    new int[]{android.R.id.text1},0);
            listDrinks.setAdapter(listAdapter);


        }catch (SQLiteException e){
            Log.e("sqlite",e.getMessage());
            Toast toast = Toast.makeText(this,"DataBase unaviliable",Toast.LENGTH_SHORT);
            toast.show();
        }
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id);
                startActivity(intent);
            }
        };
        listDrinks.setOnItemClickListener(itemClickListener);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
    }
}
