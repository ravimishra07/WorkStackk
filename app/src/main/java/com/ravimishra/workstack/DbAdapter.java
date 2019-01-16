package com.ravimishra.workstack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DbAdapter {
    Context context;
    DBHelper helper;
    SQLiteDatabase db;

ArrayList<Data> data=new ArrayList<>();
    RvAdapter rvAdapter=new RvAdapter(context,data);
    public DbAdapter(Context context) {
        this.context = context;
        this.helper = new DBHelper(context);
        rvAdapter=new RvAdapter(context,data);
    }

    public DbAdapter openDB() {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long add(String goal, String goalType,int val) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.GOAL, goal);
            cv.put(Constants.GOAL_TYPE, goalType);
            cv.put(Constants.GVALUE, val);
            return db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Cursor getAllData() {

        String[] column = {Constants.ROW_ID, Constants.GOAL, Constants.GOAL_TYPE,Constants.GVALUE};
        return db.query(Constants.TB_NAME, column, null, null, null, null, null);
    }

    public long update(int id, String goal, String goalType, int val) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.GOAL, goal);
            cv.put(Constants.GOAL_TYPE, goalType);
            cv.put(Constants.GVALUE, val);
            return db.update(Constants.TB_NAME, cv, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //DELETE
    public long Delete(int id)
    {
        try
        {

            long del= db.delete(Constants.TB_NAME,Constants.ROW_ID+" =?",new String[]{String.valueOf(id)});
            Log.d("DEL","record deleted");
            rvAdapter.notifyDataSetChanged();


return del;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }
}
