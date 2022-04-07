package com.sr.superhelperx.rebound.simple;

import android.annotation.TargetApi;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

/**
 * Created by Hang.Yang on 2018/8/17 13:58.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(11)
public class b extends a {
    private int x;

    public b(View v, Spring s, int x) {
        super(v, s);
        this.x = x;
    }

    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            public void onSpringUpdate(Spring s) {
                b.this.b.setTranslationX((float) SpringUtil.mapValueFromRangeToRange((double)((float)s.getCurrentValue()), 0.0D, 1.0D, 0.0D, (double)b.this.x));
            }
        };
    }
}
