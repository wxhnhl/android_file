package com.example.xyt;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.xyt.R;

public class StarBuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="starbuzz.db";
    private static final int DB_VER = 3;
    public StarBuzzDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "NAME TEXT,"+
                "DESCRIPTION TEXT,"+
                "IMAGE_RESOURCE_ID INTEGER,"+
                "FAVORITE INTEGER)");
        insertDrink(db,"工学馆","东秦工学馆", R.drawable.latte);
        insertDrink(db,"图书馆","图书馆的闭馆音乐",R.drawable.cappuccino);
        insertDrink(db,"南校区","南校区的咖啡厅",R.drawable.filter);
    }
    private static void insertDrink(SQLiteDatabase db,String name,String description,int resourceId){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        drinkValues.put("IMAGE_RESOURCE_ID",resourceId);
        long result = db.insert("DRINK",null,drinkValues);
        Log.d("sqlite","insert"+name+"_id"+result);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion<=1){
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
        }
        if(oldVersion<=2){
            ContentValues latteDesc = new ContentValues();
            latteDesc.put("DESCRIPTION","TASTY");
            db.update("DRINK",latteDesc,"NAME=?",new String[]{"Lattle"});
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
        }

    }

}
