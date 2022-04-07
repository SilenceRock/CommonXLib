package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Hang.Yang on 2018/8/17 15:26.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class LVBase extends View {
    public ValueAnimator valueAnimator;

    public LVBase(Context context) {
        this(context, (AttributeSet)null);
    }

    public LVBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.InitPaint();
    }

    public void startAnim() {
        this.stopAnim();
        this.startViewAnim(0.0F, 1.0F, 500L);
    }

    public void startAnim(int time) {
        this.stopAnim();
        this.startViewAnim(0.0F, 1.0F, (long)time);
    }

    public void stopAnim() {
        if(this.valueAnimator != null) {
            this.clearAnimation();
            this.valueAnimator.setRepeatCount(0);
            this.valueAnimator.cancel();
            this.valueAnimator.end();
            if(this.OnStopAnim() == 0) {
                this.valueAnimator.setRepeatCount(0);
                this.valueAnimator.cancel();
                this.valueAnimator.end();
            }
        }

    }

    private ValueAnimator startViewAnim(float startF, float endF, long time) {
        this.valueAnimator = ValueAnimator.ofFloat(new float[]{startF, endF});
        this.valueAnimator.setDuration(time);
        this.valueAnimator.setInterpolator(new LinearInterpolator());
        this.valueAnimator.setRepeatCount(this.SetAnimRepeatCount());
        if(1 == this.SetAnimRepeatMode()) {
            this.valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        } else if(2 == this.SetAnimRepeatMode()) {
            this.valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        }

        this.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LVBase.this.OnAnimationUpdate(valueAnimator);
            }
        });
        this.valueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                LVBase.this.OnAnimationRepeat(animation);
            }
        });
        if(!this.valueAnimator.isRunning()) {
            this.AinmIsRunning();
            this.valueAnimator.start();
        }

        return this.valueAnimator;
    }

    protected abstract void InitPaint();

    protected abstract void OnAnimationUpdate(ValueAnimator var1);

    protected abstract void OnAnimationRepeat(Animator var1);

    protected abstract int OnStopAnim();

    protected abstract int SetAnimRepeatMode();

    protected abstract int SetAnimRepeatCount();

    protected abstract void AinmIsRunning();

    public int dip2px(float dpValue) {
        float scale = this.getContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public float getFontlength(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return (float)rect.width();
    }

    public float getFontHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return (float)rect.height();
    }

    public float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }
}
