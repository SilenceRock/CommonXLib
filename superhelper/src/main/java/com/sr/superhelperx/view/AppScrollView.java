package com.sr.superhelperx.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Hang.Yang on 2018/8/17 16:31.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppScrollView extends ScrollView {
    private GestureDetector gestureDetector;

    @TargetApi(3)
    public AppScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            public boolean onSingleTapUp(MotionEvent arg0) {
                return false;
            }

            public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
                return Math.abs(arg3) >= Math.abs(arg2);
            }

            public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
                return false;
            }

            public boolean onDown(MotionEvent arg0) {
                return false;
            }

            public void onShowPress(MotionEvent arg0) {
            }

            public void onLongPress(MotionEvent arg0) {
            }
        });
        this.setFadingEdgeLength(0);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && this.gestureDetector.onTouchEvent(ev);
    }
}