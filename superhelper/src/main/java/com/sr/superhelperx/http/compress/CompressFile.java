package com.sr.superhelperx.http.compress;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.util.UtilBitmap;

import java.io.File;

/**
 * Created by Hang.Yang on 2018/8/17 14:59.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class CompressFile extends File {
    private int w;
    private int h;
    private int s;

    public CompressFile(@NonNull String pathName, int size) {
        this(pathName, -1, -1, size);
    }

    public CompressFile(@NonNull String pathName, int width, int height) {
        this(pathName, width, height, -1);
    }

    public CompressFile(@NonNull String pathName, int width, int height, int size) {
        super(pathName);
        this.w = 720;
        this.h = 1280;
        this.s = 2097152;
        this.w = width;
        this.h = height;
        this.s = size;
    }

    public File c() {
        Object f;
        try {
            Bitmap e = null;
            if(this.w == -1 && this.h == -1 && this.s == -1) {
                e = UtilBitmap.compressZoomQuality(this.getPath(), this.w, this.h, this.s);
            } else if(this.w == -1 && this.h == -1) {
                e = UtilBitmap.compressZoom(this.getPath(), this.w, this.h);
            } else if(this.s == -1) {
                e = UtilBitmap.compressQuality(this.getPath(), this.s);
            }

            f = UtilBitmap.saveBitmap(e, AppApplication.INSTANCE.getCameraCropCacheFolder() + System.currentTimeMillis() + ".jpg");
            e.recycle();
            if(f == null) {
                f = this;
            }
        } catch (Exception var3) {
            f = this;
        }

        return (File)f;
    }
}