package com.sr.superhelperx.dialog;

import android.content.Context;

import com.sr.superhelperx.dialog.wait.LVCircularRing;

/**
 * Created by Hang.Yang on 2018/8/17 15:25.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class WaitDialog extends AppWaitDialog<LVCircularRing> {
    public WaitDialog(Context context) {
        super(context);
    }

    void start(LVCircularRing l) {

        l.startAnim();
    }

    void stop(LVCircularRing l) {
        l.stopAnim();
//        l.startAnim();
    }
}
