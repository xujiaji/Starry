package com.xujiaji.local;

import android.content.Context;

public class StarLocal {

    public static void init(I i) {
        SPref.init(i.applicationContext());
        SLocal.config(i.configLocal());
    }

    public interface I {
        Context applicationContext();
        SLocal.Proxy configLocal();
    }
}
