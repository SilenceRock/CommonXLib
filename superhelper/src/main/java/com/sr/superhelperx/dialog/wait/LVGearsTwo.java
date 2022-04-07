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

public class LVGearsTwo extends LVBase {
    private float mWidth = 0.0F;
    private Paint mPaint;
    private Paint mPaintAxle;
    private Paint mPaintRing;
    private float mPadding = 0.0F;
    private float mWheelLength;
    private int mWheelSmallSpace = 10;
    private int mWheelBigSpace = 8;
    ValueAnimator valueAnimator = null;
    float mAnimatedValue = 0.0F;
    float hypotenuse = 0.0F;
    float smallRingCenterX = 0.0F;
    float smallRingCenterY = 0.0F;
    float bigRingCenterX = 0.0F;
    float bigRingCenterY = 0.0F;

    public LVGearsTwo(Context context) {
        super(context);
    }

    public LVGearsTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVGearsTwo(Context context, AttributeSet attrs, int defStyleAttr) {
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

    private void drawSmallRing(Canvas canvas) {
        this.hypotenuse = (float)((double)this.mWidth * Math.sqrt(2.0D));
        this.smallRingCenterX = (float)((double)(this.hypotenuse / 6.0F) * Math.cos(0.7853981633974483D));
        this.smallRingCenterY = (float)((double)(this.hypotenuse / 6.0F) * Math.sin(0.7853981633974483D));
        this.mPaintRing.setStrokeWidth((float)this.dip2px(1.0F));
        canvas.drawCircle(this.mPadding + this.smallRingCenterX, this.smallRingCenterY + this.mPadding, this.smallRingCenterX, this.mPaintRing);
        this.mPaintRing.setStrokeWidth((float)this.dip2px(1.5F));
        canvas.drawCircle(this.mPadding + this.smallRingCenterX, this.smallRingCenterY + this.mPadding, this.smallRingCenterX / 2.0F, this.mPaintRing);
    }

    private void drawSmallGear(Canvas canvas) {
        this.mPaint.setStrokeWidth((float)this.dip2px(1.0F));

        for(int i = 0; i < 360; i += this.mWheelSmallSpace) {
            int angle = (int)(this.mAnimatedValue * (float)this.mWheelSmallSpace + (float)i);
            float x3 = (float)((double)this.smallRingCenterX * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y3 = (float)((double)this.smallRingCenterY * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            float x4 = (float)((double)(this.smallRingCenterX + this.mWheelLength) * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y4 = (float)((double)(this.smallRingCenterY + this.mWheelLength) * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            canvas.drawLine(this.mPadding + this.smallRingCenterX - x4, this.smallRingCenterY + this.mPadding - y4, this.smallRingCenterX + this.mPadding - x3, this.smallRingCenterY + this.mPadding - y3, this.mPaint);
        }

    }

    private void drawBigGear(Canvas canvas) {
        this.bigRingCenterX = (float)((double)(this.hypotenuse / 2.0F) * Math.cos(0.7853981633974483D));
        this.bigRingCenterY = (float)((double)(this.hypotenuse / 2.0F) * Math.sin(0.7853981633974483D));
        float strokeWidth = (float)(this.dip2px(1.5F) / 4);
        this.mPaint.setStrokeWidth((float)this.dip2px(1.5F));

        for(int i = 0; i < 360; i += this.mWheelBigSpace) {
            int angle = (int)(360.0F - (this.mAnimatedValue * (float)this.mWheelBigSpace + (float)i));
            float x3 = (float)((double)(this.bigRingCenterX - this.smallRingCenterX) * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y3 = (float)((double)(this.bigRingCenterY - this.smallRingCenterY) * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            float x4 = (float)((double)(this.bigRingCenterX - this.smallRingCenterX + this.mWheelLength) * Math.cos((double)angle * 3.141592653589793D / 180.0D));
            float y4 = (float)((double)(this.bigRingCenterY - this.smallRingCenterY + this.mWheelLength) * Math.sin((double)angle * 3.141592653589793D / 180.0D));
            canvas.drawLine(this.bigRingCenterX + this.mPadding - x4 + this.mWheelLength * 2.0F + strokeWidth, this.bigRingCenterY + this.mPadding - y4 + this.mWheelLength * 2.0F + strokeWidth, this.bigRingCenterX + this.mPadding - x3 + this.mWheelLength * 2.0F + strokeWidth, this.bigRingCenterY + this.mPadding - y3 + this.mWheelLength * 2.0F + strokeWidth, this.mPaint);
        }

    }

    private void drawBigRing(Canvas canvas) {
        float strokeWidth = (float)(this.dip2px(1.5F) / 4);
        this.mPaintRing.setStrokeWidth((float)this.dip2px(1.5F));
        canvas.drawCircle(this.bigRingCenterX + this.mPadding + this.mWheelLength * 2.0F + strokeWidth, this.bigRingCenterY + this.mPadding + this.mWheelLength * 2.0F + strokeWidth, this.bigRingCenterX - this.smallRingCenterX - strokeWidth, this.mPaintRing);
        this.mPaintRing.setStrokeWidth((float)this.dip2px(1.5F));
        canvas.drawCircle(this.bigRingCenterX + this.mPadding + this.mWheelLength * 2.0F + strokeWidth, this.bigRingCenterY + this.mPadding + this.mWheelLength * 2.0F + strokeWidth, (this.bigRingCenterX - this.smallRingCenterX) / 2.0F - strokeWidth, this.mPaintRing);
    }

    private void drawAxle(Canvas canvas) {
        int i;
        float x3;
        float y3;
        for(i = 0; i < 3; ++i) {
            x3 = (float)((double)this.smallRingCenterX * Math.cos((double)(i * 120) * 3.141592653589793D / 180.0D));
            y3 = (float)((double)this.smallRingCenterY * Math.sin((double)(i * 120) * 3.141592653589793D / 180.0D));
            canvas.drawLine(this.mPadding + this.smallRingCenterX, this.mPadding + this.smallRingCenterY, this.mPadding + this.smallRingCenterX - x3, this.mPadding + this.smallRingCenterY - y3, this.mPaintAxle);
        }

        for(i = 0; i < 3; ++i) {
            x3 = (float)((double)(this.bigRingCenterX - this.smallRingCenterX) * Math.cos((double)(i * 120) * 3.141592653589793D / 180.0D));
            y3 = (float)((double)(this.bigRingCenterY - this.smallRingCenterY) * Math.sin((double)(i * 120) * 3.141592653589793D / 180.0D));
            canvas.drawLine(this.bigRingCenterX + this.mPadding + this.mWheelLength * 2.0F, this.bigRingCenterY + this.mPadding + this.mWheelLength * 2.0F, this.bigRingCenterX + this.mPadding + this.mWheelLength * 2.0F - x3, this.bigRingCenterY + this.mPadding + this.mWheelLength * 2.0F - y3, this.mPaintAxle);
        }

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPadding = (float)this.dip2px(5.0F);
        canvas.save();
        canvas.rotate(180.0F, this.mWidth / 2.0F, this.mWidth / 2.0F);
        this.drawSmallRing(canvas);
        this.drawSmallGear(canvas);
        this.drawBigGear(canvas);
        this.drawBigRing(canvas);
        this.drawAxle(canvas);
        canvas.restore();
    }

    private void initPaint() {
        this.mPaintRing = new Paint();
        this.mPaintRing.setAntiAlias(true);
        this.mPaintRing.setStyle(Paint.Style.STROKE);
        this.mPaintRing.setColor(-1);
        this.mPaintRing.setStrokeWidth((float)this.dip2px(1.5F));
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth((float)this.dip2px(1.0F));
        this.mPaintAxle = new Paint();
        this.mPaintAxle.setAntiAlias(true);
        this.mPaintAxle.setStyle(Paint.Style.FILL);
        this.mPaintAxle.setColor(-1);
        this.mPaintAxle.setStrokeWidth((float)this.dip2px(1.5F));
        this.mWheelLength = (float)this.dip2px(2.0F);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.mPaintAxle.setColor(color);
        this.mPaintRing.setColor(color);
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

