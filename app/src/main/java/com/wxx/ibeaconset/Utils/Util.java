package com.wxx.ibeaconset.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Tangren_ on 2017/4/21 0021.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class Util {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat getFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    /**
     * long型时间转换
     *
     * @param s
     * @return
     */
    public static String long2Date(long s) {
        Date date = new Date(s * 1000L);
        return format.format(date);
    }

    /**
     * 设备ID激活状态
     *
     * @param status
     * @return
     */
    public static String getStatus(int status) {
        if (status == 0)
            return "未激活";
        else if (status == 1)
            return "已激活";
        else return "未知";
    }


    /**
     * 得到当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        return getFormat.format(new Date());
    }

    /**
     * 得到图片路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getImagePath(final Context context, final Uri uri) {
        if (uri == null) return null;

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * bitmap转换成inputStream
     *
     * @param bm
     * @param quality
     * @return
     */
    public static InputStream Bitmap2InputStream(Bitmap bm, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }


}
