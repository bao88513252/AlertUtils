package com.example.mylibrary.view.alertcallback.alerttype;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.mylibrary.view.alertcallback.AlertBean;
import com.example.mylibrary.view.alertcallback.AlertUniversalUtils;
import com.example.mylibrary.view.alertcallback.OnReturnValue;

/**
 * 所有文字的Alert
 */
public class AllItemAlertType implements AlertType {
    private ShareMethod shareMethod = new ShareMethod();
    private OnReturnValue onReturnValue;
    public AllItemAlertType (OnReturnValue onReturnValue) {
        this.onReturnValue = onReturnValue;
    }
    @Override
    public void getAlert(AlertBean alertBean) {
        final AlertDialog.Builder alertDialog = shareMethod.getAlerts(alertBean);
        alertDialog.setItems(alertBean.getName(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onReturnValue != null) {
                    onReturnValue.setReturnValue(dialog, which);
                }
            }
        }).create().show();
    }
}
