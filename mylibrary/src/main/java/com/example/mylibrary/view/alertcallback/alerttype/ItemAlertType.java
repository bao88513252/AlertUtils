package com.example.mylibrary.view.alertcallback.alerttype;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.mylibrary.view.alertcallback.AlertBean;
import com.example.mylibrary.view.alertcallback.AlertUniversalUtils;

/**
 * 拍照的alert
 */
public class ItemAlertType implements AlertType {
    private ShareMethod shareMethod = new ShareMethod();
    @Override
    public void getAlert(final AlertBean alertBean) {
        final AlertDialog.Builder alertDialog = shareMethod.getAlerts(alertBean);
        alertDialog.setItems(alertBean.getName(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (alertBean == null) {
                    new NullPointerException(alertBean + "空指针");
                    return;
                }
                if (alertBean.getState()) {//调用系统的
                    shareMethod.getSettingPhoto(alertBean, which);
                } else {
                    getCustom();//调用第三方的拍照
                }
            }
        }).create().show();
    }

    /**
     * 调用第三方的相册
     */
    private void getCustom() {
    }
}
