package com.example.mylibrary.nfc;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.widget.Toast;

/**
 * Created by wwb on 2019/7/8/008.
 * for:
 */

public class NfcUtils {
    /*
   初始化NFC
    */
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String cardId;//卡片ID
    private NfcBean nfcBean;
    public NfcBean showNfcAdapter(NfcBean nfcBean) {
        getPendingIntent(nfcBean);
        nfcBean.setmAdapter(mAdapter);
        nfcBean.setmPendingIntent(mPendingIntent);
        return nfcBean;
    }

    /**
     * 初始化NFC
     * @param nfcBean
     */
    public void getPendingIntent(NfcBean nfcBean) {
        this.nfcBean=nfcBean;
        Intent nfcIntent = new Intent(nfcBean.getActivity(), nfcBean.getActivity().getClass());
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mPendingIntent = PendingIntent.getActivity(nfcBean.getActivity(), 0, nfcIntent, 0);
        mAdapter = NfcAdapter.getDefaultAdapter(nfcBean.getActivity());
        if (mAdapter == null) {
            Toast.makeText(nfcBean.getActivity(), "NFC feature is supported on this device.", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    //启用Nfc的类
    @SuppressLint("MissingPermission")
    public  void getEnable(){
         mAdapter.enableForegroundDispatch(nfcBean.getActivity(), mPendingIntent, null, null);
    }

    //获取解析卡号的类
    @SuppressWarnings("unchecked")
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    public String getCard(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        //获取 Tag 读取 ID 得到字节数组  转字符串 转码  得到卡号
        Long cardNo = Long.parseLong(ByteArrayTohexHepler.flipHexStr(ByteArrayTohexHepler.ByteArrayToHexString(tag.getId())), 16);
        if (cardNo.toString().getBytes().length == 10) {
            cardId = cardNo.toString();
        } else {
            cardId = "0" + cardNo.toString();
        }
        return cardId;
    }

}
