package com.example.mylibrary.view;

import android.content.Context;
import android.widget.Toast;




/**
 * Created by wwb on 2018/5/8/008.
 * for:
 */

public class ToastUnil {
      /**
     * 传入文字
     * */
    private static Toast mToast;
    public static void show(String text, Context context){
        if (mToast == null){
            mToast = Toast.makeText(context, text , Toast.LENGTH_SHORT);
        }else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }

        mToast.show();
    }
}
