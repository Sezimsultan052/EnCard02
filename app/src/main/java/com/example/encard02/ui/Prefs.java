package com.example.encard02.ui;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class Prefs {
    public SharedPreferences preferences;

    @Inject
    public Prefs(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveBoardState() {
        preferences.edit().putBoolean("isShow", true).apply();
    }

    public boolean isBoardShow() {
        return preferences.getBoolean("isShow", false);
    }
}
