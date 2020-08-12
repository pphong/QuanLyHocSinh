//package tdc.ltdd2.quanlyhocsinh.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import tdc.ltdd2.quanlyhocsinh.model.Class;
//import tdc.ltdd2.quanlyhocsinh.model.Class;
//
//public class ClassDatabaseHandler extends SQLiteOpenHelper{
//    private static final String DATABASE_NAME = "schoolManager";
//    private static final int DATABASE_VERSION = 1;
//    private static final String TABLE_NAME = "classes";
//
//    private static final String KEY_ID = "id";
//    private static final String KEY_NAME = "name";
//
//    public ClassDatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String create_classes_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
//                TABLE_NAME, KEY_ID, KEY_NAME);
//        db.execSQL(create_classes_table);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String drop_classes_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
//        db.execSQL(drop_classes_table);
//
//        onCreate(db);
//    }
//
//    public void addClass(Class classO) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, classO.getClassName());
//
//        db.insert(TABLE_NAME, null, values);
//        db.close();
//    }
//
//    public List<Class> getAllClasses() {
//        List<Class>  studentList = new ArrayList<>();
//        String query = "SELECT * FROM " + TABLE_NAME;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.moveToFirst();
//
//        while(cursor.isAfterLast() == false) {
//            Class student = new Class(cursor.getInt(0), cursor.getString(1));
//            studentList.add(student);
//            cursor.moveToNext();
//        }
//        return studentList;
//    }
//
//    public Class getClassById(int id) {
//        Class  student = new Class();
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query,null);
//        return student;
//    }
//
//    public void updateClass(Class student) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, student.getClassName());
//
//        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(student.getClassId()) });
//        db.close();
//    }
//
//    public void deleteClass(int studentId) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(studentId) });
//        db.close();
//    }
//}
