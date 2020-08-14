package tdc.ltdd2.quanlyhocsinh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.model.Teacher;

public class TeacherDatabaseHandler extends DatabaseHandler{
    private static final String TABLE_NAME = "teachers";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CLASS_ID = "class";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_BIRTH = "birth";

    public TeacherDatabaseHandler(Context context) {
        super(context);
    }

    public void addTeacher(Teacher teacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, teacher.getTeacherName());
        values.put(KEY_CLASS_ID, teacher.getTeacherClass());
        values.put(KEY_GENDER, teacher.getTeacherGender());
        values.put(KEY_BIRTH, teacher.getTeacherBirth());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher>  teacherList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Teacher teacher = new Teacher(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4));
            teacherList.add(teacher);
            cursor.moveToNext();
        }
        return teacherList;
    }

    public Teacher getTeacherById(int id) {
        Teacher  teacher = new Teacher();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return teacher;
    }

    public void updateTeacher(Teacher teacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, teacher.getTeacherName());
        values.put(KEY_CLASS_ID, teacher.getTeacherClass());
        values.put(KEY_GENDER, teacher.getTeacherGender());
        values.put(KEY_BIRTH, teacher.getTeacherBirth());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(teacher.getTeacherId()) });
        db.close();
    }

    public void deleteTeacher(int teacherId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(teacherId) });
        db.close();
    }
}
