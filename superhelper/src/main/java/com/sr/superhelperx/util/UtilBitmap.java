package com.sr.superhelperx.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Hang.Yang on 2018/8/17 16:17.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(16)
public final class UtilBitmap {
    private UtilBitmap() {
    }

    public static Bitmap compressZoomQuality(String imagePath, int width, int height, int size) throws Exception {
        return compressQuality(compressZoom(imagePath, width, height), size);
    }

    public static String toBase64ByPath(String imagePath, int width, int height, int size) throws Exception {
        Bitmap b = compressZoomQuality(imagePath, width, height, size);
        String a = toBase64(b);
        recycle(b);
        return a;
    }

    public static String toBase64(Bitmap bitmap) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
        byte[] t = b.toByteArray();
        b.close();
        return Base64.encodeToString(t, 0);
    }

    public static Bitmap compressQuality(String imagePath, int size) throws Exception {
        return compressQuality(BitmapFactory.decodeFile(imagePath), size);
    }

    public static Bitmap compressQuality(Bitmap bitmap, int size) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);

        for(int i = 100; b.toByteArray().length / 1024 > size; i -= 10) {
            b.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, b);
        }

        recycle(bitmap);
        ByteArrayInputStream i1 = new ByteArrayInputStream(b.toByteArray());
        b.close();
        Bitmap t = BitmapFactory.decodeStream(i1, (Rect)null, (BitmapFactory.Options)null);
        i1.close();
        return t;
    }

    public static Bitmap compressZoom(String imagePath, int width, int height) throws Exception {
        BufferedInputStream b = new BufferedInputStream(new FileInputStream(new File(imagePath)));
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(b, (Rect)null, o);
        b.close();

        while(o.outWidth >> o.inSampleSize > width || o.outHeight >> o.inSampleSize > height) {
            ++o.inSampleSize;
        }

        b = new BufferedInputStream(new FileInputStream(new File(imagePath)));
        o.inSampleSize = (int)Math.pow(2.0D, (double)o.inSampleSize);
        o.inJustDecodeBounds = false;
        Bitmap m = BitmapFactory.decodeStream(b, (Rect)null, o);
        b.close();
        return m;
    }

    public static File saveBitmap(Bitmap bitmap, String fileName) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
        FileOutputStream f = new FileOutputStream(new File(fileName));
        f.write(b.toByteArray());
        b.close();
        f.close();
        return new File(fileName);
    }

    public static void recycle(Bitmap bitmap) {
        if(bitmap != null) {
            bitmap.recycle();
        }

    }
}

