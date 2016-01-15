package com.snapsoft.khain.photo_app.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.snapsoft.khain.photo_app.objects.Data;

import java.util.ArrayList;

/**
 * Created by khain on 10/01/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //DB VERSION
    private static final int DB_VERSION = 1;
    //NAME DB
    private static final String DB_NAME = "wallpapers";
    //TABLE DATA
    private static final String TABLE_DATA = "data";

    private static final String KEY_PHOTOID = "photoId";
    private static final String KEY_PHOTOURL = "photoUrl";
    private static final String KEY_THUMBNAILURL = "thumbnailUrl";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_DOWNLOADCOUNT = "downloadCount";
    private static final String KEY_CREATEDTIME = "createdTime";
    private static final String KEY_MODIFIEDTIME = "modifiedTime";
    //TABLE CATEGORY
    private static final String TABLE_CATEGORY = "category";

    private static final String KEY_CATEGORYID = "categoryId";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTIONS = "descrition";
    private static final String KEY_CREATEDTIMES = "createdTime";
    private static final String KEY_MODIFIEDTIMES = "modifiedTime";


    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREAT_DATA_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_PHOTOID + " INTEGER PRIMARY KEY,"
                + KEY_PHOTOURL + " TEXT,"
                + KEY_THUMBNAILURL + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_WIDTH + " TEXT,"
                + KEY_HEIGHT + " TEXT,"
                + KEY_DOWNLOADCOUNT + " TEXT,"
                + KEY_CREATEDTIME + " TEXT,"
                + KEY_MODIFIEDTIME + " TEXT )";
        String CREAT_CATEGORY_TABLE = "CREAT TABLE " + TABLE_CATEGORY + "("
                + KEY_CATEGORYID + "INTEGER PRIMARY KEY,"
                + KEY_NAME + "TEXT,"
                + KEY_DESCRIPTIONS + "TEXT,"
                + KEY_CREATEDTIMES + "BIGINTEGER,"
                + KEY_MODIFIEDTIMES + "BIGINTEGER);";
        // db.execSQL(CREAT_CATEGORY_TABLE);
        System.out.println(CREAT_DATA_TABLE);
        db.execSQL(CREAT_DATA_TABLE);
        Log.e(null,"test");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }

    public void insertDB(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PHOTOID, data.getPhotoId());
        values.put(KEY_PHOTOURL, data.getPhotoUrl());
        values.put(KEY_THUMBNAILURL, data.getThumbnailUrl());
        values.put(KEY_TITLE, data.getTitle());
        values.put(KEY_DESCRIPTION, data.getDescription());
        values.put(KEY_WIDTH, ""+data.getWidth());
        values.put(KEY_HEIGHT, ""+data.getHeight());
        values.put(KEY_DOWNLOADCOUNT, ""+data.getDownloadCount());
        values.put(KEY_CREATEDTIME, ""+data.getCreatedTime());
        values.put(KEY_MODIFIEDTIME, ""+data.getModifiedTime());
        db.insert(TABLE_DATA, null, values);
        db.close();
    }

    public ArrayList<Data> selectDB() {
        ArrayList<Data> temp = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_DATA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            temp.add(parserData(cursor));
            cursor.moveToNext();
        }
        return temp;

    }

    public Data parserData(Cursor cursor) {
        Data d = new Data();
        d.setPhotoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_PHOTOID))));
        d.setPhotoUrl(cursor.getString(cursor.getColumnIndex(KEY_PHOTOURL)));
        d.setThumbnailUrl(cursor.getString(cursor.getColumnIndex(KEY_THUMBNAILURL)));
        d.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
        d.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
        d.setWidth(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_WIDTH))));
        d.setHeight(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_HEIGHT))));
        d.setDownloadCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_DOWNLOADCOUNT))));
        d.setCreatedTime(Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_CREATEDTIME))));
        d.setModifiedTime(Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_MODIFIEDTIME))));
        return d;
    }
}
