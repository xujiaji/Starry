package com.xujiaji.starry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xujiaji.msg.SAlert;
import com.xujiaji.msg.SLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SLog.i_d("江诗丹顿撒酒疯两款手机");

        SAlert.with(this)
                .message("测试测试")
                .yes(new SAlert.Callback() {
                    @Override
                    public void call() {

                    }
                })
                .no(new SAlert.Callback() {
                    @Override
                    public void call() {

                    }
                })
                .normal();
    }
}
