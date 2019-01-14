package com.xujiaji.msg;

import android.content.Context;

import java.lang.ref.WeakReference;

public class SAlert {

    private static Proxy proxy;

    private SAlert() {
        throw new RuntimeException("cannot new SAlert instance");
    }

    public static void config(Proxy proxy) {
        SAlert.proxy = proxy;
    }

    public static Proxy with(Context context) {
        checkProxy();
        proxy.context = new WeakReference<>(context);
        return proxy;
    }

    private static void checkProxy() {
        if (proxy == null) throw new RuntimeException("You need to config 'SAlert' in application");
    }

    public abstract static class Proxy {
        private WeakReference<Context> context;
        private String msg;
        private Callback yes;
        private Callback no;

        public Proxy message(String msg) {
            this.msg = msg;
            return this;
        }

        public Proxy yes(Callback yes) {
            this.yes = yes;
            return this;
        }

        public Proxy no(Callback no) {
            this.no = no;
            return this;
        }

        protected abstract void normal(Context context, String msg, Callback yes, Callback no);

        protected abstract void success(Context context, String msg, Callback yes, Callback no);

        protected abstract void warning(Context context, String msg, Callback yes, Callback no);

        protected abstract void error(Context context, String msg, Callback yes, Callback no);

        public void normal() {
            if (context == null || context.get() == null) return;
            normal(this.context.get(), this.msg, this.yes, this.no);
        }

        public void success() {
            if (context == null || context.get() == null) return;
            normal(this.context.get(), this.msg, this.yes, this.no);
        }

        public void warning() {
            if (context == null || context.get() == null) return;
            normal(this.context.get(), this.msg, this.yes, this.no);
        }

        public void error() {
            if (context == null || context.get() == null) return;
            normal(this.context.get(), this.msg, this.yes, this.no);
        }
    }

    public interface Callback {
        void call();
    }
}
