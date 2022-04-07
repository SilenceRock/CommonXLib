package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:30.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVGears extends LVBase {
    private float mWidth = 0.0F;
    private Paint mPaint;
    private Paint mPaintWheelBig;
    private Paint mPaintWheelSmall;
    private Paint mPaintAxle;
    private Paint mPaintCenter;
    private float mPadding = 0.0F;
    private float mPaintCenterRadius;
    private float mWheelSmallLength;
    private float mWheelBigLength;
    private int mWheelSmallSpace = 8;
    private int mWheelBigSpace = 6;
    ValueAnimator valueAnimator = null;
    float mAnimatedValue = 0.0F;

    public LVGears(Context context) {
        super(context);
    }

    public LVGears(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVGears(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
        }

    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(this.mWidth / 2.0F, this.mWidth / 2.0F, this.mWidth / 2.0F - this.mPadding, this.mPaint);
        canvas.drawCircle(this.mWidth / 2.0F, this.mWidth / 2.0F, this.mWidth / 4.0F, this.mPaint);
    }

    private void drawAxleAndCenter(Canvas canvas) {
        for(int i = 0; i < 3; ++i) {
            float x2 = (float)((double)(this.mWidth / 2.0F - this.mPadding) * Math.cos((double)(i * 120) * 3.141592653589793D / 180.0D));
            float y2 = (float)((double)(this.mWidth / 2.0F - this.mPadding) * Math.sin((double)(i * 120) * 3.141592653589793D / 180.0D));
            float x = (float)((double)this.mPaintCenterRadius * Math.cos((double)(i * 120) * 3.141592653589793D / 180.0D));
            float y = (float)((double)this.mPaintCenterRadius * Math.sin((double)(i * 120) * 3.141592653589793D / 180.0D));
            canvas.drawLine(this.mWidth / 2.0F - x, this.mWidth / 2.0F - y, this.mWidth / 2.0F - x2, this.mWidth / 2.0F - y2, this.mPaintAxle);
        }

        canvas.drawCircle(this.mWidth / 2.0F, this.mWidth / 2.0F, this.mPaintCenterRadius, this.mPaintCenter);
    }

    private void drawWheelBig(Canvas canvas) {
        for(int i = 0; i < 360; i += this.mWheelBigSpace) {
            int angle = (int)(this.mAnimatedValue * (float)this.mWheelBigSpace + (float)i);
            float x = (float)((double)(this.mWidth / 2.0F - this.mPadding + this.mWheelBigLength) * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y = (float)((double)(this.mWidth / 2.0F - this.mPadding + this.mWheelBigLength) * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            float x2 = (float)((double)(this.mWidth / 2.0F - this.mPadding) * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y2 = (float)((double)(this.mWidth / 2.0F - this.mPadding) * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            canvas.drawLine(this.mWidth / 2.0F - x, this.mWidth / 2.0F - y, this.mWidth / 2.0F - x2, this.mWidth / 2.0F - y2, this.mPaintWheelBig);
        }

    }

    private void drawWheelSmall(Canvas canvas) {
        for(int i = 0; i < 360; i += this.mWheelSmallSpace) {
            int angle = (int)(360.0F - this.mAnimatedValue * (float)this.mWheelBigSpace + (float)i);
            float x = (float)((double)(this.mWidth / 4.0F) * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y = (float)((double)(this.mWidth / 4.0F) * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            float x2 = (float)((double)(this.mWidth / 4.0F + this.mWheelSmallLength) * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y2 = (float)((double)(this.mWidth / 4.0F + this.mWheelSmallLength) * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            canvas.drawLine(this.mWidth / 2.0F - x, this.mWidth / 2.0F - y, this.mWidth / 2.0F - x2, this.mWidth / 2.0F - y2, this.mPaintWheelSmall);
        }

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        this.mPadding = (float)this.dip2px(5.0F);
        this.drawCircle(canvas);
        this.drawWheelBig(canvas);
        this.drawWheelSmall(canvas);
        this.drawAxleAndCenter(canvas);
        canvas.restore();
    }

    private void initPaint() {
        this.mPaintCenterRadius = (float)(this.dip2px(2.5F) / 2);
        this.mPaintCenter = new Paint();
        this.mPaintCenter.setAntiAlias(true);
        this.mPaintCenter.setStyle(Paint.Style.STROKE);
        this.mPaintCenter.setColor(-1);
        this.mPaintCenter.setStrokeWidth((float)this.dip2px(0.5F));
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth((float)this.dip2px(2.0F));
        this.mPaintAxle = new Paint();
        this.mPaintAxle.setAntiAlias(true);
        this.mPaintAxle.setStyle(Paint.Style.FILL);
        this.mPaintAxle.setColor(-1);
        this.mPaintAxle.setStrokeWidth((float)this.dip2px(2.0F));
        this.mPaintWheelBig = new Paint();
        this.mPaintWheelBig.setAntiAlias(true);
        this.mPaintWheelBig.setStyle(Paint.Style.STROKE);
        this.mPaintWheelBig.setColor(-1);
        this.mPaintWheelBig.setStrokeWidth((float)this.dip2px(1.0F));
        this.mPaintWheelSmall = new Paint();
        this.mPaintWheelSmall.setAntiAlias(true);
        this.mPaintWheelSmall.setStyle(Paint.Style.STROKE);
        this.mPaintWheelSmall.setColor(-1);
        this.mPaintWheelSmall.setStrokeWidth((float)this.dip2px(0.5F));
        this.mWheelSmallLength = (float)this.dip2px(3.0F);
        this.mWheelBigLength = (float)this.dip2px(2.5F);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.mPaintCenter.setColor(color);
        this.mPaintAxle.setColor(color);
        this.mPaintWheelBig.setColor(color);
        this.mPaintWheelSmall.setColor(color);
        this.postInvalidate();
    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        this.postInvalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
    }

    protected int OnStopAnim() {
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
