package com.xujiaji.msg;

/**
 * Toast Proxy
 */
public class SToast {
    private static Proxy proxy;

    private SToast() {
        throw new RuntimeException("cannot new SToast instance");
    }

    public static void config(Proxy proxy) {
        SToast.proxy = proxy;
    }

    public static void normal(String msg) {
        checkProxy();
        proxy.normal(msg);
    }

    public static void success(String msg) {
        checkProxy();
        proxy.success(msg);
    }

    public static void error(String msg) {
        checkProxy();
        proxy.error(msg);
    }

    public static void warning(String msg) {
        checkProxy();
        proxy.warning(msg);
    }

    private static void checkProxy() {
        if (proxy == null) throw new RuntimeException("You need to config 'SToast' in application");
    }

    public interface Proxy {
        void normal(String msg);
        void success(String msg);
        void error(String msg);
        void warning(String msg);
    }
}
