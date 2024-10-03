package com.example.storeapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_name = "Students.db";
    public static final String Table_name = "Student_table";
    public static final String col_id = "Id";
    public static final String col_name = "name";
    public static final String col_marks = "marks";
    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_name +" (Id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , marks INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String name,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_name,name);
        cv.put(col_marks,marks);
        Long result = db.insert(Table_name,null,cv);
        if(result == -1 )
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor Showdata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Table_name,null);
        return cursor;
    }

    public boolean update(String id,String name,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_id,id);
        cv.put(col_name,name);
        cv.put(col_marks,marks);
        db.update(Table_name,cv,"Id = ?",new String[] { id });
        return true;
    }

    public Integer delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name,"Id = ?",new String[] {id});
    }



}
