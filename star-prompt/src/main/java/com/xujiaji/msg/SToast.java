package com.xujiaji.msg;

import androidx.annotation.NonNull;

/**
 * Toast Proxy
 */
public class SToast {
    private static Proxy proxy;

    private SToast() {
        throw new RuntimeException("cannot new SToast instance");
    }

    public static void config(@NonNull Proxy proxy) {
        SToast.proxy = proxy;
    }

    public static void normal(@NonNull String msg) {
        checkProxy();
        proxy.normal(msg);
    }

    public static void success(@NonNull String msg) {
        checkProxy();
        proxy.success(msg);
    }

    public static void error(@NonNull String msg) {
        checkProxy();
        proxy.error(msg);
    }

    public static void warning(@NonNull String msg) {
        checkProxy();
        proxy.warning(msg);
    }

    private static void checkProxy() {
        if (proxy == null) throw new RuntimeException("You need to config 'SToast' in application");
    }

    public interface Proxy {
        void normal(@NonNull String msg);
        void success(@NonNull String msg);
        void error(@NonNull String msg);
        void warning(@NonNull String msg);
    }
}
