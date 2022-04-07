package com.sr.superhelperx.rebound.simple;

import android.annotation.TargetApi;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/**
 * Created by Hang.Yang on 2018/8/17 14:00.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(11)
public class d extends a {
    private float a;

    public d(View v, Spring s, float a) {
        super(v, s);
        this.a = a;
    }

    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            private float v;
            private float c;

            public void onSpringUpdate(Spring s) {
                this.v = (float)s.getCurrentValue();
                this.c = 1.0F - this.v * d.this.a;
                d.this.b.setAlpha(this.c);
            }
        };
    }
}
