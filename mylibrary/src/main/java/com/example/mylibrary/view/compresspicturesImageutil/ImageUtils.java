package com.example.mylibrary.view.compresspicturesImageutil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.example.mylibrary.view.ToastUnil;
import com.example.mylibrary.view.alertcallback.AlertBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wwb on 2019/9/25/025.
 * for:
 */

public class ImageUtils {
    private static ImageUtils imageUtils;
    private String url;
    private int state;
    private FileOutputStream fos = null;
    private String timestr = "";
    private Uri uri = null;
    private Activity activity;
    private FileUtis fileUtis;
    private AlertBean alertBean;

    public static ImageUtils getImageUtils() {
        if (imageUtils == null) {
            synchronized (ImageUtils.class) {
                if (imageUtils == null) {
                    imageUtils = new ImageUtils();
                }
            }
        }
        return imageUtils;
    }

    public ImageUtils setAlertBean(AlertBean alertBean) {
        this.alertBean = alertBean;
        return this;
    }

    public ImageUtils setFileUtis(FileUtis fileUtis) {
        this.fileUtis = fileUtis;
        return this;
    }

    public ImageUtils setUri(Uri uri) {
        this.uri = uri;
        return this;
    }

    public ImageUtils setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public ImageUtils setUrl(String url) {
        this.url = url;
        return this;
    }

    public ImageUtils setState(int state) {
        this.state = state;
        return this;
    }

    public void start() {
        compress(url, state);
    }

    /**
     * 压缩图片
     *
     * @param url
     * @param start
     * @return
     */
    public String compress(final String url, final int start) {
        File files = new File(url);
        Bitmap bitmap = BitmapFactory.decodeFile(files.getAbsolutePath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        File dirFile = new File(Environment.getExternalStorageDirectory() + "/" + "imgs");
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = null;
        if (start == 1) {
            file = new File(dirFile, timestr + "imgs.jpeg");
        } else {
            file = new File(url);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getUrl(file.getAbsolutePath());
        return file.getAbsolutePath();
    }

    private void getUrl(String pathUrl) {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String timestr = String.valueOf(time);
        String timeyear = new SimpleDateFormat("yyyy-MM").format(new Date(System.currentTimeMillis()));
        String alyUrl = "";
        if (alertBean.getUrlName() == null) {
            alyUrl = "propertymanage" + "/" + "Nursing" + "/" + timeyear + "/" + timestr + ".jpeg";
        } else {
            alyUrl = alertBean.getUrlName() + "/" + "Nursing" + "/" + timeyear + "/" + timestr + ".jpeg";
        }
        if (uri == null) {
            ToastUnil.show("没有找到路径", activity);
            return;
        }
        fileUtis.setImage(alyUrl, pathUrl);
    }
}
