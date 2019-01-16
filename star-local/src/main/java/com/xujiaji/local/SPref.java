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
            clear(key);
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

    @SuppressWarnings("unchecked")
    public static <T> T get(@NonNull String key, @NonNull Class<T> type) {
        if (type == String.class) {
            return (T) SP().getString(key, null);
        } else if (type == Boolean.class) {
            return (T) Boolean.valueOf(SP().getAll().get(key) instanceof Boolean && SP().getBoolean(key, false));
        } else if (type == Integer.class) {
            return (T) Integer.valueOf(SP().getAll().get(key) instanceof Integer ? SP().getInt(key, 0) : -1);
        } else if (type == Long.class) {
            return (T) Long.valueOf(SP().getLong(key, 0));
        } else if (type == Float.class) {
            return (T) Float.valueOf(SP().getFloat(key, 0));
        }
        return (T) getAll().get(key);
    }

    public static void clear(@NonNull String key) {
        editor().remove(key).apply();
    }


    public static boolean isExist(@NonNull String key) {
        return SP().contains(key);
    }

    public static void clearAll() {
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
