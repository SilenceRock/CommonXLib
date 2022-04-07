package com.sr.superhelperx.entity;

import android.net.Uri;

/**
 * Created by Hang.Yang on 2018/8/17 15:34.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class AppCrop {
    private boolean h = true;
    private boolean i = true;
    private boolean c = false;
    private int a = 1;
    private int b = 1;
    private int f = 200;
    private int g = 200;
    private Uri e;

    public AppCrop() {
    }

    public AppCrop setCrop(boolean crop) {
        this.c = crop;
        return this;
    }

    public boolean f() {
        return this.c;
    }

    public AppCrop setOriginalUri(Uri originalUri) {
        this.e = originalUri;
        return this;
    }

    public Uri c() {
        return this.e;
    }

    public AppCrop setAspectX(int aspectX) {
        this.a = aspectX;
        return this;
    }

    public int a() {
        return this.a;
    }

    public AppCrop setAspectY(int aspectY) {
        this.b = aspectY;
        return this;
    }

    public int b() {
        return this.b;
    }

    public AppCrop setOutputX(int outputX) {
        this.f = outputX;
        return this;
    }

    public int d() {
        return this.f;
    }

    public AppCrop setOutputY(int outputY) {
        this.g = outputY;
        return this;
    }

    public int e() {
        return this.g;
    }

    public AppCrop setScale(boolean scale) {
        this.h = scale;
        return this;
    }

    public boolean g() {
        return this.h;
    }

    public AppCrop setScaleUpIfNeeded(boolean scaleUpIfNeeded) {
        this.i = scaleUpIfNeeded;
        return this;
    }

    public boolean h() {
        return this.i;
    }
}
