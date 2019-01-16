package com.xujiaji.msg;

import android.app.Activity;
import android.os.Build;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

public class SAlert {

    private static Proxy proxy;

    private SAlert() {
        throw new RuntimeException("cannot new SAlert instance");
    }

    public static void config(@NonNull Proxy proxy) {
        SAlert.proxy = proxy;
    }

    public static Proxy with(@NonNull Activity activity) {
        checkProxy();
        if (proxy.activity != null && proxy.activity.get() != null) {
            if (proxy.lastActivity != null && proxy.lastActivity.get() == activity) {
                return proxy;
            }
            proxy.lastActivity = proxy.activity;
        }
        proxy.activity = new WeakReference<>(activity);
        return proxy;
    }

    private static void checkProxy() {
        if (proxy == null) throw new RuntimeException("You need to config 'SAlert' in application");
    }

    public abstract static class Proxy {
        private WeakReference<Activity> lastActivity;
        protected WeakReference<Activity> activity;
        protected String title;
        protected String msg;
        protected Callback yes;
        protected Callback no;

        protected boolean useLastAlert() {
            return lastActivity == activity;
        }

        public Proxy title(@NonNull String title) {
            this.title = title;
            return this;
        }

        public Proxy message(@NonNull String msg) {
            this.msg = msg;
            return this;
        }

        public Proxy yes(@NonNull Callback yes) {
            this.yes = yes;
            return this;
        }

        public Proxy no(@NonNull Callback no) {
            this.no = no;
            return this;
        }

        protected abstract void onNormal();

        protected abstract void onSuccess();

        protected abstract void onWarning();

        protected abstract void onError();

        protected abstract void onLoading();

        protected abstract void onProgress(float progress);


        public void normal() {
            if (check()) return;
            onNormal();
        }

        public void success() {
            if (check()) return;
            onSuccess();
        }

        public void warning() {
            if (check()) return;
            onWarning();
        }

        public void error() {
            if (check()) return;
            onError();
        }

        public void loading() {
            if (check()) return;
            onLoading();
        }

        public void progress(float progress) {
            if (check()) return;
            onProgress(progress);
        }

        private boolean check() {
            return activity == null || activity.get() == null || activity.get().isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.get().isDestroyed());
        }
    }

    public abstract static class Callback {
        public final String title;

        public Callback(@NonNull String title) {
            this.title = title;
        }
        public abstract void call();
    }
}
