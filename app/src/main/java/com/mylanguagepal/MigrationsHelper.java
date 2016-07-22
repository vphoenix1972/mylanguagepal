package com.mylanguagepal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MigrationsHelper extends SQLiteOpenHelper {
    private final int _currentVersion;

    public MigrationsHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        _currentVersion = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 1;  i <= _currentVersion; i++) {
            Upgrade(db, i);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion + 1;  i <= newVersion; i++) {
            Upgrade(db, i);
        }
    }

    private void Upgrade(SQLiteDatabase db, int newVersion)
    {
        if (newVersion == 1) {
            // Initial create

            // Schema
            //
            // Tags:
            //      _id: INTEGER PRIMARY KEY AUTOINCREMENT
            //      name: TEXT
            //

            db.execSQL("CREATE TABLE Tags (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT" +
                    ");");
        } else {

        }
    }
}
