package com.sr.superhelperx.rebound.simple;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/**
 * Created by Hang.Yang on 2018/8/17 13:58.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class a {
    public View b;
    public Spring a;

    public a(View b, Spring a) {
        this.b = b;
        this.a = a;
        this.a.addListener(this.f());
    }

    public void d(double s) {
        this.a.setEndValue(s);
    }

    public void c(double s) {
        this.a.setCurrentValue(s);
    }

    protected abstract SimpleSpringListener f();
}
