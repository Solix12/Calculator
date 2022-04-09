package com.salikh.calculator;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MemoryHelper.init(this);
    }
}
