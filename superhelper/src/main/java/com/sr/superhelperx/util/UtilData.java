package com.sr.superhelperx.util;

import android.annotation.TargetApi;
import android.os.Environment;

import com.sr.superhelperx.app.AppApplication;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Hang.Yang on 2018/8/17 16:18.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilData {
    public UtilData() {
    }

    @TargetApi(8)
    public static String getTotalCacheSize() throws Exception {
        long c = 0L;
        if(Environment.getExternalStorageState().equals("mounted")) {
            c += getFolderSize(AppApplication.AINSTANCE.getExternalCacheDir());
        }

        return getFormatSize((double)(c + getFolderSize(new File(AppApplication.INSTANCE.getAppFolder()))));
    }

    public static void clearAllCache() {
        deleteDir(new File(AppApplication.INSTANCE.getImageCacheFolder()));
        deleteDir(new File(AppApplication.INSTANCE.getCameraCropCacheFolder()));
        deleteDir(new File(AppApplication.INSTANCE.getJsonCacheFolder()));
        deleteDir(new File(AppApplication.INSTANCE.getVideoCacheFolder()));
        deleteDir(new File(AppApplication.INSTANCE.getFileCacheFolder()));
        if(Environment.getExternalStorageState().equals("mounted")) {
            deleteDir(AppApplication.AINSTANCE.getExternalCacheDir());
        }
    }

    private static void deleteDir(File dir) {
        if(dir != null && dir.isDirectory()) {
            String[] c = dir.list();

            for(int i = 0; i < c.length; ++i) {
                (new File(dir, c[i])).delete();
            }
        }

    }

    public static long getFolderSize(File file) throws Exception {
        long s = 0L;

        try {
            File[] e = file.listFiles();

            for(int i = 0; i < e.length; ++i) {
                if(e[i].isDirectory()) {
                    s += getFolderSize(e[i]);
                } else {
                    s += e[i].length();
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return s;
    }

    public static String getFormatSize(double size) {
        double k = size / 1024.0D;
        if(k < 1.0D) {
            return "0K";
        } else {
            double m = k / 1024.0D;
            if(m < 1.0D) {
                return (new BigDecimal(Double.toString(k))).setScale(2, 4).toPlainString() + "KB";
            } else {
                double g = m / 1024.0D;
                if(g < 1.0D) {
                    return (new BigDecimal(Double.toString(m))).setScale(2, 4).toPlainString() + "MB";
                } else {
                    double t = g / 1024.0D;
                    return t < 1.0D?(new BigDecimal(Double.toString(g))).setScale(2, 4).toPlainString() + "GB":(new BigDecimal(t)).setScale(2, 4).toPlainString() + "TB";
                }
            }
        }
    }
}
