package com.sr.superhelperx.dialog.wait;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 15:29.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LVFinePoiStar extends LVBase {
    private float mWidth = 0.0F;
    private float mPadding = 0.0F;
    private Paint mPaintLine;
    private Paint mPaintCircle;
    private int hornCount = 5;
    private List<Point> listPoint = new ArrayList();
    private boolean isDrawPath = true;
    RectF rectF = new RectF();
    float mAnimatedValue = 0.75F;

    public LVFinePoiStar(Context context) {
        super(context);
    }

    public LVFinePoiStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LVFinePoiStar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(this.getMeasuredWidth() > this.getHeight()) {
            this.mWidth = (float)this.getMeasuredHeight();
        } else {
            this.mWidth = (float)this.getMeasuredWidth();
        }

        this.mPadding = (float)this.dip2px(1.0F);
    }

    @SuppressLint({"NewApi"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setLayerType(1, (Paint)null);
        this.listPoint.clear();

        for(int cp = 0; cp < this.hornCount; ++cp) {
            LVFinePoiStar.Point currenttime = this.getPoint(this.mWidth / 2.0F - this.mPadding, (float)(90 - 360 / this.hornCount + 360 / this.hornCount * cp));
            this.listPoint.add(currenttime);
        }

        LVFinePoiStar.Point var4 = null;
        float var5 = this.mAnimatedValue * 10.0F - (float)((int)(this.mAnimatedValue * 10.0F));
        if(this.mAnimatedValue >= 0.0F && this.mAnimatedValue <= 0.1F) {
            var4 = this.drawOneEdge(var5, 1.0F, (LVFinePoiStar.Point)this.listPoint.get(0), (LVFinePoiStar.Point)this.listPoint.get(2));
            if(this.isDrawPath) {
                this.drawPathEdge(canvas, (LVFinePoiStar.Point)this.listPoint.get(0), var4);
            } else {
                canvas.drawCircle(this.mWidth / 2.0F - var4.x, this.mWidth / 2.0F - var4.y, this.mPadding, this.mPaintLine);
            }
        } else if(this.mAnimatedValue > 0.1F && this.mAnimatedValue <= 0.2F) {
            var4 = this.drawOneEdge(var5, 1.0F, (LVFinePoiStar.Point)this.listPoint.get(2), (LVFinePoiStar.Point)this.listPoint.get(4));
            if(this.isDrawPath) {
                this.drawEdge(canvas, 1);
                this.drawPathEdge(canvas, (LVFinePoiStar.Point)this.listPoint.get(2), var4);
            } else {
                canvas.drawCircle(this.mWidth / 2.0F - var4.x, this.mWidth / 2.0F - var4.y, this.mPadding, this.mPaintLine);
            }
        } else if(this.mAnimatedValue > 0.2F && this.mAnimatedValue <= 0.3F) {
            var4 = this.drawOneEdge(var5, 1.0F, (LVFinePoiStar.Point)this.listPoint.get(4), (LVFinePoiStar.Point)this.listPoint.get(1));
            if(this.isDrawPath) {
                this.drawEdge(canvas, 2);
                this.drawPathEdge(canvas, (LVFinePoiStar.Point)this.listPoint.get(4), var4);
            } else {
                canvas.drawCircle(this.mWidth / 2.0F - var4.x, this.mWidth / 2.0F - var4.y, this.mPadding, this.mPaintLine);
            }
        } else if(this.mAnimatedValue > 0.3F && this.mAnimatedValue <= 0.4F) {
            var4 = this.drawOneEdge(var5, 1.0F, (LVFinePoiStar.Point)this.listPoint.get(1), (LVFinePoiStar.Point)this.listPoint.get(3));
            if(this.isDrawPath) {
                this.drawEdge(canvas, 3);
                this.drawPathEdge(canvas, (LVFinePoiStar.Point)this.listPoint.get(1), var4);
            } else {
                canvas.drawCircle(this.mWidth / 2.0F - var4.x, this.mWidth / 2.0F - var4.y, this.mPadding, this.mPaintLine);
            }
        } else if(this.mAnimatedValue > 0.4F && this.mAnimatedValue <= 0.5F) {
            var4 = this.drawOneEdge(var5, 1.0F, (LVFinePoiStar.Point)this.listPoint.get(3), (LVFinePoiStar.Point)this.listPoint.get(0));
            if(this.isDrawPath) {
                this.drawEdge(canvas, 4);
                this.drawPathEdge(canvas, (LVFinePoiStar.Point)this.listPoint.get(3), var4);
            } else {
                canvas.drawCircle(this.mWidth / 2.0F - var4.x, this.mWidth / 2.0F - var4.y, this.mPadding, this.mPaintLine);
            }
        } else if(this.mAnimatedValue > 0.5F && this.mAnimatedValue <= 0.75F) {
            this.drawEdge(canvas, 5);
            this.rectF = new RectF(this.mPadding, this.mPadding, this.mWidth - this.mPadding, this.mWidth - this.mPadding);
            canvas.drawArc(this.rectF, (float)(-180 + (90 - 360 / this.hornCount)), 1440.0F * (this.mAnimatedValue - 0.5F), false, this.mPaintCircle);
        } else {
            this.mPaintCircle.setStrokeWidth((float)this.dip2px(1.5F));
            this.mPaintLine.setShadowLayer(1.0F, 1.0F, 1.0F, -1);
            this.drawEdge(canvas, 5);
            this.rectF = new RectF(this.mPadding, this.mPadding, this.mWidth - this.mPadding, this.mWidth - this.mPadding);
            canvas.drawArc(this.rectF, (float)(-180 + (90 - 360 / this.hornCount)), 360.0F, false, this.mPaintCircle);
        }

        this.mPaintCircle.setStrokeWidth((float)this.dip2px(1.0F));
        this.mPaintLine.setShadowLayer(0.0F, 1.0F, 1.0F, -1);
    }

    private LVFinePoiStar.Point drawOneEdge(float currenttime, float alltime, LVFinePoiStar.Point startP, LVFinePoiStar.Point endP) {
        float x = startP.x - (startP.x - endP.x) / alltime * currenttime;
        float y = startP.y - (startP.y - endP.y) / alltime * currenttime;
        LVFinePoiStar.Point resPoint = new LVFinePoiStar.Point(x, y);
        return resPoint;
    }

    private void drawFirstEdge(Canvas canvas) {
        canvas.drawLine(this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(0)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(0)).y, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(2)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(2)).y, this.mPaintLine);
    }

    private void drawSecondEdge(Canvas canvas) {
        canvas.drawLine(this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(2)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(2)).y, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(4)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(4)).y, this.mPaintLine);
    }

    private void drawThirdEdge(Canvas canvas) {
        canvas.drawLine(this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(4)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(4)).y, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(1)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(1)).y, this.mPaintLine);
    }

    private void drawFourthEdge(Canvas canvas) {
        canvas.drawLine(this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(1)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(1)).y, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(3)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(3)).y, this.mPaintLine);
    }

    private void drawFifthEdge(Canvas canvas) {
        canvas.drawLine(this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(3)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(3)).y, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(0)).x, this.mWidth / 2.0F - ((LVFinePoiStar.Point)this.listPoint.get(0)).y, this.mPaintLine);
    }

    private void drawPathEdge(Canvas canvas, LVFinePoiStar.Point start, LVFinePoiStar.Point end) {
        canvas.drawLine(this.mWidth / 2.0F - start.x, this.mWidth / 2.0F - start.y, this.mWidth / 2.0F - end.x, this.mWidth / 2.0F - end.y, this.mPaintLine);
    }

    private void drawEdge(Canvas canvas, int edgeCount) {
        switch(edgeCount) {
            case 1:
                this.drawFirstEdge(canvas);
                break;
            case 2:
                this.drawFirstEdge(canvas);
                this.drawSecondEdge(canvas);
                break;
            case 3:
                this.drawFirstEdge(canvas);
                this.drawSecondEdge(canvas);
                this.drawThirdEdge(canvas);
                break;
            case 4:
                this.drawFirstEdge(canvas);
                this.drawSecondEdge(canvas);
                this.drawThirdEdge(canvas);
                this.drawFourthEdge(canvas);
                break;
            case 5:
                this.drawFirstEdge(canvas);
                this.drawSecondEdge(canvas);
                this.drawThirdEdge(canvas);
                this.drawFourthEdge(canvas);
                this.drawFifthEdge(canvas);
        }

    }

    private void initPaint() {
        this.mPaintLine = new Paint();
        this.mPaintLine.setAntiAlias(true);
        this.mPaintLine.setStyle(Paint.Style.FILL);
        this.mPaintLine.setColor(-1);
        this.mPaintLine.setStrokeWidth((float)this.dip2px(1.0F));
        this.mPaintCircle = new Paint();
        this.mPaintCircle.setAntiAlias(true);
        this.mPaintCircle.setStyle(Paint.Style.STROKE);
        this.mPaintCircle.setColor(-1);
        this.mPaintCircle.setStrokeWidth((float)this.dip2px(1.0F));
    }

    public void setViewColor(int color) {
        this.mPaintLine.setColor(color);
        this.postInvalidate();
    }

    public void setCircleColor(int color) {
        this.mPaintCircle.setColor(color);
        this.postInvalidate();
    }

    private LVFinePoiStar.Point getPoint(float radius, float angle) {
        float x = (float)((double)radius * Math.cos((double)angle * 3.141592653589793D / 180.0D));
        float y = (float)((double)radius * Math.sin((double)angle * 3.141592653589793D / 180.0D));
        LVFinePoiStar.Point p = new LVFinePoiStar.Point(x, y);
        return p;
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
        this.mAnimatedValue = 0.75F;
        this.postInvalidate();
        return 1;
    }

    protected int SetAnimRepeatMode() {
        return 1;
    }

    public void setDrawPath(boolean isDrawPath) {
        this.isDrawPath = isDrawPath;
    }

    protected void AinmIsRunning() {
    }

    protected int SetAnimRepeatCount() {
        return -1;
    }

    private class Point {
        private float x;
        private float y;

        private Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}