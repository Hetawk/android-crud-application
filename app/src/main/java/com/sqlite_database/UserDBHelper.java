package com.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "user_info";


    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name VARCHAR NOT NULL," +
                "age INTEGER NOT NULL," +
                "height LONG NOT NULL," +
                "weight FLOAT NOT NULL," +
                "married INTEGER NOT NULL," +
                "update_time VARCHAR NOT NULL);";
        sqLiteDatabase.execSQL(sql);
        Log.i("UserDBHelper", "onCreate method executed successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop the existing table if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create the table again
        onCreate(sqLiteDatabase);
    }


    public long saveUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());
        values.put("married", user.isMarried() ? 1 : 0); // Convert boolean to integer
        values.put("update_time", user.getUpdateTime());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public boolean deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(userId)});
        db.close();
        return result > 0;
    }

    public boolean updateUserById(int userId, User newUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newUser.getName());
        values.put("age", newUser.getAge());
        values.put("height", newUser.getHeight());
        values.put("weight", newUser.getWeight());
        values.put("married", newUser.isMarried() ? 1 : 0);
        values.put("update_time", newUser.getUpdateTime());
        int result = db.update(TABLE_NAME, values, "_id=?", new String[]{String.valueOf(userId)});
        db.close();
        return result > 0;
    }




    public long insert(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("height", user.getHeight());
        values.put("weight", user.getWeight());
        values.put("married", user.isMarried() ? 1 : 0);
        values.put("update_time", user.getUpdateTime());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public List<User> queryAll() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setAge(cursor.getInt(2));
                user.setHeight(cursor.getLong(3));
                user.setWeight(cursor.getFloat(4));
                user.setMarried(cursor.getInt(5) == 1);
                user.setUpdateTime(cursor.getString(6));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}
