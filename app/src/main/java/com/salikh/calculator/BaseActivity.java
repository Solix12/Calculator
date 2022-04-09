package com.salikh.calculator;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onResume() {
        setThemeData();
        super.onResume();
    }

    public abstract void setThemeData();

}
