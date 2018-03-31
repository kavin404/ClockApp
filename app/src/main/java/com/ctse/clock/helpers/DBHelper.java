package com.ctse.clock.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.util.Log;
import android.widget.Toast;

import com.ctse.clock.models.CountryItem;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "databace.db";
    private static final String TABLE = "country";
    private String country[] = {"Japan", "New Zealand", "Australia", "USA", "UK", "China", "France", "Russia", "Canada", "Mexico", "Turkey", "Singapore"};
    private String timeZones[] = {"GMT+9:00", "GMT+13:00", "GMT+11:00", "GMT-9:00", "GMT+0:00", "GMT+8:00", "GMT+1:00", "GMT+6:00", "GMT-6:00", "GMT-5:00", "GMT+3:00", "GMT+8:00"};
    private Context context;
    public DBHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS country (id integer PRIMARY KEY, name text, timezone text, checked integer)");
    }

    public void populateFirstTime(){
        SQLiteDatabase dbs = this.getWritableDatabase();
        String count = "SELECT count(*) FROM country";

        Cursor mcursor = dbs.rawQuery(count, null);
        mcursor.moveToFirst();

        int icount = mcursor.getInt(0);
        Log.i("Count is",Integer.toString(icount));
        if(icount<=0) {
            Log.i("NoT ","does not initiate again");
            for(int i=0;i<country.length;i++){
                save(country[i],timeZones[i],false);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(String country, String timezome, boolean checked) {
        ContentValues cv = new ContentValues();
        cv.put("name", country);
        cv.put("timezone", timezome);
        if (checked) {
            cv.put("checked", 1);
        } else {
            cv.put("checked", 0);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("country", null, cv);
        db.close();
    }

    public boolean read(String country) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT checked FROM country WHERE name='" + country +"'", null);
        cur.moveToNext();
        return cur.getInt(cur.getColumnIndex("checked"))==1;
    }

    public List<CountryItem> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM country WHERE checked = 1", null);
        cur.moveToNext();

        int countryIdx = cur.getColumnIndex("name");
        int timezoneIdx = cur.getColumnIndex("timezone");
        int checkedIdx = cur.getColumnIndex("checked");

        List<CountryItem> data = new ArrayList<>();
        while(!cur.isAfterLast()){

            String country = cur.getString(countryIdx);
            String timezone = cur.getString(timezoneIdx);
            boolean checked = cur.getInt(checkedIdx)==1;
            data.add(new CountryItem(country,timezone,checked));
        }
        return data;
    }

    public void update(String country, boolean checked){
        ContentValues cv = new ContentValues();
        if (checked) {
            cv.put("checked", 1);
        } else {
            cv.put("checked", 0);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("country", null, cv);
        db.update("country",cv,"name='"+country+"'",null);
    }

}
