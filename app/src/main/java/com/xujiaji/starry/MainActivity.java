package com.xujiaji.starry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xujiaji.local.SLocal;
import com.xujiaji.local.SPref;
import com.xujiaji.msg.SAlert;
import com.xujiaji.msg.SLog;
import com.xujiaji.msg.SToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SLog.i_d("Hello world!!!");
        SPref.put("key", "hello SPref");
        String value = SLocal.get("key", String.class);
        SLog.e_d("" + value);
    }

    public void onClickAlert(View view) {
        SAlert.with(this)
                .title("title")
                .message("test content")
                .yes(new SAlert.Callback("confirm") {

                    @Override
                    public void call() {
                        SToast.normal("clicked confirm");
                    }
                })
                .no(new SAlert.Callback("cancel") {
                    @Override
                    public void call() {
                        SToast.normal("clicked cancel");
                    }
                })
                .normal();

    }

    public void onClickToast(View view) {
        SToast.success("SToast");
    }
}
