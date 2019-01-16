package com.xujiaji.local;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SPref {

    private static Context mApplicationContext;

    private SPref() {}

    public static void init(Context context) {
        if (!(context instanceof Application)) {
            throw new RuntimeException("you need to use application context");
        }
        mApplicationContext = context;
    }

    public static <T> void put(@NonNull String key, @Nullable T value) {
        if (value == null) {
            clearKey(key);
            return;
        }
        if (value instanceof String) {
            editor()
                    .putString(key, (String) value)
                    .apply();
        } else if (value instanceof Integer) {
            editor()
                    .putInt(key, (Integer) value)
                    .apply();
        } else if (value instanceof Long) {
            editor()
                    .putLong(key, (Long) value)
                    .apply();
        } else if (value instanceof Boolean) {
            editor()
                    .putBoolean(key, (Boolean) value)
                    .apply();
        } else if (value instanceof Float) {
            editor()
                    .putFloat(key, (Float) value)
                    .apply();
        } else {
            editor()
                    .putString(key, value.toString())
                    .apply();
        }
    }

    @Nullable
    public static String getString(@NonNull String key) {
        return SP().getString(key, null);
    }

    public static boolean getBoolean(@NonNull String key) {
        return SP().getAll().get(key) instanceof Boolean && SP().getBoolean(key, false);
    }

    public static int getInt(@NonNull String key) {
        return SP().getAll().get(key) instanceof Integer ? SP().getInt(key, 0) : -1;
    }

    public static long getLong(@NonNull String key) {
        return SP().getLong(key, 0);
    }

    public static float getFloat(@NonNull String key) {
        return SP().getFloat(key, 0);
    }

    public static void clearKey(@NonNull String key) {
        editor().remove(key).apply();
    }


    public static boolean isExist(@NonNull String key) {
        return SP().contains(key);
    }

    public static void clearPrefs() {
        editor().clear().apply();
    }

    public static Map<String, ?> getAll() {
        return SP().getAll();
    }

    private static SharedPreferences.Editor editor() {
        return SP().edit();
    }

    private static SharedPreferences SP() {
        if (mApplicationContext == null)
            throw new RuntimeException("you not init SPref");
        return PreferenceManager.getDefaultSharedPreferences(mApplicationContext);
    }

}
