package com.sr.superhelperx.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.sr.superhelperx.R;
import com.sr.superhelperx.dialog.wait.LVCircularRing;

/**
 * Created by Hang.Yang on 2018/8/17 16:40.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class WaitBackdropView extends AppWaitView<LVCircularRing> {
    public WaitBackdropView(Context context) {
        super(context);
    }

    public WaitBackdropView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void start(LVCircularRing l) {
        l.startAnim();
    }

    protected void stop(LVCircularRing l) {
        l.stopAnim();
    }

    protected void layoutBackdrop(LinearLayout l) {
        l.setBackgroundResource(R.drawable.shape_wait_backdrop);
    }
}