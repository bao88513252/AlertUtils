package com.example.mylibrary.view.compresspicturesImageutil;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.mylibrary.view.ToastUnil;
import com.example.mylibrary.view.alertcallback.AlertBean;

import java.io.File;

/**
 * 拍照工具类
 */

public class FileUtis extends OnImageCallBack {
    private Activity activity;
    private OnFileUrl onFileUrl;
    private Uri uri = null;
    private AlertBean alertBean;
    private String timestr;
    private static  FileUtis fileUtis;
    /**
     * 拍照
     */
    private String picPath;
     public static FileUtis getFileUtis() {
        if (fileUtis== null) {
            synchronized (FileUtis.class) {
                if (fileUtis == null) {
                    fileUtis = new FileUtis();
                }
            }
        }
        return fileUtis;
    }
    public  FileUtis setAlertBean(AlertBean alertBean){
         this.alertBean=alertBean;
         this.activity=(Activity)alertBean.getContext();
         return this;
    }


    public FileUtis setOnFileUrl(OnFileUrl onFileUrl) {
        this.onFileUrl = onFileUrl;
        return this;
    }

    public void getTakePic(){//调用拍照;
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                File dir = new File(Environment.getExternalStorageDirectory() + "/" + "imgs");
                if (!dir.exists()) //noinspection ResultOfMethodCallIgnored
                    dir.mkdirs();
                long time = System.currentTimeMillis();
                timestr = String.valueOf(time);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(dir, timestr + "imgs.jpeg");//localTempImgDir和localTempImageFileName是自己定义的名字
                if (Build.VERSION.SDK_INT >= 24) {
                    uri = FileProvider.getUriForFile(activity, "com.example.propertymanage.fileprovider", f);
                } else {
                    uri = Uri.fromFile(f);
                }
                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                activity.startActivityForResult(intent, 0);
            } catch (ActivityNotFoundException e) {
                ToastUnil.show("没有找到储存目录", activity);
            }
        } else {
            ToastUnil.show("没有储存卡", activity);
        }
    }

    /**
     * 选择相册
     */
    public void getChoosePic() {//调用android的图库
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, 1);
    }

    /**
     * 处理拍照
     */
    public void getStartActivityForResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0://拍照的回调
                if (resultCode == Activity.RESULT_OK) {
                    File f = new File(Environment.getExternalStorageDirectory() + "/" + "imgs" + "/" + timestr + "imgs.jpeg");
                    //开始压缩图片
                    compress(f.getAbsolutePath(),1);
                }
                break;
            case 1://相册的回调
                if (resultCode == Activity.RESULT_OK) {
                    uri = data.getData();
                    String url = getImagePathFromUri(activity, uri);
                    //开始压缩图片
                    compress(url,2);
                }
                break;
        }
    }

    //开始压缩图片
    public void compress(String url,int state) {
        ImageUtils.getImageUtils()
                .setUrl(url)
                .setActivity(activity)
                .setFileUtis(this)
                .setUri(uri)
                .setAlertBean(alertBean)
                .setState(state)
                .start();
    }

    /**
     * @param alyUrl//命名的路径
     * @param pathUrl//本地的路径
     */
    @Override
    void setImage(String alyUrl, String pathUrl) {
           onFileUrl.setStringUrl(alyUrl,pathUrl);
    }

    public interface OnFileUrl {
        void setStringUrl(String aliyun, String path);
    }


    /**
     * @param context
     * @param picUri  图片URI
     * @return 选择的图片路径
     */
    private String getImagePathFromUri(final Context context, Uri picUri) {
        // 选择的图片路径
        String selectPicPath = null;
        final String scheme = picUri.getScheme();
        //noinspection ConstantConditions
        if (picUri != null && scheme != null) {
            if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
                // content://开头的uri
                Cursor cursor = activity.getContentResolver().query(picUri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    // 取出文件路径
                    selectPicPath = cursor.getString(columnIndex);
                    // Android 4.1 更改了SD的目录，sdcard映射到/storage/sdcard0
                    if (!selectPicPath.startsWith("/storage") && !selectPicPath.startsWith("/mnt")) {
                        // 检查是否有"/mnt"前缀
                        selectPicPath = "/mnt" + selectPicPath;
                    }
                    //关闭游标
                    cursor.close();
                }
            } else if (scheme.equals(ContentResolver.SCHEME_FILE)) {// file:///开头的uri
                // 替换file://
                selectPicPath = picUri.toString().replace("file://", "");
                int index = selectPicPath.indexOf("/sdcard");
                selectPicPath = index == -1 ? selectPicPath : selectPicPath.substring(index);
                if (!selectPicPath.startsWith("/mnt")) {
                    // 加上"/mnt"头
                    selectPicPath = "/mnt" + selectPicPath;
                }
            }
        }
        return selectPicPath;
    }

}
