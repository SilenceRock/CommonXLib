package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:31.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVPlayBall extends LVBase {
    private Paint mPaint;
    private Paint mPaintCircle;
    private Paint mPaintBall;
    private float mPaintStrokeWidth;
    private float mHigh = 0.0F;
    private float mWidth = 0.0F;
    private float quadToStart = 0.0F;
    private float mRadius = 0.0F;
    private float mRadiusBall = 0.0F;
    private float ballY = 0.0F;
    Path path = new Path();

    public LVPlayBall(Context context) {
        super(context);
    }

    public LVPlayBall(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVPlayBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mHigh = (float)this.getMeasuredHeight();
        this.mWidth = (float)this.getMeasuredWidth();
        this.quadToStart = this.mHigh / 2.0F;
        this.mRadius = (float)this.dip2px(3.0F);
        this.mPaintStrokeWidth = 2.0F;
        this.ballY = this.mHigh / 2.0F;
        this.mRadiusBall = (float)this.dip2px(4.0F);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.path = new Path();
        this.path.moveTo(0.0F + this.mRadius * 2.0F + this.mPaintStrokeWidth, (float)(this.getMeasuredHeight() / 2));
        this.path.quadTo(this.mWidth / 2.0F, this.quadToStart, this.mWidth - this.mRadius * 2.0F - this.mPaintStrokeWidth, this.mHigh / 2.0F);
        this.mPaint.setStrokeWidth(2.0F);
        canvas.drawPath(this.path, this.mPaint);
        this.mPaintCircle.setStrokeWidth(this.mPaintStrokeWidth);
        canvas.drawCircle(this.mRadius + this.mPaintStrokeWidth, this.mHigh / 2.0F, this.mRadius, this.mPaintCircle);
        canvas.drawCircle(this.mWidth - this.mRadius - this.mPaintStrokeWidth, this.mHigh / 2.0F, this.mRadius, this.mPaintCircle);
        if(this.ballY - this.mRadiusBall > this.mRadiusBall) {
            canvas.drawCircle(this.mWidth / 2.0F, this.ballY - this.mRadiusBall, this.mRadiusBall, this.mPaintBall);
        } else {
            canvas.drawCircle(this.mWidth / 2.0F, this.mRadiusBall, this.mRadiusBall, this.mPaintBall);
        }

    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaintCircle = new Paint();
        this.mPaintCircle.setAntiAlias(true);
        this.mPaintCircle.setStyle(Paint.Style.STROKE);
        this.mPaintCircle.setColor(-1);
        this.mPaintBall = new Paint();
        this.mPaintBall.setAntiAlias(true);
        this.mPaintBall.setStyle(Paint.Style.FILL);
        this.mPaintBall.setColor(-1);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.mPaintCircle.setColor(color);
        this.postInvalidate();
    }

    public void setBallColor(int color) {
        this.mPaintBall.setColor(color);
        this.postInvalidate();
    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        float value = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        if((double)value > 0.75D) {
            this.quadToStart = this.mHigh / 2.0F - (1.0F - ((Float)valueAnimator.getAnimatedValue()).floatValue()) * this.mHigh / 3.0F;
        } else {
            this.quadToStart = this.mHigh / 2.0F + (1.0F - ((Float)valueAnimator.getAnimatedValue()).floatValue()) * this.mHigh / 3.0F;
        }

        if(value > 0.35F) {
            this.ballY = this.mHigh / 2.0F - this.mHigh / 2.0F * value;
        } else {
            this.ballY = this.mHigh / 2.0F + this.mHigh / 6.0F * value;
        }

        this.invalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
    }

    protected int OnStopAnim() {
        this.quadToStart = this.mHigh / 2.0F;
        this.ballY = this.mHigh / 2.0F;
        return 0;
    }

    protected int SetAnimRepeatMode() {
        return 2;
    }

    protected void AinmIsRunning() {
    }

    protected int SetAnimRepeatCount() {
        return -1;
    }
}
