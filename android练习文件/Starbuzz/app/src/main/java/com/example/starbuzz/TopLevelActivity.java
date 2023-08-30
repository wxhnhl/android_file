package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        setupOptionsListView();
        setupOptionsListView();
    }
    private Cursor favoritesCursor;
    private SQLiteDatabase db;

    private void setupOptionsListView() {
        ListView listFavorites = findViewById(R.id.list_favorites);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarBuzzDatabaseHelper(this);
        try{
            db= starbuzzDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("DRINK",new String[]{"_id","NAME"},"FAVORITE=1",null,null,null,null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(TopLevelActivity.this,android.R.layout.simple_list_item_1,
                    favoritesCursor,new String[]{"NAME"},new int[]{android.R.id.text1},0);
            listFavorites.setAdapter(favoriteAdapter);
        }catch (  SQLException e ){
            Log.e("sqlite",e.getMessage());
            Toast.makeText(this,"database unavilable",Toast.LENGTH_SHORT).show();
        }

        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int)id);
                startActivity(intent);
            }
        });
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if(position==0)  {
                  Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                  startActivity(intent);
              }
            }
            };


        ListView listView =(ListView)findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Cursor newcursor =  db.query("DRINK",new String[]{"_id","NAME"},"FAVORITE=1",null,null,null,null);
      ListView listFavorites = findViewById(R.id.list_favorites);
      CursorAdapter adapter = (CursorAdapter)listFavorites.getAdapter();
      adapter.changeCursor(newcursor);
        favoritesCursor=newcursor;
    }

}
