package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:28.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVCircularRing extends LVBase {
    private Paint mPaint;
    private Paint mPaintPro;
    private float mWidth = 0.0F;
    private float mPadding = 0.0F;
    private float startAngle = 0.0F;
    RectF rectF = new RectF();

    public LVCircularRing(Context context) {
        super(context);
    }

    public LVCircularRing(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVCircularRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
        }

        this.mPadding = 5.0F;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(this.mWidth / 2.0F, this.mWidth / 2.0F, this.mWidth / 2.0F - this.mPadding, this.mPaintPro);
        this.rectF = new RectF(this.mPadding, this.mPadding, this.mWidth - this.mPadding, this.mWidth - this.mPadding);
        canvas.drawArc(this.rectF, this.startAngle, 100.0F, false, this.mPaint);
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth(8.0F);
        this.mPaintPro = new Paint();
        this.mPaintPro.setAntiAlias(true);
        this.mPaintPro.setStyle(Paint.Style.STROKE);
        this.mPaintPro.setColor(Color.argb(100, 255, 255, 255));
        this.mPaintPro.setStrokeWidth(8.0F);
    }

    public void setViewColor(int color) {
        this.mPaintPro.setColor(color);
        this.postInvalidate();
    }

    public void setBarColor(int color) {
        this.mPaint.setColor(color);
        this.postInvalidate();
    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        float value = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        this.startAngle = 360.0F * value;
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
