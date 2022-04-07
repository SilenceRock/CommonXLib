package com.sr.superhelperx.http.base64;

import com.sr.superhelperx.util.UtilBitmap;

/**
 * Created by Hang.Yang on 2018/8/17 14:57.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AsyImageBase64 implements AsyBase64 {
    private int w;
    private int h;
    private int s;
    private String i;

    public AsyImageBase64(String imagePath, int width, int height, int size) {
        this.i = imagePath;
        this.w = width;
        this.h = height;
        this.s = size;
    }

    public String toBase64() {
        try {
            return UtilBitmap.toBase64ByPath(this.i, this.w, this.h, this.s);
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }
}
