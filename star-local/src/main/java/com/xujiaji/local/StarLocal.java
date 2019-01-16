package com.xujiaji.local;

import android.content.Context;

public class StarLocal {

    public static void init(I i) {
        SPref.init(i.applicationContext());
    }

    public interface I {
        Context applicationContext();
    }
}
