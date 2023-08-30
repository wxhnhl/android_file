package com.example.xyt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xyt.R;
import com.example.xyt.StarBuzzDatabaseHelper;

import java.sql.SQLException;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKID="drinkId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = getIntent().getExtras().getInt(EXTRA_DRINKID);
//        Drink drink = Drink.drinks[drinkId];
//
//        TextView name =(TextView)findViewById(R.id.description);
//        name.setText(drink.getName());
//
//        TextView description = (TextView)findViewById(R.id.description);
//        description.setText(drink.getDescription());
//
//        ImageView photo = (ImageView)findViewById(R.id.photo);
//        photo.setImageResource(drink.getImageResourceId());
//        photo.setContentDescription(drink.getName());
        SQLiteOpenHelper starBuzzDatabaseHelper = new StarBuzzDatabaseHelper(this);
        try{
            SQLiteDatabase db = starBuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","FAVORITE"},
                    "_id=?",new String[]{Integer.toString(drinkId)},null,null,null);

            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3)==1);
                TextView name = findViewById(R.id.name);
                name.setText(nameText);

                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView photo = findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                CheckBox favarite = findViewById(R.id.favorite);
                favarite.setChecked(isFavorite);
            }
            cursor.close();
        }catch (SQLiteException e){
            Log.e("sqlite",e.getMessage());
            Toast toast = Toast.makeText(this,"DataBase unaviliable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view) {
//        CheckBox favorite = (CheckBox)view;
//        ContentValues drinkValues = new ContentValues();
//        drinkValues.put("FAVORITE",favorite.isChecked());
        int drinkId = getIntent().getExtras().getInt(EXTRA_DRINKID);
        new UpdateDrinkTask().execute(drinkId);
//        SQLiteOpenHelper MyHelper = new StarBuzzDatabaseHelper(this);
//        try{
//            SQLiteDatabase db = MyHelper.getWritableDatabase();
//            int row = db.update("DRINK",drinkValues,"_id=?",new String[]{Integer.toString(drinkId)});
//            Log.d("sqlite","update row"+row);
//        }catch( SQLiteException e){
//            Log.e("sqlite",e.getMessage());
//            Toast.makeText(this,"database unavailable",Toast.LENGTH_SHORT).show();
//        }
    }

    private class UpdateDrinkTask extends AsyncTask<Integer,Void,Boolean>{
        private  ContentValues drinkValues;
        protected  void onPreExecute(){
            CheckBox favarite = findViewById(R.id.favorite);
            drinkValues= new ContentValues();
            drinkValues.put("FAVORITE",favarite.isChecked());
        }

        protected Boolean doInBackground(Integer... integers){
            int drinkId = getIntent().getExtras().getInt(EXTRA_DRINKID);
            SQLiteOpenHelper MyHelper = new StarBuzzDatabaseHelper(DrinkActivity.this);
            try{SQLiteDatabase db = MyHelper.getWritableDatabase();
                db.update("DRINK",drinkValues,"_id=?",new String[]{Integer.toString(drinkId)});
                return  true;
            }catch ( SQLiteException e ){
                Log.e("sqlite",e.getMessage());
                return false;
            }
        }

        protected void onPostExecute(Boolean success){
            if (!success){
                Toast.makeText(DrinkActivity.this,"Database unavailable",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
