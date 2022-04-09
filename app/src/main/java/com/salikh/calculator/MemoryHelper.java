package com.salikh.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

public class MemoryHelper {

    private static MemoryHelper helper;
    private final SharedPreferences preferences;


    private MemoryHelper(Context context) {
        preferences = context.getSharedPreferences("calc", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {

        if (helper == null) {
            helper = new MemoryHelper(context);
        }
    }

    public static MemoryHelper getHelpr() {
        return helper;
    }


    // mainBack

    public int getThemeBack() {

        return preferences.getInt("theme_back", Color.parseColor("#4f5b66"));

    }

    public void setThemeBack(int themeId) {

        preferences.edit().putInt("theme_back", themeId).apply();

    }

    // cardBack

    public int getThemeCard() {

        return preferences.getInt("theme_card", Color.parseColor("#65737e"));

    }

    public void setThemeCard(int themeId) {

        preferences.edit().putInt("theme_card", themeId).apply();

    }

    // textColor

    public int getThemeText() {

        return preferences.getInt("theme_text", Color.parseColor("#343d46"));

    }

    public void setThemeText(int themeId) {

        preferences.edit().putInt("theme_text", themeId).apply();

    }

}
