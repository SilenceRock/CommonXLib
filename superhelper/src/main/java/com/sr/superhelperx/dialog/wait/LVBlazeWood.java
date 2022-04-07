package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 15:27.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVBlazeWood extends LVBase {
    private int mWidth;
    private Paint mPaintBg;
    private Paint mPaintWood;
    private Paint mPaintFire;
    private RectF rectFBg;
    private RectF rectFWood;
    private int mPadding;
    private int woodWidth;
    private int woodLength;
    private Bitmap wood;
    RectF rectFire0 = new RectF();
    RectF rectFire1 = new RectF();
    RectF rectFire2 = new RectF();
    RectF rectFire3 = new RectF();
    ArgbEvaluator evaluator;
    private float mAnimatedValue = 0.5F;

    public LVBlazeWood(Context context) {
        super(context);
    }

    public LVBlazeWood(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVBlazeWood(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        this.evaluator = new ArgbEvaluator();
        this.mPadding = this.dip2px(1.0F);
        this.mPaintBg = new Paint();
        this.mPaintBg.setAntiAlias(true);
        this.mPaintBg.setStyle(Paint.Style.FILL);
        this.mPaintBg.setColor(-16777216);
        this.mPaintWood = new Paint();
        this.mPaintWood.setAntiAlias(true);
        this.mPaintWood.setStyle(Paint.Style.FILL);
        this.mPaintWood.setColor(Color.rgb(122, 57, 47));
        this.mPaintFire = new Paint();
        this.mPaintFire.setAntiAlias(true);
        this.mPaintFire.setStyle(Paint.Style.FILL);
        this.mPaintFire.setColor(Color.rgb(232, 132, 40));
    }

    private Bitmap getWood() {
        if(this.wood != null) {
            return this.wood;
        } else {
            this.wood = Bitmap.createBitmap(this.getMeasuredWidth(), this.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = null;
            canvas = new Canvas(this.wood);
            canvas.rotate(-18.0F, this.rectFWood.centerX(), this.rectFWood.centerY());
            this.mPaintWood.setColor(Color.rgb(97, 46, 37));
            canvas.drawRoundRect(this.rectFWood, (float)this.woodWidth / 5.0F, (float)this.woodWidth / 5.0F, this.mPaintWood);
            canvas.rotate(36.0F, this.rectFWood.centerX(), this.rectFWood.centerY());
            this.mPaintWood.setColor(Color.rgb(102, 46, 37));
            canvas.drawRoundRect(this.rectFWood, (float)this.woodWidth / 5.0F, (float)this.woodWidth / 5.0F, this.mPaintWood);
            return this.wood;
        }
    }

    private void drawFire0(Canvas canvas) {
        int color = ((Integer)this.evaluator.evaluate(this.mAnimatedValue, Integer.valueOf(Color.rgb(255, 220, 1)), Integer.valueOf(Color.rgb(240, 169, 47)))).intValue();
        this.mPaintFire.setColor(color);
        Path pathFire = new Path();
        RectF rectFire0 = new RectF();
        rectFire0.top = this.rectFire0.centerY() - (this.rectFire1.height() / 2.0F - this.rectFire0.height() / 2.0F) * this.mAnimatedValue - (this.rectFire0.centerY() - this.rectFire1.centerY()) * this.mAnimatedValue;
        rectFire0.bottom = this.rectFire0.centerY() + (this.rectFire1.height() / 2.0F - this.rectFire0.height() / 2.0F) * this.mAnimatedValue - (this.rectFire0.centerY() - this.rectFire1.centerY()) * this.mAnimatedValue;
        rectFire0.left = this.rectFire0.centerX() - (this.rectFire1.width() / 2.0F - this.rectFire0.width() / 2.0F) * this.mAnimatedValue - this.rectFire1.width() / 5.0F * this.mAnimatedValue;
        rectFire0.right = this.rectFire0.centerX() + (this.rectFire1.width() / 2.0F - this.rectFire0.width() / 2.0F) * this.mAnimatedValue - this.rectFire1.width() / 5.0F * this.mAnimatedValue;
        pathFire.moveTo(rectFire0.centerX(), rectFire0.top);
        pathFire.lineTo(rectFire0.right, rectFire0.centerY());
        pathFire.lineTo(rectFire0.centerX(), rectFire0.bottom);
        pathFire.lineTo(rectFire0.left, rectFire0.centerY());
        pathFire.close();
        canvas.drawPath(pathFire, this.mPaintFire);
    }

    private void drawFire1(Canvas canvas) {
        int color = ((Integer)this.evaluator.evaluate(this.mAnimatedValue, Integer.valueOf(Color.rgb(240, 169, 47)), Integer.valueOf(Color.rgb(232, 132, 40)))).intValue();
        this.mPaintFire.setColor(color);
        Path pathFire = new Path();
        RectF rectFire1 = new RectF();
        rectFire1.top = this.rectFire1.centerY() - this.rectFire1.height() / 2.0F - (this.rectFire2.height() / 2.0F - this.rectFire1.height() / 2.0F) * this.mAnimatedValue - (this.rectFire1.centerY() - this.rectFire2.centerY()) * this.mAnimatedValue;
        rectFire1.bottom = this.rectFire1.centerY() + this.rectFire1.height() / 2.0F + (this.rectFire2.height() / 2.0F - this.rectFire1.height() / 2.0F) * this.mAnimatedValue - (this.rectFire1.centerY() - this.rectFire2.centerY()) * this.mAnimatedValue;
        rectFire1.left = this.rectFire1.centerX() - this.rectFire1.width() / 2.0F - (this.rectFire2.width() / 2.0F - this.rectFire1.width() / 2.0F) * this.mAnimatedValue + this.rectFire1.width() / 5.0F * this.mAnimatedValue;
        rectFire1.right = this.rectFire1.centerX() + this.rectFire1.width() / 2.0F + (this.rectFire2.width() / 2.0F - this.rectFire1.width() / 2.0F) * this.mAnimatedValue + this.rectFire1.width() / 5.0F * this.mAnimatedValue;
        pathFire.moveTo(rectFire1.centerX(), rectFire1.top);
        pathFire.lineTo(rectFire1.right, rectFire1.centerY());
        pathFire.lineTo(rectFire1.centerX(), rectFire1.bottom);
        pathFire.lineTo(rectFire1.left, rectFire1.centerY());
        pathFire.close();
        canvas.drawPath(pathFire, this.mPaintFire);
    }

    private void drawFire2(Canvas canvas) {
        int color = ((Integer)this.evaluator.evaluate(this.mAnimatedValue, Integer.valueOf(Color.rgb(232, 132, 40)), Integer.valueOf(Color.rgb(223, 86, 33)))).intValue();
        this.mPaintFire.setColor(color);
        RectF rectFire2 = new RectF();
        rectFire2.bottom = this.rectFire2.centerY() + this.rectFire3.height() / 2.0F + (this.rectFire2.height() / 2.0F - this.rectFire3.height() / 2.0F) * (1.0F - this.mAnimatedValue) - (this.rectFire2.centerY() - this.rectFire3.centerY()) * this.mAnimatedValue;
        rectFire2.top = this.rectFire2.centerY() - this.rectFire3.height() / 2.0F + -(this.rectFire2.height() / 2.0F - this.rectFire3.height() / 2.0F) * (1.0F - this.mAnimatedValue) - (this.rectFire2.centerY() - this.rectFire3.centerY()) * this.mAnimatedValue;
        rectFire2.left = this.rectFire2.centerX() - this.rectFire3.width() / 2.0F + -(this.rectFire2.height() / 2.0F - this.rectFire3.width() / 2.0F) * (1.0F - this.mAnimatedValue) + this.rectFire3.width() / 3.0F * this.mAnimatedValue;
        rectFire2.right = this.rectFire2.centerX() + this.rectFire3.width() / 2.0F + (this.rectFire2.height() / 2.0F - this.rectFire3.width() / 2.0F) * (1.0F - this.mAnimatedValue) + this.rectFire3.width() / 3.0F * this.mAnimatedValue;
        Path pathFire = new Path();
        pathFire.moveTo(rectFire2.centerX(), rectFire2.top);
        pathFire.lineTo(rectFire2.right, rectFire2.centerY());
        pathFire.lineTo(rectFire2.centerX(), rectFire2.bottom);
        pathFire.lineTo(rectFire2.left, rectFire2.centerY());
        pathFire.close();
        canvas.drawPath(pathFire, this.mPaintFire);
    }

    private void drawFire3(Canvas canvas) {
        this.mPaintFire.setColor(Color.rgb(223, 86, 33));
        RectF rectFire3 = new RectF();
        rectFire3.bottom = this.rectFire3.centerY() + this.rectFire3.height() / 2.0F * (1.0F - this.mAnimatedValue) - this.rectFire3.height() * 0.75F * this.mAnimatedValue;
        rectFire3.top = this.rectFire3.centerY() - this.rectFire3.height() / 2.0F * (1.0F - this.mAnimatedValue) - this.rectFire3.height() * 0.75F * this.mAnimatedValue;
        rectFire3.left = this.rectFire3.centerX() - this.rectFire3.height() / 2.0F * (1.0F - this.mAnimatedValue) - this.rectFire3.width() / 3.0F * this.mAnimatedValue;
        rectFire3.right = this.rectFire3.centerX() + this.rectFire3.height() / 2.0F * (1.0F - this.mAnimatedValue) - this.rectFire3.width() / 3.0F * this.mAnimatedValue;
        Path pathFire = new Path();
        pathFire.moveTo(rectFire3.centerX(), rectFire3.top);
        pathFire.lineTo(rectFire3.right, rectFire3.centerY());
        pathFire.lineTo(rectFire3.centerX(), rectFire3.bottom);
        pathFire.lineTo(rectFire3.left, rectFire3.centerY());
        pathFire.close();
        canvas.drawPath(pathFire, this.mPaintFire);
    }

    private void initFire() {
        this.rectFire3.bottom = this.rectFBg.centerY() + (float)(this.woodLength / 5) - (float)(this.woodLength / 4);
        this.rectFire3.top = this.rectFBg.centerY() - (float)(this.woodLength / 5) - (float)(this.woodLength / 4);
        this.rectFire3.left = this.rectFBg.centerX() - (float)(this.woodLength / 5);
        this.rectFire3.right = this.rectFBg.centerX() + (float)(this.woodLength / 5);
        this.rectFire3.left += this.rectFire3.width() / 3.0F;
        this.rectFire3.right += this.rectFire3.width() / 3.0F;
        this.rectFire2.bottom = this.rectFBg.centerY() + (float)(this.woodLength / 3);
        this.rectFire2.top = this.rectFBg.centerY() - (float)(this.woodLength / 3);
        this.rectFire2.left = this.rectFBg.centerX() - (float)(this.woodLength / 3);
        this.rectFire2.right = this.rectFBg.centerX() + (float)(this.woodLength / 3);
        this.rectFire1.bottom = this.rectFBg.centerY() + (float)(this.woodLength / 4) + (float)(this.woodLength / 4);
        this.rectFire1.top = this.rectFBg.centerY() - (float)(this.woodLength / 4) + (float)(this.woodLength / 4);
        this.rectFire1.left = this.rectFBg.centerX() - (float)(this.woodLength / 4);
        this.rectFire1.right = this.rectFBg.centerX() + (float)(this.woodLength / 4);
        this.rectFire1.left -= this.rectFire1.width() / 5.0F;
        this.rectFire1.right -= this.rectFire1.width() / 5.0F;
        this.rectFire0.bottom = this.rectFWood.centerY() + this.rectFWood.height() / 2.0F;
        this.rectFire0.top = this.rectFWood.centerY() - this.rectFWood.height() / 2.0F;
        this.rectFire0.left = this.rectFWood.centerX() - this.rectFWood.height() / 2.0F;
        this.rectFire0.right = this.rectFWood.centerX() + this.rectFWood.height() / 2.0F;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        this.rectFBg = new RectF((float)(this.getMeasuredWidth() / 2 - this.mWidth / 2 + this.mPadding), (float)(this.getMeasuredHeight() / 2 - this.mWidth / 2 + this.mPadding), (float)(this.getMeasuredWidth() / 2 + this.mWidth / 2 - this.mPadding), (float)(this.getMeasuredHeight() / 2 + this.mWidth / 2 - this.mPadding));
        this.woodWidth = (int)(this.rectFBg.height() / 12.0F);
        this.woodLength = (int)(this.rectFBg.width() / 3.0F * 2.0F);
        this.rectFWood = new RectF();
        this.rectFWood.bottom = this.rectFBg.bottom - (float)(this.woodWidth * 2);
        this.rectFWood.top = this.rectFBg.bottom - (float)(this.woodWidth * 3);
        this.rectFWood.left = this.rectFBg.centerX() - (float)this.woodLength / 2.0F;
        this.rectFWood.right = this.rectFBg.centerX() + (float)this.woodLength / 2.0F;
        this.initFire();
        if(this.valueAnimator != null) {
            this.drawFire3(canvas);
            this.drawFire2(canvas);
            this.drawFire1(canvas);
            this.drawFire0(canvas);
        }

        canvas.drawBitmap(this.getWood(), 0.0F, 0.0F, this.mPaintBg);
        canvas.restore();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode == -2147483648 && heightSpecMode == -2147483648) {
            this.setMeasuredDimension(this.dip2px(30.0F), this.dip2px(30.0F));
        } else if(widthSpecMode == -2147483648) {
            this.setMeasuredDimension(heightSpecSize, heightSpecSize);
        } else if(heightSpecMode == -2147483648) {
            this.setMeasuredDimension(widthSpecSize, widthSpecSize);
        }

        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = this.getMeasuredHeight();
        } else {
            this.mWidth = this.getMeasuredWidth();
        }

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w > h) {
            this.mWidth = h;
        } else {
            this.mWidth = w;
        }

    }

    public float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    public float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    public int dip2px(float dpValue) {
        float scale = this.getContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
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
        this.mAnimatedValue = 0.25F;
        this.valueAnimator = null;
        this.postInvalidate();
        return 1;
    }

    protected int SetAnimRepeatMode() {
        return 1;
    }

    public boolean isAnimatorRunning() {
        return this.valueAnimator == null?false:this.valueAnimator.isRunning();
    }

    protected void AinmIsRunning() {
    }

    protected int SetAnimRepeatCount() {
        return -1;
    }
}

