package com.mylanguagepal;

import android.app.Application;
import android.util.Log;

public class App extends Application {

    /* Constants */
    public static final String LOG_TAG = "MY_LANGUAGE_PAL_LOG";

    public static final String DATABASE_NAME = "mylanguagepal.db";
    public static final int    DATABASE_VERSION = 1;

    /* Private */
    private static App _instance;
    private DatabaseManager _db;

    /* Methods */
    public static App instance() {
        return _instance;
    }

    public DatabaseManager db() {
        return _db;
    }

    /* Main */
    @Override
    public void onCreate() {
        super.onCreate();

        // * Set singleton *
        _instance = this;

        // ***** Run *****
        Log.w(LOG_TAG, "Main");

        // Initialize db manager
        MigrationsHelper dbHelper = new MigrationsHelper(this, DATABASE_NAME, null, DATABASE_VERSION);
        _db = new DatabaseManager(dbHelper);
    }
}
