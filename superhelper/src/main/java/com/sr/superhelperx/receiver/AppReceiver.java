package com.sr.superhelperx.receiver;

import android.content.BroadcastReceiver;

/**
 * Created by Hang.Yang on 2018/8/17 14:19.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppReceiver extends BroadcastReceiver {
    public AppReceiver() {
    }

    public String createAction() {
        return this.getClass().toString();
    }
}
