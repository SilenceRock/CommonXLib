package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:31.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVNews extends LVBase {
    private float mWidth = 0.0F;
    private float mPadding = 0.0F;
    private Paint mPaint;
    private float cornerRadius = 0.0F;
    RectF rectFTopRight = new RectF();
    RectF rectFBottomRight = new RectF();
    RectF rectFBottomLeft = new RectF();
    RectF rectFTopLeft = new RectF();
    RectF rectFSquare = new RectF();
    RectF rectFSquareBG = new RectF();
    float marggingSquareH = 0.0F;
    float marggingSquareV = 0.0F;
    float marggingLineH = 0.0F;
    float marggingLineV = 0.0F;
    private int mValue = 100;
    private int mStep = 1;
    float mAnimatedValue = 0.0F;

    public LVNews(Context context) {
        super(context);
    }

    public LVNews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVNews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(int value) {
        this.stopAnim();
        if(value <= 100) {
            this.mValue = value;
            this.postInvalidate();
            if(this.mValue == 100) {
                this.startAnim();
            }
        } else {
            this.mValue = 100;
        }

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
        this.cornerRadius = (float)this.dip2px(3.0F);
        this.mPadding = (float)this.dip2px(1.0F);
        canvas.save();
        this.mPaint.setStrokeWidth((float)this.dip2px(1.0F));
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.rectFSquareBG.top = this.mPadding;
        this.rectFSquareBG.left = this.mPadding;
        this.rectFSquareBG.right = this.mWidth - this.mPadding;
        this.rectFSquareBG.bottom = this.mWidth - this.mPadding;
        this.drawContent(canvas, this.mStep);
        if(this.mValue <= 25) {
            if(this.mValue <= 5) {
                this.mValue = 5;
            }

            this.drawLineToRight(canvas, this.mValue);
            this.drawContentSquareLineToRight(canvas, this.mValue);
        } else if(this.mValue > 25 && this.mValue <= 50) {
            this.drawLineToBottom(canvas, this.mValue);
            this.drawContentSquareLineToBottom(canvas, this.mValue);
        } else if(this.mValue > 50 && this.mValue <= 75) {
            this.drawLineToLeft(canvas, this.mValue);
            this.drawContentSquareLineToLeft(canvas, this.mValue);
        } else if(this.mValue > 75) {
            if(this.mValue > 100) {
                this.mValue = 100;
            }

            this.drawLineToTop(canvas, this.mValue);
            this.drawContentSquareLineToTop(canvas, this.mValue);
        }

        if(this.mValue <= 16) {
            this.drawLine(canvas, 1, this.mValue);
        } else if(this.mValue > 16 && this.mValue <= 32) {
            this.drawLine(canvas, 2, this.mValue);
        } else if(this.mValue > 32 && this.mValue <= 48) {
            this.drawLine(canvas, 3, this.mValue);
        } else if(this.mValue > 48 && this.mValue <= 64) {
            this.drawLine(canvas, 4, this.mValue);
        } else if(this.mValue > 64 && this.mValue <= 80) {
            this.drawLine(canvas, 5, this.mValue);
        } else if(this.mValue > 80) {
            this.drawLine(canvas, 6, this.mValue);
        }

        canvas.restore();
    }

    private void drawLineToRight(Canvas canvas, int value) {
        if(value <= 20) {
            canvas.drawLine(this.rectFSquareBG.left + this.cornerRadius, this.rectFSquareBG.top, this.rectFSquareBG.width() * (float)value / 20.0F - this.cornerRadius, this.rectFSquareBG.top, this.mPaint);
        } else {
            canvas.drawLine(this.rectFSquareBG.left + this.cornerRadius, this.rectFSquareBG.top, this.rectFSquareBG.right - this.cornerRadius, this.rectFSquareBG.top, this.mPaint);
            this.rectFTopRight.top = this.mPadding;
            this.rectFTopRight.left = this.mWidth - this.mPadding - this.cornerRadius * 2.0F;
            this.rectFTopRight.bottom = this.mPadding + this.cornerRadius * 2.0F;
            this.rectFTopRight.right = this.mWidth - this.mPadding;
            canvas.drawArc(this.rectFTopRight, -90.0F, 18.0F * (float)(value - 20), false, this.mPaint);
        }

    }

    private void drawLineToBottom(Canvas canvas, int value) {
        this.drawLineToRight(canvas, 25);
        if(value <= 45) {
            canvas.drawLine(this.rectFSquareBG.right, this.rectFSquareBG.top + this.cornerRadius, this.rectFSquareBG.right, this.rectFSquareBG.height() * (float)(value - 25) / 20.0F, this.mPaint);
        } else {
            canvas.drawLine(this.rectFSquareBG.right, this.rectFSquareBG.top + this.cornerRadius, this.rectFSquareBG.right, this.rectFSquareBG.bottom - this.cornerRadius, this.mPaint);
            this.rectFBottomRight.top = this.mWidth - this.mPadding - this.cornerRadius * 2.0F;
            this.rectFBottomRight.left = this.mWidth - this.mPadding - this.cornerRadius * 2.0F;
            this.rectFBottomRight.bottom = this.mWidth - this.mPadding;
            this.rectFBottomRight.right = this.mWidth - this.mPadding;
            canvas.drawArc(this.rectFBottomRight, 0.0F, 18.0F * (float)(value - 45), false, this.mPaint);
        }

    }

    private void drawLineToLeft(Canvas canvas, int value) {
        this.drawLineToBottom(canvas, 50);
        if(value <= 70) {
            canvas.drawLine(this.rectFSquareBG.right - this.cornerRadius, this.rectFSquareBG.bottom, this.rectFSquareBG.left + this.rectFSquareBG.width() - this.rectFSquareBG.width() * (float)(value - 50) / 20.0F, this.rectFSquareBG.bottom, this.mPaint);
        } else {
            canvas.drawLine(this.rectFSquareBG.right - this.cornerRadius, this.rectFSquareBG.bottom, this.rectFSquareBG.left + this.cornerRadius, this.rectFSquareBG.bottom, this.mPaint);
            this.rectFBottomLeft.top = this.mWidth - this.mPadding - this.cornerRadius * 2.0F;
            this.rectFBottomLeft.left = this.mPadding;
            this.rectFBottomLeft.bottom = this.mWidth - this.mPadding;
            this.rectFBottomLeft.right = this.mPadding + this.cornerRadius * 2.0F;
            canvas.drawArc(this.rectFBottomLeft, 90.0F, 18.0F * (float)(value - 70), false, this.mPaint);
        }

    }

    private void drawLineToTop(Canvas canvas, int value) {
        this.drawLineToLeft(canvas, 75);
        if(value <= 95) {
            canvas.drawLine(this.rectFSquareBG.left, this.rectFSquareBG.bottom - this.cornerRadius, this.rectFSquareBG.left, this.rectFSquareBG.top + this.rectFSquareBG.height() - this.rectFSquareBG.height() * (float)(value - 75) / 20.0F, this.mPaint);
        } else {
            canvas.drawLine(this.rectFSquareBG.left, this.rectFSquareBG.bottom - this.cornerRadius, this.rectFSquareBG.left, this.rectFSquareBG.top + this.cornerRadius, this.mPaint);
            this.rectFTopLeft.top = this.mPadding;
            this.rectFTopLeft.left = this.mPadding;
            this.rectFTopLeft.bottom = this.mPadding + this.cornerRadius * 2.0F;
            this.rectFTopLeft.right = this.mPadding + this.cornerRadius * 2.0F;
            canvas.drawArc(this.rectFTopLeft, 180.0F, 18.0F * (float)(value - 95), false, this.mPaint);
        }

    }

    private void drawContentSquareLineToRight(Canvas canvas, int value) {
        canvas.drawLine(this.rectFSquare.left, this.rectFSquare.top, this.rectFSquare.left + this.rectFSquare.width() * (float)value / 25.0F, this.rectFSquare.top, this.mPaint);
    }

    private void drawContentSquareLineToBottom(Canvas canvas, int value) {
        this.drawContentSquareLineToRight(canvas, 25);
        canvas.drawLine(this.rectFSquare.right, this.rectFSquare.top, this.rectFSquare.right, this.rectFSquare.top + this.rectFSquare.height() * (float)(value - 25) / 25.0F, this.mPaint);
    }

    private void drawContentSquareLineToLeft(Canvas canvas, int value) {
        this.drawContentSquareLineToBottom(canvas, 50);
        canvas.drawLine(this.rectFSquare.right, this.rectFSquare.bottom, this.rectFSquare.left + this.rectFSquare.width() - this.rectFSquare.width() * (float)(value - 50) / 25.0F, this.rectFSquare.bottom, this.mPaint);
    }

    private void drawContentSquareLineToTop(Canvas canvas, int value) {
        this.drawContentSquareLineToLeft(canvas, 75);
        canvas.drawLine(this.rectFSquare.left, this.rectFSquare.bottom, this.rectFSquare.left, this.rectFSquare.top + this.rectFSquare.height() - this.rectFSquare.height() * (float)(value - 75) / 25.0F, this.mPaint);
    }

    private void drawContent(Canvas canvas, int step) {
        if(step == 1) {
            this.marggingSquareH = 0.0F + this.mAnimatedValue * (this.mWidth / 2.0F - this.cornerRadius) / 0.25F;
            this.marggingSquareV = 0.0F;
            this.marggingLineH = 0.0F + this.mAnimatedValue * (this.mWidth / 2.0F - this.cornerRadius) / 0.25F;
            this.marggingLineV = 0.0F;
        } else if(step == 2) {
            this.marggingSquareH = this.mWidth / 2.0F - this.cornerRadius;
            this.marggingSquareV = 0.0F + (this.mWidth / 2.0F - this.cornerRadius) / 0.25F * (this.mAnimatedValue - 0.25F);
            this.marggingLineH = this.mWidth / 2.0F - this.cornerRadius;
            this.marggingLineV = 0.0F + (-this.mWidth / 2.0F + this.cornerRadius) / 0.25F * (this.mAnimatedValue - 0.25F);
        } else if(step == 3) {
            this.marggingSquareH = this.mWidth / 2.0F - this.cornerRadius - (this.mWidth / 2.0F - this.cornerRadius) / 0.25F * (this.mAnimatedValue - 0.5F);
            this.marggingSquareV = this.mWidth / 2.0F - this.cornerRadius;
            this.marggingLineH = this.mWidth / 2.0F - this.cornerRadius - (this.mWidth / 2.0F - this.cornerRadius) / 0.25F * (this.mAnimatedValue - 0.5F);
            this.marggingLineV = -this.mWidth / 2.0F + this.cornerRadius;
        } else if(step == 4) {
            this.marggingSquareH = 0.0F;
            this.marggingSquareV = this.mWidth / 2.0F - this.cornerRadius - (this.mWidth / 2.0F - this.cornerRadius) / 0.25F * (this.mAnimatedValue - 0.75F);
            this.marggingLineH = 0.0F;
            this.marggingLineV = -this.mWidth / 2.0F + this.cornerRadius - (-this.mWidth / 2.0F + this.cornerRadius) / 0.25F * (this.mAnimatedValue - 0.75F);
        }

        if(this.mValue == 100) {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setAlpha(100);
            this.rectFSquare.top = this.mPadding + this.cornerRadius + this.marggingSquareV;
            this.rectFSquare.left = this.mPadding + this.cornerRadius + this.marggingSquareH;
            this.rectFSquare.bottom = this.mWidth / 2.0F - this.mPadding + this.marggingSquareV;
            this.rectFSquare.right = this.mWidth / 2.0F - this.mPadding + this.marggingSquareH;
            canvas.drawRect(this.rectFSquare, this.mPaint);
        }

        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setAlpha(255);
    }

    private void drawShortLine(Canvas canvas, float line_width_short, int value, int positon) {
        --positon;
        canvas.drawLine(this.mWidth / 2.0F + this.mPadding + this.cornerRadius / 2.0F - this.marggingLineH, this.mPadding + this.cornerRadius + this.cornerRadius - this.marggingLineV + this.rectFSquare.height() / 3.0F * (float)positon, this.mWidth / 2.0F + this.mPadding + this.cornerRadius / 2.0F - this.marggingLineH + line_width_short / 16.0F * (float)value, this.mPadding + this.cornerRadius + this.cornerRadius - this.marggingLineV + this.rectFSquare.height() / 3.0F * (float)positon, this.mPaint);
    }

    private void drawlongLine(Canvas canvas, float line_width_long, int value, int positon) {
        positon -= 4;
        canvas.drawLine(this.mPadding + this.cornerRadius, this.mPadding + this.cornerRadius + this.rectFSquare.height() / 3.0F * (float)positon + this.mWidth / 2.0F + this.marggingLineV, this.mPadding + this.cornerRadius + line_width_long / 16.0F * (float)value, this.mPadding + this.cornerRadius + this.rectFSquare.height() / 3.0F * (float)positon + this.mWidth / 2.0F + this.marggingLineV, this.mPaint);
    }

    private void drawLine(Canvas canvas, int count, int mValue) {
        float line_width_short = this.mWidth - this.mPadding - this.cornerRadius - this.marggingLineH - (this.mWidth / 2.0F + this.mPadding + this.cornerRadius / 2.0F - this.marggingLineH);
        float line_width_long = this.mWidth - this.mPadding - this.cornerRadius - (this.mPadding + this.cornerRadius);
        if(count == 1) {
            this.drawShortLine(canvas, line_width_short, mValue, 1);
        } else if(count == 2) {
            this.drawShortLine(canvas, line_width_short, 16, 1);
            this.drawShortLine(canvas, line_width_short, mValue - 16, 2);
        } else if(count == 3) {
            this.drawShortLine(canvas, line_width_short, 16, 1);
            this.drawShortLine(canvas, line_width_short, 16, 2);
            this.drawShortLine(canvas, line_width_short, mValue - 32, 3);
        } else if(count == 4) {
            this.drawShortLine(canvas, line_width_short, 16, 1);
            this.drawShortLine(canvas, line_width_short, 16, 2);
            this.drawShortLine(canvas, line_width_short, 16, 3);
            this.drawlongLine(canvas, line_width_long, mValue - 48, 4);
        } else if(count == 5) {
            this.drawShortLine(canvas, line_width_short, 16, 1);
            this.drawShortLine(canvas, line_width_short, 16, 2);
            this.drawShortLine(canvas, line_width_short, 16, 3);
            this.drawlongLine(canvas, line_width_long, 16, 4);
            this.drawlongLine(canvas, line_width_long, mValue - 64, 5);
        } else if(count == 6) {
            this.drawShortLine(canvas, line_width_short, 16, 1);
            this.drawShortLine(canvas, line_width_short, 16, 2);
            this.drawShortLine(canvas, line_width_short, 16, 3);
            this.drawlongLine(canvas, line_width_long, 16, 4);
            this.drawlongLine(canvas, line_width_long, 16, 5);
            canvas.drawLine(this.mPadding + this.cornerRadius, this.mPadding + this.cornerRadius + this.rectFSquare.height() / 3.0F * 2.0F + this.mWidth / 2.0F + this.marggingLineV, this.mPadding + this.cornerRadius + line_width_long / 20.0F * (float)(mValue - 80), this.mPadding + this.cornerRadius + this.rectFSquare.height() / 3.0F * 2.0F + this.mWidth / 2.0F + this.marggingLineV, this.mPaint);
        }

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
        }

    }

    public void stopAnim() {
        if(this.valueAnimator != null) {
            this.clearAnimation();
            this.valueAnimator.setRepeatCount(0);
            this.valueAnimator.cancel();
            this.valueAnimator.end();
            this.mAnimatedValue = 0.0F;
            this.mStep = 1;
            this.invalidate();
        } else {
            this.mAnimatedValue = 0.0F;
            this.mStep = 1;
            this.mValue = 100;
            this.invalidate();
        }

    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        if(this.mAnimatedValue > 0.0F && this.mAnimatedValue <= 0.25F) {
            this.mStep = 1;
        } else if(this.mAnimatedValue > 0.25F && this.mAnimatedValue <= 0.5F) {
            this.mStep = 2;
        } else if(this.mAnimatedValue > 0.5F && this.mAnimatedValue <= 0.75F) {
            this.mStep = 3;
        } else if(this.mAnimatedValue > 0.75F && this.mAnimatedValue <= 1.0F) {
            this.mStep = 4;
        }

        this.invalidate();
    }

    protected void OnAnimationRepeat(Animator animation) {
    }

    protected int OnStopAnim() {
        return 0;
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

