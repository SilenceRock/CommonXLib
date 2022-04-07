package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:28.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVCircularSmile extends LVBase {
    private Paint mPaint;
    private float mWidth = 0.0F;
    private float mEyeWidth = 0.0F;
    private float mPadding = 0.0F;
    private float startAngle = 0.0F;
    private boolean isSmile = false;
    RectF rectF = new RectF();
    float mAnimatedValue = 0.0F;

    public LVCircularSmile(Context context) {
        super(context);
    }

    public LVCircularSmile(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVCircularSmile(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
        }

        this.mPadding = (float)this.dip2px(10.0F);
        this.mEyeWidth = (float)this.dip2px(3.0F);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.rectF = new RectF(this.mPadding, this.mPadding, this.mWidth - this.mPadding, this.mWidth - this.mPadding);
        this.mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(this.rectF, this.startAngle, 180.0F, false, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        if(this.isSmile) {
            canvas.drawCircle(this.mPadding + this.mEyeWidth + this.mEyeWidth / 2.0F, this.mWidth / 3.0F, this.mEyeWidth, this.mPaint);
            canvas.drawCircle(this.mWidth - this.mPadding - this.mEyeWidth - this.mEyeWidth / 2.0F, this.mWidth / 3.0F, this.mEyeWidth, this.mPaint);
        }

    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth((float)this.dip2px(2.0F));
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.postInvalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        if((double)this.mAnimatedValue < 0.5D) {
            this.isSmile = false;
            this.startAngle = 720.0F * this.mAnimatedValue;
        } else {
            this.startAngle = 720.0F;
            this.isSmile = true;
        }

        this.invalidate();
    }

    protected int OnStopAnim() {
        this.isSmile = false;
        this.mAnimatedValue = 0.0F;
        this.startAngle = 0.0F;
        return 0;
    }

    protected void InitPaint() {
        this.initPaint();
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
