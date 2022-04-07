package com.sr.superhelperx.rebound;

import android.view.View;

import com.facebook.rebound.SpringSystem;
import com.sr.superhelperx.rebound.simple.a;
import com.sr.superhelperx.rebound.simple.b;
import com.sr.superhelperx.rebound.simple.c;
import com.sr.superhelperx.rebound.simple.d;
import com.sr.superhelperx.rebound.simple.e;

/**
 * Created by Hang.Yang on 2018/8/17 13:58.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class g {
    public static final SpringSystem a = SpringSystem.create();

    private g() {
    }

    public static a d(View v, float b) {
        return new c(v, a.createSpring(), b);
    }

    public static a f(View v, float b) {
        return new d(v, a.createSpring(), b);
    }

    public static a c(View v, float b) {
        return new c(v, a.createSpring(), b);
    }

    public static a e(View v, float b) {
        return new d(v, a.createSpring(), b);
    }

    public static a h(View v, int y) {
        return new e(v, a.createSpring(), y);
    }

    public static a b(View v, int x) {
        return new b(v, a.createSpring(), x);
    }
}
