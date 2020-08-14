package tdc.ltdd2.quanlyhocsinh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import tdc.ltdd2.quanlyhocsinh.model.Class;

public class ClassDatabaseHandler extends DatabaseHandler{
    private static final String TABLE_NAME = "classes";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public ClassDatabaseHandler(Context context) {
        super(context);
    }

    public void addClass(Class classO) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, classO.getClassName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Class> getAllClasses() {
        List<Class>  classList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Class classes = new Class(cursor.getInt(0), cursor.getString(1));
            classList.add(classes);
            cursor.moveToNext();
        }
        return classList;
    }

    public Class getClassById(int id) {
        Class  student = new Class();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return student;
    }

    public void updateClass(Class classO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, classO.getClassName());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(classO.getClassId()) });
        db.close();
    }

    public void deleteClass(int classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(classId) });
        db.close();
    }
}
