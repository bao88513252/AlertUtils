package com.example.mylibrary.view.alertcallback.alerttype;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.mylibrary.view.alertcallback.AlertBean;
import com.example.mylibrary.view.alertcallback.AlertUniversalUtils;
import com.example.mylibrary.view.alertcallback.OnReturnValue;

/**
 * 普通的alert
 */

public class OrdinaryAlertType implements AlertType {
    private ShareMethod shareMethod = new ShareMethod();
    private OnReturnValue onReturnValue;
    public OrdinaryAlertType(OnReturnValue onReturnValue){
         this.onReturnValue=onReturnValue;
    }
    @Override
    public void getAlert(AlertBean alertBean) {
        AlertDialog.Builder alertDialog = shareMethod.getAlerts(alertBean);
        alertDialog.setMessage(alertBean.getMessage());
        alertDialog.setPositiveButton(alertBean.getYes(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onReturnValue != null) {
                    onReturnValue.setReturnValue(dialog, which);
                }
            }
        }).setNegativeButton(alertBean.getNo(), null).create().show();
    }
}
