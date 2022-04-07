package com.sr.superhelperx.view.progress;

import android.content.Context;
import android.util.AttributeSet;

import com.sr.superhelperx.dialog.wait.LVCircularRing;

/**
 * Created by Hang.Yang on 2018/8/17 16:41.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class WaitView extends AppWaitView<LVCircularRing> {
    public WaitView(Context context) {
        super(context);
    }

    public WaitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void start(LVCircularRing l) {
        l.startAnim();
    }

    protected void stop(LVCircularRing l) {
        l.stopAnim();
    }
}