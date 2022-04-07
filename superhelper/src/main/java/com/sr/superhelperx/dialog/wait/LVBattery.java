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
 * Created by Hang.Yang on 2018/8/17 15:26.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVBattery extends LVBase {
    private float mWidth = 0.0F;
    private float mhigh = 0.0F;
    private float mBatteryWidth;
    private float mBatteryHigh;
    private float mPadding = 0.0F;
    private float mBodyCorner = 0.0F;
    private float mBatterySpace = 0.0F;
    private Paint mPaint;
    private Paint mPaintHead;
    private Paint mPaintValue;
    private LVBattery.BatteryOrientation mBatteryOrientation;
    RectF rectFBody;
    RectF rectHead;
    private boolean mShowNum;
    private float mAnimatedValue;

    public LVBattery(Context context) {
        super(context);
        this.mBatteryOrientation = LVBattery.BatteryOrientation.HORIZONTAL;
        this.rectFBody = null;
        this.rectHead = null;
        this.mShowNum = false;
        this.mAnimatedValue = 0.0F;
    }

    public LVBattery(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mBatteryOrientation = LVBattery.BatteryOrientation.HORIZONTAL;
        this.rectFBody = null;
        this.rectHead = null;
        this.mShowNum = false;
        this.mAnimatedValue = 0.0F;
    }

    public LVBattery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mBatteryOrientation = LVBattery.BatteryOrientation.HORIZONTAL;
        this.rectFBody = null;
        this.rectHead = null;
        this.mShowNum = false;
        this.mAnimatedValue = 0.0F;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
            this.mhigh = (float)this.getMeasuredHeight() * 0.8F;
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
            this.mhigh = (float)this.getMeasuredWidth() * 0.8F;
        }

    }

    private void drawHead(Canvas canvas) {
        float mHeadwidth = this.mhigh / 6.0F;
        this.rectHead = new RectF(this.mWidth - this.mPadding - mHeadwidth, this.mWidth / 2.0F - mHeadwidth / 2.0F, this.mWidth - this.mPadding, this.mWidth / 2.0F + mHeadwidth / 2.0F);
        canvas.drawArc(this.rectHead, -70.0F, 140.0F, false, this.mPaintHead);
    }

    private void drawBody(Canvas canvas) {
        float mHeadwidth = this.mhigh / 6.0F;
        float x = (float)((double)(mHeadwidth / 2.0F) * Math.cos(-1.2217304763960306D));
        this.rectFBody = new RectF();
        this.rectFBody.top = this.mWidth / 2.0F - this.mhigh / 4.0F + this.mPadding;
        this.rectFBody.bottom = this.mWidth / 2.0F + this.mhigh / 4.0F - this.mPadding;
        this.rectFBody.left = this.mPadding;
        this.rectFBody.right = this.mWidth - this.mPadding - x - x - this.mBatterySpace;
        canvas.drawRoundRect(this.rectFBody, this.mBodyCorner, this.mBodyCorner, this.mPaint);
    }

    private void drawValue(Canvas canvas) {
        RectF rectFBatteryValueFill = new RectF();
        rectFBatteryValueFill.top = this.rectFBody.top + this.mBatterySpace;
        rectFBatteryValueFill.bottom = this.rectFBody.bottom - this.mBatterySpace;
        rectFBatteryValueFill.left = this.mPadding + this.mBatterySpace;
        rectFBatteryValueFill.right = this.rectFBody.right - this.mBatterySpace;
        RectF rectFBatteryValue = new RectF();
        rectFBatteryValue.top = rectFBatteryValueFill.top;
        rectFBatteryValue.bottom = rectFBatteryValueFill.bottom;
        rectFBatteryValue.left = rectFBatteryValueFill.left;
        rectFBatteryValue.right = rectFBatteryValueFill.right * this.mAnimatedValue;
        canvas.drawRoundRect(rectFBatteryValue, 1.0F, 1.0F, this.mPaintValue);
    }

    private void drawLogo(Canvas canvas) {
        this.mPaintHead.setTextSize(this.mhigh / 6.0F);
        Path p;
        if(!this.mShowNum) {
            Path text = new Path();
            text.moveTo(this.mWidth / 2.0F - this.mhigh / 6.0F, this.mWidth / 2.0F - (float)this.dip2px(1.5F));
            text.lineTo(this.mWidth / 2.0F + (float)this.dip2px(2.0F), this.mWidth / 2.0F + this.mhigh / 12.0F);
            text.lineTo(this.mWidth / 2.0F + (float)this.dip2px(1.0F), this.mWidth / 2.0F);
            text.close();
            canvas.drawPath(text, this.mPaintHead);
            p = new Path();
            p.moveTo(this.mWidth / 2.0F - (float)this.dip2px(2.0F), this.mWidth / 2.0F - this.mhigh / 12.0F);
            p.lineTo(this.mWidth / 2.0F + this.mhigh / 6.0F, this.mWidth / 2.0F + (float)this.dip2px(1.5F));
            p.lineTo(this.mWidth / 2.0F - (float)this.dip2px(1.0F), this.mWidth / 2.0F);
            p.close();
            canvas.drawPath(p, this.mPaintHead);
        } else {
            String text1 = (int)(this.mAnimatedValue * 100.0F) + "%";
            if(this.mBatteryOrientation == LVBattery.BatteryOrientation.VERTICAL) {
                p = new Path();
                p.moveTo(this.mWidth / 2.0F, 0.0F);
                p.lineTo(this.mWidth / 2.0F, this.mWidth);
                canvas.drawTextOnPath(text1, p, this.mWidth / 2.0F - this.getFontlength(this.mPaintHead, text1) / 2.0F, this.mWidth / 2.0F - this.mhigh / 2.0F - this.getFontHeight(this.mPaintHead, text1) / 2.0F, this.mPaintHead);
            } else {
                canvas.drawText(text1, this.mWidth / 2.0F - this.getFontlength(this.mPaintHead, text1) / 2.0F, this.mWidth / 2.0F + this.getFontHeight(this.mPaintHead, text1) / 2.0F, this.mPaintHead);
            }
        }

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.mBatteryOrientation == LVBattery.BatteryOrientation.VERTICAL) {
            canvas.rotate(270.0F, this.mWidth / 2.0F, this.mWidth / 2.0F);
        } else {
            canvas.rotate(0.0F, this.mWidth / 2.0F, this.mWidth / 2.0F);
        }

        canvas.save();
        this.drawHead(canvas);
        this.drawBody(canvas);
        this.drawValue(canvas);
        this.drawLogo(canvas);
        canvas.restore();
    }

    private void initPaint() {
        this.mBatteryHigh = (float)this.dip2px(20.0F);
        this.mPadding = (float)this.dip2px(2.0F);
        this.mBodyCorner = (float)this.dip2px(1.0F);
        this.mBatterySpace = (float)this.dip2px(1.0F);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaintHead = new Paint();
        this.mPaintHead.setAntiAlias(true);
        this.mPaintHead.setStyle(Paint.Style.FILL);
        this.mPaintHead.setColor(-1);
        this.mPaintValue = new Paint();
        this.mPaintValue.setAntiAlias(true);
        this.mPaintValue.setStyle(Paint.Style.FILL);
        this.mPaintValue.setColor(Color.rgb(67, 213, 81));
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.mPaintHead.setColor(color);
        this.postInvalidate();
    }

    public void setCellColor(int color) {
        this.mPaintValue.setColor(color);
        this.postInvalidate();
    }

    public void setValue(int value) {
        this.mAnimatedValue = (float)value * 1.0F / 100.0F;
        this.invalidate();
    }

    public void setShowNum(boolean show) {
        this.mShowNum = show;
        this.invalidate();
    }

    public void setBatteryOrientation(LVBattery.BatteryOrientation batteryOrientation) {
        this.mBatteryOrientation = batteryOrientation;
        this.invalidate();
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

    protected int SetAnimRepeatCount() {
        return -1;
    }

    protected int OnStopAnim() {
        this.mAnimatedValue = 0.0F;
        this.postInvalidate();
        return 1;
    }

    protected void AinmIsRunning() {
    }

    protected int SetAnimRepeatMode() {
        return 1;
    }

    public static enum BatteryOrientation {
        VERTICAL,
        HORIZONTAL;

        private BatteryOrientation() {
        }
    }
}
