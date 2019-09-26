package com.example.mylibrary.view.alertcallback;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.mylibrary.view.ToastUnil;
import com.example.mylibrary.view.alertcallback.alerttype.AlertType;

/**
 * Created by wwb on 2019/9/25/025.
 * for:
 */

public class AlertUniversalUtils {
    private AlertBean alertBean;
    private AlertType alertType;
    public AlertUniversalUtils setAlertBean(AlertBean alertBean){
        this.alertBean=alertBean;
        return this;
    }
    public AlertUniversalUtils setAlertType(AlertType alertType){
            this.alertType=alertType;
            return this;
    }
    public void getAllAlert(){
          alertType.getAlert(alertBean);
    }

    /**
     * 自定义弹窗
     */
    protected void getMyView() {

    }

}
