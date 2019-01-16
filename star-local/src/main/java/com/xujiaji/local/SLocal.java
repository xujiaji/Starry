package com.xujiaji.local;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SLocal {
    private static Proxy proxy;

    private SLocal() {
        throw new RuntimeException("cannot new SLocal instance");
    }

    public static void config(@NonNull Proxy proxy) {
        SLocal.proxy = proxy;
    }

    public static <T> void put(@NonNull String key, @Nullable T value) {
        check();
        proxy.put(key, value);
    }

    public static <T> T get(@NonNull String key, @NonNull Class<T> type) {
        check();
        return proxy.get(key, type);
    }

    public static boolean isExist(@NonNull String key) {
        check();
        return proxy.isExist(key);
    }

    public static void clear(@NonNull String key) {
        check();
        proxy.clear(key);
    }

    public static void clearAll() {
        check();
        proxy.clearAll();
    }

    public static Map<String, ?> getAll() {
        check();
        return proxy.getAll();
    }

    private static void check() {
        if (proxy == null) throw new RuntimeException("You need to config 'SLocal' in application");
    }

    public interface Proxy {
        <T> void put(@NonNull String key, @Nullable T value);
        <T> T get(@NonNull String key, @NonNull Class<T> type);
        boolean isExist(@NonNull String key);
        void clear(@NonNull String key);
        void clearAll();
        Map<String, ?> getAll();
    }
}
