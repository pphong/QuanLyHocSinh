package tdc.ltdd2.quanlyhocsinh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.model.Class;
import tdc.ltdd2.quanlyhocsinh.model.Student;
import tdc.ltdd2.quanlyhocsinh.model.Subjects;
import tdc.ltdd2.quanlyhocsinh.model.Teachers;

public class StudentDatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String TABLE_CLASS_NAME = "class";
    private static final String TABLE_TEACHERS_NAME = "teachers";
    private static final String TABLE_SUBJECTS_NAME = "subjects";

    private static final String KEY_ID = "id";
    private static final String KEY_CLASS_ID = "id_class";
    private static final String KEY_TEACHERS_ID = "id_teachers";
    private static final String KEY_SUBJECTS_ID = "id_subjects";
    private static final String KEY_NAME = "name";
    private static final String KEY_CLASS_NAME = "class_name";
    private static final String KEY_TEACHERS_NAME = "teachers_name";
    private static final String KEY_SUBJECTS_NAME = "subjects_name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_BIRTH = "birth";

    public StudentDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table student
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_CLASS_ID, KEY_GENDER,KEY_BIRTH);
        db.execSQL(create_students_table);

        //create table class
        String create_class_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                TABLE_CLASS_NAME, KEY_CLASS_ID,KEY_CLASS_NAME);
        db.execSQL(create_class_table);

        //create table teachers
        String create_teachers_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_TEACHERS_NAME, KEY_TEACHERS_ID, KEY_TEACHERS_NAME, KEY_CLASS_ID, KEY_GENDER,KEY_BIRTH);
        db.execSQL(create_teachers_table);

        //create table subjects
        String create_subjects_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                TABLE_SUBJECTS_NAME, KEY_SUBJECTS_ID, KEY_SUBJECTS_NAME);
        db.execSQL(create_subjects_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }
    //add students
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

    //add class into table data
    public void addClass(Class cls) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_ID, cls.getClassId());
        values.put(KEY_CLASS_NAME, cls.getClassName());

        db.insert(TABLE_CLASS_NAME, null, values);
        db.close();
    }

    //add teachers into table data
    public void addTeachers(Teachers teachers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_ID, teachers.getClassId());
        values.put(KEY_TEACHERS_NAME, teachers.getTeachersName());
        values.put(KEY_GENDER, teachers.getTeachersGender());
        values.put(KEY_BIRTH, teachers.getTeachersBirth());

        db.insert(TABLE_TEACHERS_NAME, null, values);
        db.close();
    }

    //add subjects into table data
    public void addSubjects(Subjects subjects) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subjects.getSubjectName());
        values.put(KEY_CLASS_ID, subjects.getSubjectId());

        db.insert(TABLE_SUBJECTS_NAME, null, values);
        db.close();
    }

    //get all data from student table
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

    //get all data from student table
    public List<Teachers> getAllTeachers() {
        List<Teachers>  teachersList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TEACHERS_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Teachers teachers = new Teachers(cursor.getInt(0), cursor.getInt(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4));
            teachersList.add(teachers);
            cursor.moveToNext();
        }
        return teachersList;
    }

    //get all data from class table
    public List<Class> getAllClass() {
        List<Class>  classList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_CLASS_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Class aClass = new Class(cursor.getInt(0), cursor.getString(1));
            classList.add(aClass);
            cursor.moveToNext();
        }
        return classList;
    }

    //get all data from subjects table
    public List<Subjects> getAllSubjects() {
        List<Subjects>  subjectsList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_SUBJECTS_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Subjects subjects = new Subjects(cursor.getInt(0), cursor.getString(1));
            subjectsList.add(subjects);
            cursor.moveToNext();
        }
        return subjectsList;
    }


    public Student getStudentById(int id) {
        Student  student = new Student();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return student;
    }

    //update student data in sqlLite
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

    //update class data in sqlLite
    public void updateClass(Class aClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_NAME, aClass.getClassName());

        db.update(TABLE_CLASS_NAME, values, KEY_CLASS_ID + " = ?", new String[] { String.valueOf(aClass.getClassId()) });
        db.close();
    }

    //update teachers data in sqlLite
    public void updateTeachers(Teachers teachers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEACHERS_NAME, teachers.getTeachersName());
        values.put(KEY_CLASS_ID, teachers.getClassId());
        values.put(KEY_GENDER, teachers.getTeachersGender());
        values.put(KEY_BIRTH, teachers.getTeachersBirth());

        db.update(TABLE_TEACHERS_NAME, values, KEY_TEACHERS_ID + " = ?", new String[] { String.valueOf(teachers.getTeachersId()) });
        db.close();
    }

    //update subjects data in sqlLite
    public void updateSubjects(Subjects subjects) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECTS_NAME, subjects.getSubjectName());

        db.update(TABLE_SUBJECTS_NAME, values, KEY_SUBJECTS_ID + " = ?", new String[] { String.valueOf(subjects.getSubjectId()) });
        db.close();
    }

    //delete student by id in table sql
    public void deleteStudent(int studentID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(studentID) });
        db.close();
    }

    //delete teachers by id in table sql
    public void deleteTeachers(int teacherID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEACHERS_NAME, KEY_TEACHERS_ID + " = ?", new String[] { String.valueOf(teacherID) });
        db.close();
    }
    //delete class by id in table sql
    public void deleteClass(int classID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASS_NAME, KEY_CLASS_ID + " = ?", new String[] { String.valueOf(classID) });
        db.close();
    }
    //delete subjects by id in table sql
    public void deleteSubjects(int subjectsID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECTS_NAME, KEY_SUBJECTS_ID + " = ?", new String[] { String.valueOf(subjectsID) });
        db.close();
    }

}
