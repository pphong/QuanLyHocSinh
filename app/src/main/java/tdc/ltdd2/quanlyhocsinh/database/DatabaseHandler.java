package tdc.ltdd2.quanlyhocsinh.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENT = "students";
    private static final String TABLE_CLASS = "classes";

    public String sqlTableStudent(){
        String TABLE_NAME = "students";
        String KEY_ID = "id";
        String KEY_NAME = "name";
        String KEY_CLASS_ID = "class";
        String KEY_GENDER = "gender";
        String KEY_BIRTH = "birth";
        return String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_CLASS_ID, KEY_GENDER,KEY_BIRTH);
    }
    public String sqlTableClass(){
        String TABLE_NAME = "classes";
        String KEY_ID = "id";
        String KEY_NAME = "name";
        return String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME);
    }
    public String sqlTableTeachers(){
        String TABLE_NAME = "teachers";
        String KEY_ID = "id";
        String KEY_NAME = "name";
        return String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME);
    }
    public String sqlTableSubjects(){
        String TABLE_NAME = "subjects";
        String KEY_ID = "id";
        String KEY_NAME = "name";
        return String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME);
    }

    public String sqlDropTableIfExist(String tableName){
        return String.format("DROP TABLE IF EXISTS %s", tableName);
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.sqlTableStudent());
        sqLiteDatabase.execSQL(this.sqlTableClass());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(this.sqlDropTableIfExist(TABLE_STUDENT));
        sqLiteDatabase.execSQL(this.sqlDropTableIfExist(TABLE_CLASS));
        onCreate(sqLiteDatabase);
    }
}
