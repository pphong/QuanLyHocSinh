package tdc.ltdd2.quanlyhocsinh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import tdc.ltdd2.quanlyhocsinh.model.Subject;

public class SubjectDatabaseHandler extends DatabaseHandler{
    private static final String TABLE_NAME = "subjects";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public SubjectDatabaseHandler(Context context) {
        super(context);
    }

    public void addSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subject.getSubjectName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Subject> getAllSubjects() {
        List<Subject>  classList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Subject subjects = new Subject(cursor.getInt(0), cursor.getString(1));
            classList.add(subjects);
            cursor.moveToNext();
        }
        return classList;
    }

    public Subject getSubjectById(int id) {
        Subject  student = new Subject();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return student;
    }

    public void updateSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subject.getSubjectName());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(subject.getSubjectId()) });
        db.close();
    }

    public void deleteSubject(int classId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(classId) });
        db.close();
    }
}
