package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:32.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVRingProgress extends LVBase {
    private Paint mPaint;
    private Bitmap mBitmapBg;
    private Paint mPaintText;
    private float MaxAngle = 359.0F;
    private int mPadding;
    private int mWidth;
    private RectF rectFBg = new RectF();
    private int Progress = 0;
    int ProStartColor = Color.argb(100, 0, 242, 123);
    int ProEndColor = Color.argb(100, 86, 171, 228);
    private float mAnimatedValue = 0.0F;

    public LVRingProgress(Context context) {
        super(context);
    }

    public LVRingProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVRingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getProgress() {
        return this.Progress;
    }

    public void setProgress(int progress) {
        this.Progress = progress;
        this.invalidate();
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaintText = new Paint();
        this.mPaintText.setAntiAlias(true);
        this.mPaintText.setStyle(Paint.Style.FILL);
        this.mPaintText.setColor(-1);
    }

    public void setViewColor(int color) {
        this.mPaint.setColor(color);
        this.postInvalidate();
    }

    public void setTextColor(int color) {
        this.mPaintText.setColor(color);
        this.postInvalidate();
    }

    public void setPorBarStartColor(int color) {
        this.ProStartColor = color;
    }

    public void setPorBarEndColor(int color) {
        this.ProEndColor = color;
    }

    private Bitmap getmBitmapBg(Paint paint) {
        Canvas canvas = null;
        if(this.mBitmapBg == null) {
            this.mBitmapBg = Bitmap.createBitmap(this.getMeasuredWidth(), this.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(this.mBitmapBg);
            paint.setAntiAlias(true);
            paint.setStrokeWidth((float)this.mPadding);
            paint.setStyle(Paint.Style.STROKE);
            Path pathBg = new Path();
            pathBg.addArc(this.rectFBg, 0.0F, 360.0F);
            paint.setShadowLayer((float)(this.mPadding / 3), 0.0F, (float)(this.mPadding / 4), Color.argb(100, 0, 0, 0));
            canvas.drawPath(pathBg, paint);
        }

        return this.mBitmapBg;
    }

    private void drawBg(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getmBitmapBg(paint), 0.0F, 0.0F, paint);
    }

    private void drawProgress(Canvas canvas, Paint paint, int sweepAngle) {
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStrokeWidth((float)this.mPadding);
        paint.setStyle(Paint.Style.STROKE);
        Path pathProgress = new Path();
        pathProgress.addArc(this.rectFBg, -90.0F, (float)sweepAngle);
        LinearGradient mShader = new LinearGradient(this.rectFBg.left, this.rectFBg.top, this.rectFBg.left, this.rectFBg.bottom, new int[]{this.ProStartColor, this.ProEndColor}, new float[]{0.0F, 1.0F}, Shader.TileMode.CLAMP);
        paint.setShader(mShader);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(pathProgress, paint);
        paint.setShader((Shader)null);
        this.mPaintText.setTextSize(this.mPaint.getStrokeWidth() / 2.0F);
        String text = (int)((float)sweepAngle / this.MaxAngle * 100.0F) + "%";
        canvas.drawTextOnPath(text, pathProgress, (float)(3.141592653589793D * (double)this.rectFBg.width() * (double)((float)sweepAngle / this.MaxAngle) - (double)(this.getFontlength(this.mPaintText, text) * 1.5F)), this.getFontHeight(this.mPaintText) / 3.0F, this.mPaintText);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        this.mPadding = this.mWidth / 10;
        this.rectFBg = new RectF((float)(this.getMeasuredWidth() / 2 - this.mWidth / 2 + this.mPadding), (float)(this.getMeasuredHeight() / 2 - this.mWidth / 2 + this.mPadding), (float)(this.getMeasuredWidth() / 2 + this.mWidth / 2 - this.mPadding), (float)(this.getMeasuredHeight() / 2 + this.mWidth / 2 - this.mPadding));
        this.drawBg(canvas, this.mPaint);
        this.drawProgress(canvas, this.mPaint, (int)(this.MaxAngle / 100.0F * (float)this.getProgress()));
        canvas.restore();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = this.getMeasuredHeight();
        } else {
            this.mWidth = this.getMeasuredWidth();
        }

    }

    protected void InitPaint() {
        this.initPaint();
    }

    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        this.mAnimatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
        this.setProgress((int)(this.mAnimatedValue * 100.0F));
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

    protected int SetAnimRepeatCount() {
        return 0;
    }

    protected void AinmIsRunning() {
    }
}
