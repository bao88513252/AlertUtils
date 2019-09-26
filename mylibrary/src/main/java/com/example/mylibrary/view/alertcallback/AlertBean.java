package com.example.mylibrary.view.alertcallback;

import android.content.Context;

import com.example.mylibrary.view.compresspicturesImageutil.FileUtis;

/**
 * Created by wwb on 2019/9/25/025.
 * for:
 */
public class AlertBean {
    private String title = "标题";//显示的标题
    private String message = "内容";//显示的内容
    private String[] name = {"文字", "文字"};//显示的文字
    private String yes = "确定";//按钮的文字
    private String no = "取消";//按钮的文字
    private Context context;
    private boolean state = true;//弹窗的状态(可以不用)
    private int states;//相机和弹窗的状态
    private FileUtis fileUtis;
    private String urlName;

    public AlertBean() {//初始化弹窗

    }

    public AlertBean(FileUtis.OnFileUrl onFileUrl) {//初始化弹窗使用相机和选择相片的时候使用
        fileUtis = initializationFileUtis(onFileUrl);
    }

    public AlertBean setUrlName(String urlName) {
        this.urlName = urlName;
        return this;
    }

    public AlertBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlertBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public AlertBean setName(String[] name) {
        this.name = name;
        return this;
    }

    public AlertBean setYes(String yes) {
        this.yes = yes;
        return this;
    }

    public AlertBean setNo(String no) {
        this.no = no;
        return this;
    }

    public AlertBean setContext(Context context) {
        this.context = context;
        return this;
    }

    public AlertBean setState(boolean state) {
        this.state = state;
        return this;
    }

    public AlertBean setStates(int states) {
        this.states = states;
        return this;
    }

    public String getUrlName() {
        return urlName;
    }


    public String getTitle() {
        return title;
    }


    public String getMessage() {
        return message;
    }


    public String[] getName() {
        return name;
    }


    public String getYes() {
        return yes;
    }


    public String getNo() {
        return no;
    }


    public Context getContext() {
        return context;
    }


    public FileUtis getFileUtis() {
        return fileUtis;
    }

    public boolean isState() {
        return state;
    }

    public boolean getState() {
        return state;
    }

    public int getStates() {
        return states;
    }

    /**
     * 初始化
     */
    public FileUtis initializationFileUtis(FileUtis.OnFileUrl onFileUrl) {
        return FileUtis.getFileUtis()
                .setOnFileUrl(onFileUrl);
    }
}
