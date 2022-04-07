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
 * Created by Hang.Yang on 2018/8/17 15:30.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVFunnyBar extends LVBase {
    private Paint mPaintLeftTop;
    private Paint mPaintLeftLeft;
    private Paint mPaintLeftRight;
    private Paint mPaintRightTop;
    private Paint mPaintRightLeft;
    private Paint mPaintRightRight;
    private int mWidth = 0;
    private int mHeight = 0;
    private float mAnimatedValue = 1.0F;

    public LVFunnyBar(Context context) {
        super(context);
    }

    public LVFunnyBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVFunnyBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float height = (float)MeasureSpec.getSize(heightMeasureSpec);
        float width = (float)MeasureSpec.getSize(widthMeasureSpec);
        height = (float)((double)width / Math.sqrt(3.0D));
        this.setMeasuredDimension((int)width, (int)height);
        this.mWidth = this.getMeasuredWidth();
        this.mHeight = this.getMeasuredHeight();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        float wspace = (float)this.mWidth / 8.0F;
        float hspace = (float)this.mHeight / 8.0F;
        Path p = new Path();
        float leftLong = 0.1F;
        float rightLong = 0.1F;

        for(int i = 0; i < 3; ++i) {
            leftLong = this.mAnimatedValue * (1.0F + (float)i / 4.0F);
            if(leftLong > 1.0F) {
                leftLong = 1.0F;
            }

            float wlong = (float)(this.mWidth / 2) * leftLong - wspace / 2.0F;
            float hlong = (float)(this.mHeight / 2) * leftLong - hspace / 2.0F;
            if(wlong < wspace / 8.0F / 8.0F / 2.0F) {
                wlong = wspace / 8.0F / 8.0F / 2.0F;
            }

            if(hlong < hspace / 8.0F / 8.0F / 2.0F) {
                hlong = wspace / 8.0F / 8.0F / 2.0F;
            }

            p.reset();
            p.moveTo(((float)i + 0.5F) * wspace, (float)(this.mHeight / 2) + (float)i * hspace);
            p.lineTo(((float)i + 1.0F) * wspace + wlong, (float)(this.mHeight / 2) - hspace / 2.0F + (float)i * hspace - hlong);
            p.lineTo(((float)i + 1.5F) * wspace + wlong, (float)(this.mHeight / 2) + (float)i * hspace - hlong);
            p.lineTo(((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace);
            p.close();
            canvas.drawPath(p, this.mPaintLeftTop);
            p.reset();
            p.moveTo(((float)i + 0.5F) * wspace, (float)(this.mHeight / 2) + (float)i * hspace);
            p.lineTo(((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace);
            p.lineTo(((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace + hspace);
            p.lineTo(((float)i + 0.5F) * wspace, (float)(this.mHeight / 2) + (float)i * hspace + hspace);
            p.close();
            canvas.drawPath(p, this.mPaintLeftLeft);
            p.reset();
            p.moveTo(((float)i + 1.5F) * wspace + wlong, (float)(this.mHeight / 2) + (float)i * hspace - hlong);
            p.lineTo(((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace);
            p.lineTo(((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace + hspace);
            p.lineTo(((float)i + 1.5F) * wspace + wlong, (float)(this.mHeight / 2) + (float)i * hspace + hspace - hlong);
            p.close();
            canvas.drawPath(p, this.mPaintLeftRight);
            p.reset();
            p.moveTo((float)this.mWidth - ((float)i + 1.5F) * wspace - wlong, (float)(this.mHeight / 2) + (float)i * hspace - hlong);
            p.lineTo((float)this.mWidth - ((float)i + 1.0F) * wspace - wlong, (float)(this.mHeight / 2) - hspace / 2.0F + (float)i * hspace - hlong);
            p.lineTo((float)this.mWidth - ((float)i + 0.5F) * wspace, (float)(this.mHeight / 2) + (float)i * hspace);
            p.lineTo((float)this.mWidth - ((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace);
            p.close();
            canvas.drawPath(p, this.mPaintRightTop);
            p.reset();
            p.moveTo((float)this.mWidth - ((float)i + 1.5F) * wspace - wlong, (float)(this.mHeight / 2) + (float)i * hspace - hlong);
            p.lineTo((float)this.mWidth - ((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace);
            p.lineTo((float)this.mWidth - ((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace + hspace);
            p.lineTo((float)this.mWidth - ((float)i + 1.5F) * wspace - wlong, (float)(this.mHeight / 2) + (float)i * hspace + hspace - hlong);
            p.close();
            canvas.drawPath(p, this.mPaintRightLeft);
            p.reset();
            p.moveTo((float)this.mWidth - ((float)i + 0.5F) * wspace, (float)(this.mHeight / 2) + (float)i * hspace);
            p.lineTo((float)this.mWidth - ((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace);
            p.lineTo((float)this.mWidth - ((float)i + 1.0F) * wspace, (float)(this.mHeight / 2) + hspace / 2.0F + (float)i * hspace + hspace);
            p.lineTo((float)this.mWidth - ((float)i + 0.5F) * wspace, (float)(this.mHeight / 2) + (float)i * hspace + hspace);
            p.close();
            canvas.drawPath(p, this.mPaintRightRight);
        }

        canvas.restore();
    }

    private void drawFire(Canvas canvas) {
        new RectF();
    }

    private void initPaint() {
        this.mPaintLeftTop = new Paint();
        this.mPaintLeftTop.setAntiAlias(true);
        this.mPaintLeftTop.setStyle(Paint.Style.FILL);
        this.mPaintLeftTop.setColor(Color.rgb(234, 167, 107));
        this.mPaintLeftLeft = new Paint();
        this.mPaintLeftLeft.setAntiAlias(true);
        this.mPaintLeftLeft.setStyle(Paint.Style.FILL);
        this.mPaintLeftLeft.setColor(Color.rgb(174, 113, 94));
        this.mPaintLeftRight = new Paint();
        this.mPaintLeftRight.setAntiAlias(true);
        this.mPaintLeftRight.setStyle(Paint.Style.FILL);
        this.mPaintLeftRight.setColor(Color.rgb(138, 97, 85));
        this.mPaintRightTop = new Paint();
        this.mPaintRightTop.setAntiAlias(true);
        this.mPaintRightTop.setStyle(Paint.Style.FILL);
        this.mPaintRightTop.setColor(Color.rgb(234, 167, 107));
        this.mPaintRightLeft = new Paint();
        this.mPaintRightLeft.setAntiAlias(true);
        this.mPaintRightLeft.setStyle(Paint.Style.FILL);
        this.mPaintRightLeft.setColor(Color.rgb(174, 113, 94));
        this.mPaintRightRight = new Paint();
        this.mPaintRightRight.setAntiAlias(true);
        this.mPaintRightRight.setStyle(Paint.Style.FILL);
        this.mPaintRightRight.setColor(Color.rgb(138, 97, 85));
    }

    public void setViewColor(int color) {
        this.mPaintLeftTop.setColor(color);
        this.mPaintRightTop.setColor(color);
        int red = (color & 16711680) >> 16;
        int green = (color & '\uff00') >> 8;
        int blue = color & 255;
        this.mPaintLeftLeft.setColor(Color.rgb(red - 60 > 0?red - 60:0, green - 54 > 0?green - 54:0, blue - 13 > 0?blue - 13:0));
        this.mPaintRightLeft.setColor(Color.rgb(red - 60 > 0?red - 60:0, green - 54 > 0?green - 54:0, blue - 13 > 0?blue - 13:0));
        this.mPaintRightRight.setColor(Color.rgb(red - 96 > 0?red - 96:0, green - 70 > 0?green - 70:0, blue - 22 > 0?blue - 22:0));
        this.mPaintLeftRight.setColor(Color.rgb(red - 96 > 0?red - 96:0, green - 70 > 0?green - 70:0, blue - 22 > 0?blue - 22:0));
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
        this.mAnimatedValue = 1.0F;
        this.postInvalidate();
        return 1;
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
