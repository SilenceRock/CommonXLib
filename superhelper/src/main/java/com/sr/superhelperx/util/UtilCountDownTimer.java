package com.sr.superhelperx.util;

import android.os.CountDownTimer;

/**
 * Created by Hang.Yang on 2018/8/17 16:18.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class UtilCountDownTimer extends CountDownTimer {
    public UtilCountDownTimer(long millisInFuture) {
        super(millisInFuture, 1000L);
        this.start();
    }

    public void onTick(long l) {
        this.onTick(l * 1000L);
    }

    public abstract void onTick(int var1);
}