package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:29.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVEatBeans extends LVBase {
    private Paint mPaint;
    private Paint mPaintEye;
    private float mWidth = 0.0F;
    private float mHigh = 0.0F;
    private float mPadding = 5.0F;
    private float eatErWidth = 60.0F;
    private float eatErPositonX = 0.0F;
    int eatSpeed = 5;
    private float beansWidth = 10.0F;
    private float mAngle = 34.0F;
    private float eatErStrtAngle;
    private float eatErEndAngle;

    public LVEatBeans(Context context) {
        super(context);
        this.eatErStrtAngle = this.mAngle;
        this.eatErEndAngle = 360.0F - 2.0F * this.eatErStrtAngle;
    }

    public LVEatBeans(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.eatErStrtAngle = this.mAngle;
        this.eatErEndAngle = 360.0F - 2.0F * this.eatErStrtAngle;
    }

    public LVEatBeans(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.eatErStrtAngle = this.mAngle;
        this.eatErEndAngle = 360.0F - 2.0F * this.eatErStrtAngle;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = (float)this.getMeasuredWidth();
        this.mHigh = (float)this.getMeasuredHeight();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float eatRightX = this.mPadding + this.eatErWidth + this.eatErPositonX;
        RectF rectF = new RectF(this.mPadding + this.eatErPositonX, this.mHigh / 2.0F - this.eatErWidth / 2.0F, eatRightX, this.mHigh / 2.0F + this.eatErWidth / 2.0F);
        canvas.drawArc(rectF, this.eatErStrtAngle, this.eatErEndAngle, true, this.mPaint);
        canvas.drawCircle(this.mPadding + this.eatErPositonX + this.eatErWidth / 2.0F, this.mHigh / 2.0F - this.eatErWidth / 4.0F, this.beansWidth / 2.0F, this.mPaintEye);
        int beansCount = (int)((this.mWidth - this.mPadding * 2.0F - this.eatErWidth) / this.beansWidth / 2.0F);

        for(int i = 0; i < beansCount; ++i) {
            float x = (float)(beansCount * i) + this.beansWidth / 2.0F + this.mPadding + this.eatErWidth;
            if(x > eatRightX) {
                canvas.drawCircle(x, this.mHigh / 2.0F, this.beansWidth / 2.0F, this.mPaint);
            }
        }

    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-1);
        this.mPaintEye = new Paint();
        this.mPaintEye.setAntiAlias(true);
        this.mPaintEye.setStyle(Paint.Style.FILL);
        this.mPaintEye.setColor(-16777216);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.postInvalidate();
    }

    public void setEyeColor(int color) {
        this.mPaintEye.setColor(color);
        this.postInvalidate();
    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        float mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        this.eatErPositonX = (this.mWidth - 2.0F * this.mPadding - this.eatErWidth) * mAnimatedValue;
        this.eatErStrtAngle = this.mAngle * (1.0F - (mAnimatedValue * (float)this.eatSpeed - (float)((int)(mAnimatedValue * (float)this.eatSpeed))));
        this.eatErEndAngle = 360.0F - this.eatErStrtAngle * 2.0F;
        this.invalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
    }

    protected int OnStopAnim() {
        this.eatErPositonX = 0.0F;
        this.postInvalidate();
        return 1;
    }

    protected int SetAnimRepeatMode() {
        return 1;
    }

    protected void AinmIsRunning() {
    }

    protected int SetAnimRepeatCount() {
        return -1;
    }
}
