package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:32.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVWifi extends LVBase {
    private float mWidth;
    private float mPadding;
    private Paint mPaint;
    private int signalSize;
    private float mAnimatedValue;

    public LVWifi(Context context) {
        this(context, (AttributeSet)null);
    }

    public LVWifi(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVWifi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mWidth = 0.0F;
        this.mPadding = 0.0F;
        this.signalSize = 4;
        this.mAnimatedValue = 0.9F;
        this.initPaint();
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(0.0F, this.mWidth / (float)this.signalSize);
        this.mPaint.setStrokeWidth(this.mWidth / (float)this.signalSize / 2.0F / 2.0F / 2.0F);
        int scale = (int)((this.mAnimatedValue * (float)this.signalSize - (float)((int)(this.mAnimatedValue * (float)this.signalSize))) * (float)this.signalSize) + 1;
        RectF rect = null;
        float signalRadius = this.mWidth / 2.0F / (float)this.signalSize;

        for(int i = 0; i < this.signalSize; ++i) {
            if(i >= this.signalSize - scale) {
                float radius = signalRadius * (float)i;
                rect = new RectF(radius, radius, this.mWidth - radius, this.mWidth - radius);
                if(i < this.signalSize - 1) {
                    this.mPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(rect, -135.0F, 90.0F, false, this.mPaint);
                } else {
                    this.mPaint.setStyle(Paint.Style.FILL);
                    canvas.drawArc(rect, -135.0F, 90.0F, true, this.mPaint);
                }
            }
        }

        canvas.restore();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
        }

        this.mPadding = (float)this.dip2px(1.0F);
    }

    protected void InitPaint() {
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        this.invalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
    }

    protected int OnStopAnim() {
        this.mAnimatedValue = 0.9F;
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