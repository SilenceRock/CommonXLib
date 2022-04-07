package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:31.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVGhost extends LVBase {
    float mWidth = 0.0F;
    float mHight = 0.0F;
    Paint mPaint;
    Paint mPaintHand;
    Paint mPaintShadow;
    RectF rectFGhost = new RectF();
    RectF rectFGhostShadow = new RectF();
    float mPadding = 0.0F;
    int mskirtH = 0;
    Path path = new Path();
    float wspace = 10.0F;
    float hspace = 10.0F;
    private float mAnimatedValue = 0.0F;
    int onAnimationRepeatFlag = 1;

    public LVGhost(Context context) {
        super(context);
    }

    public LVGhost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVGhost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = (float)this.getMeasuredWidth();
        this.mHight = (float)this.getMeasuredHeight();
        this.mPadding = 10.0F;
        this.mskirtH = (int)(this.mWidth / 40.0F);
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-1);
        this.mPaintHand = new Paint();
        this.mPaintHand.setAntiAlias(true);
        this.mPaintHand.setStyle(Paint.Style.FILL);
        this.mPaintHand.setColor(Color.argb(220, 0, 0, 0));
        this.mPaintShadow = new Paint();
        this.mPaintShadow.setAntiAlias(true);
        this.mPaintShadow.setStyle(Paint.Style.FILL);
        this.mPaintShadow.setColor(Color.argb(60, 0, 0, 0));
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.postInvalidate();
    }

    public void setHandColor(int color) {
        this.mPaintHand.setColor(color);
        this.postInvalidate();
    }

    private void drawShadow(Canvas canvas) {
        canvas.drawArc(this.rectFGhostShadow, 0.0F, 360.0F, false, this.mPaintShadow);
    }

    private void drawHead(Canvas canvas) {
        canvas.drawCircle(this.rectFGhost.left + this.rectFGhost.width() / 2.0F, this.rectFGhost.width() / 2.0F + this.rectFGhost.top, this.rectFGhost.width() / 2.0F - 15.0F, this.mPaint);
    }

    private void drawHand(Canvas canvas) {
        canvas.drawCircle(this.rectFGhost.left + this.rectFGhost.width() / 2.0F - (float)(this.mskirtH * 3 / 2) + (float)(this.mskirtH * this.onAnimationRepeatFlag), this.rectFGhost.width() / 2.0F + (float)this.mskirtH + this.rectFGhost.top, (float)this.mskirtH * 0.9F, this.mPaintHand);
        canvas.drawCircle(this.rectFGhost.left + this.rectFGhost.width() / 2.0F + (float)(this.mskirtH * 3 / 2) + (float)(this.mskirtH * this.onAnimationRepeatFlag), this.rectFGhost.width() / 2.0F + (float)this.mskirtH + this.rectFGhost.top, (float)this.mskirtH * 0.9F, this.mPaintHand);
    }

    private void drawBody(Canvas canvas) {
        this.path.reset();
        float x = (float)((double)(this.rectFGhost.width() / 2.0F - 15.0F) * Math.cos(0.08726646259971647D));
        float y = (float)((double)(this.rectFGhost.width() / 2.0F - 15.0F) * Math.sin(0.08726646259971647D));
        float x2 = (float)((double)(this.rectFGhost.width() / 2.0F - 15.0F) * Math.cos(3.0543261909900763D));
        float y2 = (float)((double)(this.rectFGhost.width() / 2.0F - 15.0F) * Math.sin(3.0543261909900763D));
        this.path.moveTo(this.rectFGhost.left + this.rectFGhost.width() / 2.0F - x, this.rectFGhost.width() / 2.0F - y + this.rectFGhost.top);
        this.path.lineTo(this.rectFGhost.left + this.rectFGhost.width() / 2.0F - x2, this.rectFGhost.width() / 2.0F - y2 + this.rectFGhost.top);
        this.path.quadTo(this.rectFGhost.right + this.wspace / 2.0F, this.rectFGhost.bottom, this.rectFGhost.right - this.wspace, this.rectFGhost.bottom - this.hspace);
        float a = (float)this.mskirtH;
        float m = (this.rectFGhost.width() - 2.0F * this.wspace) / 7.0F;

        for(int i = 0; i < 7; ++i) {
            if(i % 2 == 0) {
                this.path.quadTo(this.rectFGhost.right - this.wspace - m * (float)i - m / 2.0F, this.rectFGhost.bottom - this.hspace - a, this.rectFGhost.right - this.wspace - m * (float)(i + 1), this.rectFGhost.bottom - this.hspace);
            } else {
                this.path.quadTo(this.rectFGhost.right - this.wspace - m * (float)i - m / 2.0F, this.rectFGhost.bottom - this.hspace + a, this.rectFGhost.right - this.wspace - m * (float)(i + 1), this.rectFGhost.bottom - this.hspace);
            }
        }

        this.path.quadTo(this.rectFGhost.left - 5.0F, this.rectFGhost.bottom, this.rectFGhost.left + this.rectFGhost.width() / 2.0F - x, this.rectFGhost.width() / 2.0F - y + this.rectFGhost.top);
        this.path.close();
        canvas.drawPath(this.path, this.mPaint);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        float distance = (this.mWidth - 2.0F * this.mPadding) / 3.0F * 2.0F * this.mAnimatedValue;
        this.rectFGhost.left = this.mPadding + distance;
        this.rectFGhost.right = (this.mWidth - 2.0F * this.mPadding) / 3.0F + distance;
        float moveY = 0.0F;
        float moveYMax = this.mHight / 4.0F / 2.0F;
        float shadowHighMax = 5.0F;
        float shadowHigh = 0.0F;
        if((double)this.mAnimatedValue <= 0.25D) {
            moveY = (float)((double)moveYMax / 0.25D * (double)this.mAnimatedValue);
            this.rectFGhost.top = moveY;
            this.rectFGhost.bottom = this.mHight / 4.0F * 3.0F + moveY;
            shadowHigh = shadowHighMax / 0.25F * this.mAnimatedValue;
        } else if((double)this.mAnimatedValue > 0.25D && this.mAnimatedValue <= 0.5F) {
            moveY = (float)((double)moveYMax / 0.25D * (double)(this.mAnimatedValue - 0.25F));
            this.rectFGhost.top = moveYMax - moveY;
            this.rectFGhost.bottom = this.mHight / 4.0F * 3.0F + moveYMax - moveY;
            shadowHigh = shadowHighMax - shadowHighMax / 0.25F * (this.mAnimatedValue - 0.25F);
        } else if((double)this.mAnimatedValue > 0.5D && this.mAnimatedValue <= 0.75F) {
            moveY = (float)((double)moveYMax / 0.25D * (double)(this.mAnimatedValue - 0.5F));
            this.rectFGhost.top = moveY;
            this.rectFGhost.bottom = this.mHight / 4.0F * 3.0F + moveY;
            shadowHigh = shadowHighMax / 0.25F * (this.mAnimatedValue - 0.5F);
        } else if((double)this.mAnimatedValue > 0.75D && this.mAnimatedValue <= 1.0F) {
            moveY = (float)((double)moveYMax / 0.25D * (double)(this.mAnimatedValue - 0.75F));
            this.rectFGhost.top = moveYMax - moveY;
            this.rectFGhost.bottom = this.mHight / 4.0F * 3.0F + moveYMax - moveY;
            shadowHigh = shadowHighMax - shadowHighMax / 0.25F * (this.mAnimatedValue - 0.75F);
        }

        this.rectFGhostShadow.top = this.mHight - 25.0F + shadowHigh;
        this.rectFGhostShadow.bottom = this.mHight - 5.0F - shadowHigh;
        this.rectFGhostShadow.left = this.rectFGhost.left + 5.0F + shadowHigh * 3.0F;
        this.rectFGhostShadow.right = this.rectFGhost.right - 5.0F - shadowHigh * 3.0F;
        this.drawShadow(canvas);
        this.drawHead(canvas);
        this.drawBody(canvas);
        this.drawHand(canvas);
        canvas.restore();
    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        this.invalidate();
    }

    protected int SetAnimRepeatCount() {
        return -1;
    }

    protected void OnAnimationRepeat(Animator animation) {
        this.onAnimationRepeatFlag *= -1;
        if(this.onAnimationRepeatFlag == -1) {
            this.wspace = 22.0F;
        } else {
            this.wspace = -2.0F;
        }

    }

    protected int OnStopAnim() {
        this.mAnimatedValue = 0.0F;
        this.wspace = 10.0F;
        this.onAnimationRepeatFlag = 1;
        this.postInvalidate();
        return 1;
    }

    protected int SetAnimRepeatMode() {
        return 2;
    }

    protected void AinmIsRunning() {
        this.wspace = -2.0F;
    }
}
