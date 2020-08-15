package com.example.projectnhom15.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBConection extends SQLiteAssetHelper {

   private static final String DATABASE_NAME="dbhs.db";
   private static final int DATABASE_VERSION=1;

   //constructor
    public DBConection(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
}
