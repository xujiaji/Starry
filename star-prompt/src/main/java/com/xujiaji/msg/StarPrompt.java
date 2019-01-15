package com.xujiaji.msg;

public class StarPrompt {

    public static void init(I i) {
        SAlert.config(i.configAlert());
        SToast.config(i.configToast());
        i.configLog();
    }

    public interface I {
        SAlert.Proxy configAlert();
        SToast.Proxy configToast();
        void configLog();
    }
}
