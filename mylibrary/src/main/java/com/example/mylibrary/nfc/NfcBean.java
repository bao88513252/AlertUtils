package com.example.mylibrary.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.nfc.NfcAdapter;

/**
 * Created by wwb on 2019/7/8/008.
 * for:
 */

public class NfcBean {
    public Activity activitys;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;

    public NfcBean(Activity activity) {
        this.activitys = activity;
    }

    public Activity getActivity() {
        return activitys;
    }

    public NfcAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(NfcAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public PendingIntent getmPendingIntent() {
        return mPendingIntent;
    }

    public void setmPendingIntent(PendingIntent mPendingIntent) {
        this.mPendingIntent = mPendingIntent;
    }
}
