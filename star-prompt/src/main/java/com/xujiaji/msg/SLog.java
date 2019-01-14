package com.xujiaji.msg;

import android.util.Log;

import java.util.Locale;

/**
 * Log print
 */
public class SLog {

    /**
     * print log: tag + msg
     */
    public static void v(String tag, String msg) { if (BuildConfig.DEBUG) Log.v(tag, msg); }

    /**
     * print log: tag + msg
     */
    public static void d(String tag, String msg) { if (BuildConfig.DEBUG) Log.d(tag, msg); }

    /**
     * print log: tag + msg
     */
    public static void i(String tag, String msg) { if (BuildConfig.DEBUG) Log.i(tag, msg); }

    /**
     * print log: tag + msg
     */
    public static void w(String tag, String msg) { if (BuildConfig.DEBUG) Log.w(tag, msg); }

    /**
     * print log: tag + msg
     */
    public static void e(String tag, String msg) { if (BuildConfig.DEBUG) Log.e(tag, msg); }


    /**
     * print log: class name tag + msg
     */
    public static void v(String msg) { if (BuildConfig.DEBUG) v(generateTag(), msg); }

    /**
     * print log: class name tag + msg
     */
    public static void d(String msg) { if (BuildConfig.DEBUG) d(generateTag(), msg); }

    /**
     * print log: class name tag + msg
     */
    public static void i(String msg) { if (BuildConfig.DEBUG) i(generateTag(), msg); }

    /**
     * print log: class name tag + msg
     */
    public static void w(String msg) { if (BuildConfig.DEBUG) w(generateTag(), msg); }

    /**
     * print log: class name tag + msg
     */
    public static void e(String msg) { if (BuildConfig.DEBUG) e(generateTag(), msg); }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Msg
     */
    public static void v_m(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            v(generateTag(st), packMethodMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Msg
     */
    public static void d_m(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            d(generateTag(st), packMethodMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Msg
     */
    public static void i_m(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            i(generateTag(st), packMethodMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Msg
     */
    public static void w_m(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            w(generateTag(st), packMethodMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Msg
     */
    public static void e_m(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            e(generateTag(st), packMethodMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Position Line Number + Msg
     */
    public static void v_d(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            v(generateTag(st), packPositionMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Position Line Number + Msg
     */
    public static void d_d(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            d(generateTag(st), packPositionMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Position Line Number + Msg
     */
    public static void i_d(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            i(generateTag(st), packPositionMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Position Line Number + Msg
     */
    public static void w_d(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            w(generateTag(st), packPositionMsg(st, msg));
        }
    }

    /**
     * print log: Class name tag + Thread name + Thread id + Method name + Position Line Number + Msg
     */
    public static void e_d(String msg) {
        if (BuildConfig.DEBUG) {
            final StackTraceElement st = getCallerElement();
            e(generateTag(st), packPositionMsg(st, msg));
        }
    }

    /**
     * get caller element
     * @return caller element
     */
    private static StackTraceElement getCallerElement() {
//        StackTraceElement[] sts = new Throwable().fillInStackTrace().getStackTrace();
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod() || Thread.class.getName().equals(st.getClassName()) || SLog.class.getName().equals(st.getClassName())) {
                continue;
            }
            return st;
        }
        return null;
    }

    /**
     * get caller class name
     * @return caller class name
     */
    private static String generateTag() {
        return generateTag(null);
    }

    private static String generateTag(StackTraceElement st) {
        if (st == null) {
            st = getCallerElement();
        }
        if (st == null) return "";
        return st.getClassName().substring(st.getClassName().lastIndexOf('.') + 1);
    }

    /**
     * pack message
     * @param originMsg origin message
     * @return pack message
     */
    private static String packMethodMsg(StackTraceElement st, String originMsg) {
        if (st == null) return originMsg;
        return String.format(Locale.US, "[%s-%d] %s(): %s",
                Thread.currentThread().getName(),
                Thread.currentThread().getId(),
                st.getMethodName(),
                originMsg);
    }

    /**
     * generate caller position
     * @return caller position
     */
    private static String packPositionMsg(StackTraceElement st, String msg) {
        if (st == null) return msg;
        return String.format(Locale.US, "%s\n\t┏━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n\t┣ Thread ╋%s-%s\n\t┣Position╋%s.%s(%s:%d)\n\t┗━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛",
                msg,
                Thread.currentThread().getName(),
                Thread.currentThread().getId(),
                st.getClassName(),
                st.getMethodName(),
                st.getFileName(),
                st.getLineNumber());
    }
}
