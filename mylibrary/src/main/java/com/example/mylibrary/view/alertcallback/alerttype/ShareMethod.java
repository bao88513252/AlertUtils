package com.example.mylibrary.view.alertcallback.alerttype;

import android.support.v7.app.AlertDialog;

import com.example.mylibrary.view.ToastUnil;
import com.example.mylibrary.view.alertcallback.AlertBean;

/**
 * Created by wwb on 2019/9/25/025.
 * for:
 */

public class ShareMethod {
    public AlertDialog.Builder getAlerts(AlertBean alertBean) {
        AlertDialog.Builder alert = new AlertDialog.Builder(alertBean.getContext());
        alert.setTitle(alertBean.getTitle());
        return alert;
    }
    /**
     * 方便在自定义的View中调用拍照
     * @param alertBean
     * @param which
     */
    public void getSettingPhoto(AlertBean alertBean, int which) {
        if (alertBean==null){
            new NullPointerException("alertBean不能为空");
            return;
        }
        if (alertBean.getState()) {//alertBean.getState()==true使用系统相机
            if (alertBean.getFileUtis() != null) {
                alertBean.getFileUtis().setAlertBean(alertBean);
                if (which == 0) {//拍照
                    alertBean.getFileUtis().getTakePic();
                } else {//相册
                    alertBean.getFileUtis().getChoosePic();
                }
            } else {
                ToastUnil.show("请使用AlertBean带有参数的构造方法", alertBean.getContext());
            }
        }
    }
}
