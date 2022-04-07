package com.sr.superhelperx.view.asy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Hang.Yang on 2018/8/17 16:37.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

abstract class b extends BroadcastReceiver {
    static final String a = "vegsdfe";

    b() {
    }

    public void onReceive(Context context, Intent i) {
        this.c(i.getIntExtra("vegsdfe", 0));
    }

    abstract void c(int var1);
}
