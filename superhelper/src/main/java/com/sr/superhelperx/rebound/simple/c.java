package com.sr.superhelperx.rebound.simple;

import android.annotation.TargetApi;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/**
 * Created by Hang.Yang on 2018/8/17 13:59.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(11)
public class c extends a {
    private float a;

    public c(View v, Spring s, float a) {
        super(v, s);
        this.a = a;
    }

    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            private float v;
            private float c;

            public void onSpringUpdate(Spring s) {
                this.v = (float)s.getCurrentValue();
                this.c = 1.0F - this.v * c.this.a;
                c.this.b.setScaleX(this.c);
                c.this.b.setScaleY(this.c);
            }
        };
    }
}
