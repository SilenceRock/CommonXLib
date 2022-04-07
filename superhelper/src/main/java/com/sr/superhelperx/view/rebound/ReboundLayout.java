package com.sr.superhelperx.view.rebound;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.sr.superhelperx.rebound.simple.a;
import com.sr.superhelperx.rebound.simple.b;
import com.sr.superhelperx.rebound.g;

/**
 * Created by Hang.Yang on 2018/8/17 16:41.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class ReboundLayout extends LinearLayout {
    private a a;

    public ReboundLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.a = new b(this, g.a.createSpring(), 1000);
    }

    public void start() {
        this.a.c(1.0D);
        this.a.d(0.0D);
        this.setVisibility(0);
    }

    public void stop() {
        this.a.c(0.0D);
    }
}

