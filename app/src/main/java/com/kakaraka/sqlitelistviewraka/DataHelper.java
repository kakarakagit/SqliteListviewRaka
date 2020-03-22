package com.kakaraka.sqlitelistviewraka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "databasenya";
    private static final String COL_1 = "id";
    private static final String COL_2 = "nama";



    public DataHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String create = "CREATE TABLE " + TABLE_NAME + " (" +  COL_1 + " INTEGER PRIMARY KEY," + COL_2 + " TEXT " + ")";

        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i1, int i2){
        db.execSQL("DROP IF TABLE EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData (String mNama){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, mNama);

        long result =  db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemId(String name ){
        SQLiteDatabase db = this.getWritableDatabase();

        String que = "SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + name + "'";

        Cursor data = db.rawQuery(que, null);
        return data;

    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String quer = "UPDATE " + TABLE_NAME + " SET " + COL_2 + " = '" + newName + "' WHERE " + COL_1 + " = '" + id + "'" + " AND "
                + COL_2 + " = '" + oldName + "'";
        db.execSQL(quer);
    }

    public void deleteData(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryDelet = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_1 + " = '" + id + "'" + " AND " + COL_2 + " = '" + name + "'";
        db.execSQL(queryDelet);
    }


}
