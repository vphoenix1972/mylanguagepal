package com.mylanguagepal;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // App main
        Log.w("MY_TAG", "Main");
    }
}
