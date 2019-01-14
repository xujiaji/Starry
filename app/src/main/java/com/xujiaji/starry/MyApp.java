package com.xujiaji.starry;

import android.app.Application;
import android.content.Context;

import com.xujiaji.msg.SAlert;
import com.xujiaji.msg.SToast;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }

    private void config() {
        SToast.config(new SToast.Proxy() {
            @Override
            public void normal(String msg) {

            }

            @Override
            public void success(String msg) {

            }

            @Override
            public void error(String msg) {

            }

            @Override
            public void warning(String msg) {

            }
        });

        SAlert.config(new SAlert.Proxy() {
            @Override
            protected void normal(Context context, String msg, SAlert.Callback yes, SAlert.Callback no) {

            }

            @Override
            protected void success(Context context, String msg, SAlert.Callback yes, SAlert.Callback no) {

            }

            @Override
            protected void warning(Context context, String msg, SAlert.Callback yes, SAlert.Callback no) {

            }

            @Override
            protected void error(Context context, String msg, SAlert.Callback yes, SAlert.Callback no) {

            }
        });
    }

}
