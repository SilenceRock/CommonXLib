package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:28.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVCircularJump extends LVBase {
    private Paint mPaint;
    private float mWidth = 0.0F;
    private float mHigh = 0.0F;
    private float mMaxRadius = 6.0F;
    private int circularCount = 4;
    private float mAnimatedValue = 0.0F;
    private int mJumpValue = 0;

    public LVCircularJump(Context context) {
        super(context);
    }

    public LVCircularJump(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVCircularJump(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = (float)this.getMeasuredWidth();
        this.mHigh = (float)this.getMeasuredHeight();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float circularX = this.mWidth / (float)this.circularCount;

        for(int i = 0; i < this.circularCount; ++i) {
            if(i == this.mJumpValue % this.circularCount) {
                canvas.drawCircle((float)i * circularX + circularX / 2.0F, this.mHigh / 2.0F - this.mHigh / 2.0F * this.mAnimatedValue, this.mMaxRadius, this.mPaint);
            } else {
                canvas.drawCircle((float)i * circularX + circularX / 2.0F, this.mHigh / 2.0F, this.mMaxRadius, this.mPaint);
            }
        }

    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-1);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.postInvalidate();
    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        if((double)this.mAnimatedValue > 0.5D) {
            this.mAnimatedValue = 1.0F - this.mAnimatedValue;
        }

        this.invalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
        ++this.mJumpValue;
    }

    protected int SetAnimRepeatMode() {
        return 1;
    }

    protected int OnStopAnim() {
        this.mAnimatedValue = 0.0F;
        this.mJumpValue = 0;
        return 0;
    }

    protected void AinmIsRunning() {
    }

    protected int SetAnimRepeatCount() {
        return -1;
    }
}
