package com.sr.superhelperx.rebound.simple;

import android.annotation.TargetApi;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

/**
 * Created by Hang.Yang on 2018/8/17 14:00.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(11)
public class e extends a {
    public int y;

    public e(View v, Spring s, int y) {
        super(v, s);
        this.y = y;
    }

    protected SimpleSpringListener f() {
        return new SimpleSpringListener() {
            public void onSpringUpdate(Spring s) {
                e.this.b.setTranslationY((float) SpringUtil.mapValueFromRangeToRange((double)((float)s.getCurrentValue()), 0.0D, 1.0D, 0.0D, (double)e.this.y));
            }
        };
    }
}
