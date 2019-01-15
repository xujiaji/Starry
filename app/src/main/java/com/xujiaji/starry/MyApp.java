package com.xujiaji.starry;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import com.xujiaji.msg.SAlert;
import com.xujiaji.msg.SLog;
import com.xujiaji.msg.SToast;
import com.xujiaji.msg.StarPrompt;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

public class MyApp extends Application implements StarPrompt.I {

    @Override
    public void onCreate() {
        super.onCreate();
        StarPrompt.init(this);
    }

    @Override
    public SAlert.Proxy configAlert() {
        return new SAlert.Proxy() {

            private SweetAlertDialog sweetAlertDialog;
            private int alertType;

            private void buildDialog(int alertType) {
                if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
                    sweetAlertDialog.dismiss();
                }

                if (!useLastAlert() || this.alertType != alertType) {
                    sweetAlertDialog = new SweetAlertDialog(activity.get(), alertType)
                            .showCancelButton(false)
                            .showContentText(false);
                }
                this.alertType = alertType;
                if (!TextUtils.isEmpty(title)) {
                    sweetAlertDialog.setTitleText(title);
                }
                if (!TextUtils.isEmpty(msg)) {
                    sweetAlertDialog.showContentText(false);
                    sweetAlertDialog.setContentText(msg);
                }

                if (yes != null) {
                    sweetAlertDialog.setConfirmText(yes.title);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            yes.call();
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
                }

                if (no != null) {
                    sweetAlertDialog.setCancelText(no.title);
                    sweetAlertDialog.showCancelButton(true);
                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            no.call();
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
                }
                sweetAlertDialog.show();
            }

            @Override
            protected void onNormal() {
                buildDialog(SweetAlertDialog.NORMAL_TYPE);
            }

            @Override
            protected void onSuccess() {
                buildDialog(SweetAlertDialog.SUCCESS_TYPE);
            }

            @Override
            protected void onWarning() {
                buildDialog(SweetAlertDialog.WARNING_TYPE);
            }

            @Override
            protected void onError() {
                buildDialog(SweetAlertDialog.ERROR_TYPE);
            }

            @Override
            protected void onLoading() {
                buildDialog(SweetAlertDialog.WARNING_TYPE);
            }

            @Override
            protected void onProgress(float progress) {
                if (sweetAlertDialog == null || !sweetAlertDialog.isShowing() || sweetAlertDialog.getAlerType() != SweetAlertDialog.PROGRESS_TYPE) {
                    buildDialog(SweetAlertDialog.PROGRESS_TYPE);
                }
                sweetAlertDialog.getProgressHelper().setInstantProgress(progress);
            }

        };

    }

    @Override
    public SToast.Proxy configToast() {
        return new SToast.Proxy() {
            @Override
            public void normal(String msg) {
                Toasty.normal(MyApp.this, msg).show();
            }

            @Override
            public void success(String msg) {
                Toasty.success(MyApp.this, msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(String msg) {
                Toasty.error(MyApp.this, msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void warning(String msg) {
                Toasty.warning(MyApp.this, msg, Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public void configLog() {
        SLog.LOG_SWITCH_V = true;
    }
}
