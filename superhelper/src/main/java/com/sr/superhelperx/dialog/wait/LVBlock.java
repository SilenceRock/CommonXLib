package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:27.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVBlock extends LVBase {
    private Paint mPaint;
    private Paint mPaintShadow;
    private Paint mPaintLeft;
    private Paint mPaintRight;
    private float mWidth = 0.0F;
    float moveYtoCenter = 0.0F;
    float rhomboidsX = 0.0F;
    float rhomboidsY = 0.0F;
    float mAnimatedValue = 0.0F;
    private boolean mShadow = true;

    public LVBlock(Context context) {
        super(context);
    }

    public LVBlock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LVBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
        }

        this.rhomboidsX = (float)((double)(3.0F * this.mWidth / 16.0F) / Math.sqrt(3.0D));
        this.rhomboidsY = this.mWidth / 16.0F;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if(!this.mShadow) {
            this.moveYtoCenter = this.mWidth / 4.0F;
        } else {
            this.moveYtoCenter = 0.0F;
        }

        if(this.mAnimatedValue >= 0.0F && this.mAnimatedValue < 0.33333334F) {
            this.drawStep1(canvas, this.mAnimatedValue);
            if(this.mShadow) {
                this.drawShadowStep1(canvas, this.mAnimatedValue);
            }
        } else if(this.mAnimatedValue >= 0.33333334F && this.mAnimatedValue < 0.6666667F) {
            this.drawStep2(canvas, this.mAnimatedValue);
            if(this.mShadow) {
                this.drawShadowStep2(canvas, this.mAnimatedValue);
            }
        } else if(this.mAnimatedValue >= 0.6666667F && this.mAnimatedValue <= 1.0F) {
            this.drawStep3(canvas, this.mAnimatedValue);
            if(this.mShadow) {
                this.drawShadowStep3(canvas, this.mAnimatedValue);
            }
        }

        canvas.restore();
    }

    private void drawStep1(Canvas canvas, float time) {
        float moveX = this.rhomboidsX / 2.0F * time / 0.33333334F;
        float moveY = this.rhomboidsY / 2.0F * time / 0.33333334F;
        Path p = new Path();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - moveX, this.rhomboidsY * 12.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 11.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - moveX, this.mWidth / 4.0F * 3.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - moveX, this.rhomboidsY * 12.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - moveX, this.rhomboidsY * 12.0F - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 11.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintRight);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F + this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintRight);
    }

    private void drawStep2(Canvas canvas, float time) {
        float moveX = this.rhomboidsX * (time - 0.33333334F) / 0.33333334F;
        float moveY = this.rhomboidsY * (time - 0.33333334F) / 0.33333334F;
        Path p = new Path();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX / 2.0F + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 12.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 11.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintRight);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 11.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintRight);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintRight);
    }

    private void drawStep3(Canvas canvas, float time) {
        float moveX = this.rhomboidsX / 2.0F * (time - 0.6666667F) / 0.33333334F;
        float moveY = this.rhomboidsY / 2.0F * (time - 0.6666667F) / 0.33333334F;
        Path p = new Path();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintRight);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
        p.reset();
        p.moveTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintRight);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaint);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY - this.mWidth / 2.0F + this.rhomboidsY * 2.0F + this.moveYtoCenter);
        p.close();
        canvas.drawPath(p, this.mPaintLeft);
    }

    private void drawShadowStep1(Canvas canvas, float time) {
        float moveX = this.rhomboidsX / 2.0F * time / 0.33333334F;
        float moveY = this.rhomboidsY / 2.0F * time / 0.33333334F;
        Path p = new Path();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - moveX, this.rhomboidsY * 12.0F - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 11.0F - moveY);
        p.lineTo(this.mWidth / 2.0F - moveX, this.mWidth / 4.0F * 3.0F - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F - moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY + moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY + moveY);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + moveY);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY + moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F + moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 11.0F + moveY);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F + moveY);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F + moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY - moveY);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - moveY);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
    }

    private void drawShadowStep2(Canvas canvas, float time) {
        float moveX = this.rhomboidsX * (time - 0.33333334F) / 0.33333334F;
        float moveY = this.rhomboidsY * (time - 0.33333334F) / 0.33333334F;
        Path p = new Path();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY / 2.0F - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX / 2.0F + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY / 2.0F - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 12.0F - this.rhomboidsY + this.rhomboidsY / 2.0F);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 11.0F - this.rhomboidsY + this.rhomboidsY / 2.0F);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY / 2.0F + moveY);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + moveY);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 11.0F + this.rhomboidsY - this.rhomboidsY / 2.0F);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - this.rhomboidsY / 2.0F);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
    }

    private void drawShadowStep3(Canvas canvas, float time) {
        float moveX = this.rhomboidsX / 2.0F * (time - 0.6666667F) / 0.33333334F;
        float moveY = this.rhomboidsY / 2.0F * (time - 0.6666667F) / 0.33333334F;
        Path p = new Path();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX - this.rhomboidsX / 2.0F + this.rhomboidsX + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY / 2.0F - this.rhomboidsY + moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 12.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 11.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.mWidth / 4.0F * 3.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F + moveX, this.rhomboidsY * 13.0F - this.rhomboidsY + this.rhomboidsY / 2.0F + moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX + this.rhomboidsX + this.rhomboidsX / 2.0F - this.rhomboidsX - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY / 2.0F + this.rhomboidsY - moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
        p.reset();
        p.moveTo(this.mWidth / 2.0F - this.rhomboidsX * 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 12.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY);
        p.lineTo(this.mWidth / 2.0F - this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 11.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY);
        p.lineTo(this.mWidth / 2.0F + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.mWidth / 4.0F * 3.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY);
        p.lineTo(this.mWidth / 2.0F + -this.rhomboidsX + this.rhomboidsX - this.rhomboidsX / 2.0F - moveX, this.rhomboidsY * 13.0F + this.rhomboidsY - this.rhomboidsY / 2.0F - moveY);
        p.close();
        canvas.drawPath(p, this.mPaintShadow);
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setColor(Color.rgb(247, 202, 42));
        this.mPaint.setStrokeWidth(1.0F);
        this.mPaintShadow = new Paint();
        this.mPaintShadow.setAntiAlias(true);
        this.mPaintShadow.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintShadow.setColor(Color.rgb(0, 0, 0));
        this.mPaintShadow.setStrokeWidth(1.0F);
        this.mPaintLeft = new Paint();
        this.mPaintLeft.setAntiAlias(true);
        this.mPaintLeft.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintLeft.setColor(Color.rgb(227, 144, 11));
        this.mPaintLeft.setStrokeWidth(1.0F);
        this.mPaintRight = new Paint();
        this.mPaintRight.setAntiAlias(true);
        this.mPaintRight.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaintRight.setColor(Color.rgb(188, 91, 26));
        this.mPaintRight.setStrokeWidth(1.0F);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        int red = (color & 16711680) >> 16;
        int green = (color & '\uff00') >> 8;
        int blue = color & 255;
        this.mPaintLeft.setColor(Color.rgb(red - 15 > 0?red - 15:0, green - 58 > 0?green - 58:0, blue - 31 > 0?blue - 31:0));
        this.mPaintRight.setColor(Color.rgb(red - 59 > 0?red - 59:0, green - 111 > 0?green - 111:0, blue - 16 > 0?blue - 16:0));
        this.postInvalidate();
    }

    public void setShadowColor(int color) {
        this.mPaintShadow.setColor(color);
        this.postInvalidate();
        this.postInvalidate();
    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        this.invalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
    }

    protected int OnStopAnim() {
        this.mAnimatedValue = 0.0F;
        this.postInvalidate();
        return 1;
    }

    protected int SetAnimRepeatMode() {
        return 1;
    }

    public void isShadow(boolean show) {
        this.mShadow = show;
        this.invalidate();
    }

    protected void AinmIsRunning() {
    }

    protected int SetAnimRepeatCount() {
        return -1;
    }
}
