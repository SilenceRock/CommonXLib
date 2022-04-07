package com.sr.superhelperx.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Hang.Yang on 2018/8/17 16:32.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppSideBar extends View {
    private AppSideBar.OnLetterCallBack onLetterCallBack;
    private String[] strings = this.getTextArray();
    private float itemHeight = -1.0F;
    private Bitmap letterBitmap;
    private Paint paint;

    public AppSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        if(this.itemHeight == -1.0F) {
            this.itemHeight = (float)(this.getHeight() / this.strings.length);
        }

        if(this.paint == null) {
            this.paint = new Paint();
            this.paint.setFlags(1);
            this.paint.setTextSize((float)this.getTextSize());
            this.paint.setColor(this.getTextColor());
            Canvas mCanvas = new Canvas();
            this.letterBitmap = Bitmap.createBitmap(this.getMeasuredWidth(), this.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            mCanvas.setBitmap(this.letterBitmap);
            float widthCenter = (float)this.getMeasuredWidth() / 2.0F;

            for(int i = 0; i < this.strings.length; ++i) {
                mCanvas.drawText(this.strings[i], widthCenter - this.paint.measureText(this.strings[i]) / 2.0F, this.itemHeight * (float)i + this.itemHeight, this.paint);
            }
        }

        if(this.letterBitmap != null) {
            canvas.drawBitmap(this.letterBitmap, 0.0F, 0.0F, this.paint);
        }

        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch(event.getAction()) {
            case 0:
            case 2:
                int position = (int)(event.getY() / this.itemHeight);
                if(position >= 0 && position < this.strings.length) {
                    try {
                        this.onLetterCallBack.onLetter(this.strings[position], position);
                    } catch (Exception var4) {
                        ;
                    }
                }

                return true;
            case 1:
            case 4:
                this.onLetterCallBack.onActionUp();
                return true;
            case 3:
            default:
                return false;
        }
    }

    public void setOnLetterTouchListener(AppSideBar.OnLetterCallBack onLetterCallBack) {
        this.onLetterCallBack = onLetterCallBack;
    }

    public abstract String[] getTextArray();

    public abstract int getTextColor();

    public abstract int getTextSize();

    public interface OnLetterCallBack {
        void onLetter(String var1, int var2);

        void onActionUp();
    }
}
