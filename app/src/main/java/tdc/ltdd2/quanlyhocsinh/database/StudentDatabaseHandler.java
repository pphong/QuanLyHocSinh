package tdc.ltdd2.quanlyhocsinh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.model.Student;

public class StudentDatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CLASS_ID = "class";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_BIRTH = "birth";

    public StudentDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_CLASS_ID, KEY_GENDER,KEY_BIRTH);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getStudentName());
        values.put(KEY_CLASS_ID, student.getStudentClass());
        values.put(KEY_GENDER, student.getStudentGender());
        values.put(KEY_BIRTH, student.getStudentBirth());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Student> getAllStudents() {
        List<Student>  studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Student student = new Student(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }

    public Student getStudentById(int id) {
        Student  student = new Student();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return student;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getStudentName());
        values.put(KEY_CLASS_ID, student.getStudentClass());
        values.put(KEY_GENDER, student.getStudentGender());
        values.put(KEY_BIRTH, student.getStudentBirth());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(student.getStudentId()) });
        db.close();
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(studentId) });
        db.close();
    }
}
