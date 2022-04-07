package com.sr.superhelperx.http.base64;

import android.graphics.Bitmap;

import com.sr.superhelperx.util.UtilBitmap;

/**
 * Created by Hang.Yang on 2018/8/17 14:57.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AsyBitmapBase64 implements AsyBase64 {
    private Bitmap b;
    private int s;

    public AsyBitmapBase64(Bitmap bitmap, int size) {
        this.b = bitmap;
        this.s = size;
    }

    public String toBase64() {
        try {
            return this.s > 0? UtilBitmap.toBase64(UtilBitmap.compressQuality(this.b, this.s)):UtilBitmap.toBase64(this.b);
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }
}