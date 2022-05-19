package com.sr.commonlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

/**
 * FileName: CustomTextView
 * Author: hangY
 * Date: 2022/3/25 15:53
 * Description:
 */
@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {

    private GradientDrawable gd_background;

    private boolean openFocusMode;

    private boolean openParentFocusMode;

    private int parentFocusLevel = 1;

    private int backgroundColor = 0;

    private int backgroundColorFocused = 0;

    private int textColorNormal = 0;

    private int textColorFocused = 0;

    private float scaleDouble = 0F;

    private int scaleDuration = 0;

    private float scaleWidth = 0F;

    private float scaleHeight = 0F;

    private int cornerRadius = 0;

    private int cornerLeftTopRadius = 0;

    private int cornerLeftBottomRadius = 0;

    private int cornerRightTopRadius = 0;

    private int cornerRightBottomRadius = 0;

    private int cornerRadiusFocused = 0;

    private int cornerLeftTopRadiusFocused = 0;

    private int cornerLeftBottomRadiusFocused = 0;

    private int cornerRightTopRadiusFocused = 0;

    private int cornerRightBottomRadiusFocused = 0;

    private int strokeWidth = 0;

    private int strokeWidthFocused = 0;

    private int strokeColor = 0;

    private int strokeColorFocused = 0;

    private int backgroundNormal = 0;

    private int backgroundFocused = 0;

    private boolean isRadiusHalfHeight;

    private boolean isWidthHeightEqual;

    private boolean isDrawableCenter;

    private boolean interceptLeftKey;

    private boolean interceptRightKey;

    private boolean interceptUpKey;

    private boolean interceptDownKey;

    private ViewGroup parentFocusView;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainAttributes(context,attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void obtainAttributes(Context context, @Nullable AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);

        openFocusMode = ta.getBoolean(R.styleable.CustomTextView_tv_openFocusMode, false);
        openParentFocusMode = ta.getBoolean(R.styleable.CustomTextView_tv_openParentFocusMode, false);
        parentFocusLevel = ta.getInteger(R.styleable.CustomTextView_tv_parentFocusLevel, 1);
        scaleDouble = ta.getFloat(R.styleable.CustomTextView_tv_scaleDouble, 0F);
        scaleDuration = ta.getInteger(R.styleable.CustomTextView_tv_scaleDuration, 0);
        scaleWidth = ta.getFloat(R.styleable.CustomTextView_tv_scaleWidth, 0F);
        scaleHeight = ta.getFloat(R.styleable.CustomTextView_tv_scaleHeight, 0F);
        backgroundColor = ta.getColor(R.styleable.CustomTextView_tv_backgroundColor, Color.TRANSPARENT);
        backgroundColorFocused = ta.getColor(R.styleable.CustomTextView_tv_backgroundColorFocused, Color.TRANSPARENT);
        textColorNormal = ta.getColor(R.styleable.CustomTextView_tv_textColor, getCurrentTextColor());
        textColorFocused = ta.getColor(R.styleable.CustomTextView_tv_textColorFocused, Color.TRANSPARENT);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerRadius, 0);
        cornerLeftTopRadius = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerLeftTopRadius, 0);
        cornerLeftBottomRadius = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerLeftBottomRadius, 0);
        cornerRightTopRadius = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerRightTopRadius, 0);
        cornerRightBottomRadius = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerRightBottomRadius, 0);
        cornerRadiusFocused = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerRadiusFocused, 0);
        cornerLeftTopRadiusFocused = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerLeftTopRadiusFocused, 0);
        cornerLeftBottomRadiusFocused = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerLeftBottomRadiusFocused, 0);
        cornerRightTopRadiusFocused = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerRightTopRadiusFocused, 0);
        cornerRightBottomRadiusFocused = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_cornerRightBottomRadiusFocused, 0);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_strokeWidth, 0);
        strokeWidthFocused = ta.getDimensionPixelSize(R.styleable.CustomTextView_tv_strokeWidthFocused, 0);
        strokeColor = ta.getColor(R.styleable.CustomTextView_tv_strokeColor, Color.TRANSPARENT);
        strokeColorFocused = ta.getColor(R.styleable.CustomTextView_tv_strokeColorFocused, Color.TRANSPARENT);
        isRadiusHalfHeight = ta.getBoolean(R.styleable.CustomTextView_tv_isRadiusHalfHeight, false);
        isWidthHeightEqual = ta.getBoolean(R.styleable.CustomTextView_tv_isWidthHeightEqual, false);
        isDrawableCenter = ta.getBoolean(R.styleable.CustomTextView_tv_isDrawableCenter, false);
        backgroundNormal = ta.getResourceId(R.styleable.CustomTextView_tv_backgroundNormal, 0);
        backgroundFocused = ta.getResourceId(R.styleable.CustomTextView_tv_backgroundFocused, 0);

        interceptLeftKey = ta.getBoolean(R.styleable.CustomTextView_tv_interceptLeftKey, false);
        interceptRightKey = ta.getBoolean(R.styleable.CustomTextView_tv_interceptRightKey, false);
        interceptUpKey = ta.getBoolean(R.styleable.CustomTextView_tv_interceptUpKey, false);
        interceptDownKey = ta.getBoolean(R.styleable.CustomTextView_tv_interceptDownKey, false);

        ta.recycle();

        init();
    }

    private void init(){
        if (openFocusMode){
            setFocusable(true);
            setFocusableInTouchMode(true);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isWidthHeightEqual() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(),getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isDrawableCenter){
            Drawable[] drawables = getCompoundDrawables();
            Drawable drawableLeft = drawables[0];
            Drawable drawableRight = drawables[2];
            if (drawableLeft != null){
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0F);
            }else if (drawableRight != null){
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = drawableRight.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate(-((getWidth() - bodyWidth) / 2), 0F);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (isRadiusHalfHeight()) {
            setCornerRadius(getHeight() / 2);
        } else {
            setBgSelector();
        }
        if (openParentFocusMode && getParent() != null && parentFocusView == null){
            ViewGroup parentView = (ViewGroup) getParent();
            while (parentFocusLevel > 1){
                if (parentView.getParent() != null){
                    parentView = (ViewGroup) parentView.getParent();
                }
                --parentFocusLevel;
            }
            parentFocusView = parentView;
//            if (parentFocusView instanceof CustomLinearLayout){
//                ((CustomLinearLayout) parentFocusView).setOnFocusListener(hasFocus -> setBgSelector());
//            }else if (parentFocusView instanceof CustomRelativeLayout){
//                ((CustomRelativeLayout) parentFocusView).setOnFocusListener(hasFocus -> setBgSelector());
//            }
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (openFocusMode){
            setBgSelector();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (interceptUpKey && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP
                || interceptDownKey && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN
                || interceptLeftKey && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT
                || interceptRightKey && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT){
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void  setInterceptKey(Boolean leftKey, Boolean upKey, Boolean rightKey, Boolean downKey){
        interceptLeftKey = leftKey;
        interceptUpKey = upKey;
        interceptRightKey = rightKey;
        interceptDownKey = downKey;
    }

    public void setDrawableCenter(boolean isCenter){
        this.isDrawableCenter = isCenter;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBgSelector();
    }

    public void setNormalTextColor(int normalTextColor){
        this.textColorNormal = normalTextColor;
        setBgSelector();
    }

    public void setFocusTextColor(int focusTextColor){
        this.textColorFocused = focusTextColor;
        setBgSelector();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = dp2px(cornerRadius);
        setBgSelector();
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = dp2px(strokeWidth);
        setBgSelector();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        setBgSelector();
    }

    public void setStrokeFocusWidth(int strokeWidthFocused) {
        this.strokeWidthFocused = strokeWidthFocused;
        setBgSelector();
    }

    public void setStrokeFocusColor(int strokeColorFocused) {
        this.strokeColorFocused = strokeColorFocused;
        setBgSelector();
    }

    public void setIsRadiusHalfHeight(boolean isRadiusHalfHeight) {
        this.isRadiusHalfHeight = isRadiusHalfHeight;
        setBgSelector();
    }

    public void setIsWidthHeightEqual(boolean isWidthHeightEqual) {
        this.isWidthHeightEqual = isWidthHeightEqual;
        setBgSelector();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public int getStrokeFocusWidth() {
        return strokeWidthFocused;
    }

    public int getStrokeFocusColor() {
        return strokeColorFocused;
    }

    public boolean isRadiusHalfHeight() {
        return isRadiusHalfHeight;
    }

    public boolean isWidthHeightEqual() {
        return isWidthHeightEqual;
    }

    public int dp2px(float dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int sp2px(float sp) {
        float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    private void setDrawable(GradientDrawable gd, int color, int strokeColor) {
        gd.setColor(color);
        if (cornerRadius > 0){
            gd.setCornerRadius(cornerRadius);
        }else {
            gd.setCornerRadii(new float[]{cornerLeftTopRadius,cornerLeftTopRadius,cornerRightTopRadius,cornerRightTopRadius,cornerRightBottomRadius,cornerRightBottomRadius,cornerLeftBottomRadius,cornerLeftBottomRadius});
        }
        gd.setStroke(strokeWidth, strokeColor);
    }

    private void setDrawable() {
        gd_background = new GradientDrawable();
        if (openFocusMode){
            if (isFocused()){
                if (backgroundFocused != 0){
                    setBackgroundResource(backgroundFocused);
                    return;
                }else {
                    gd_background.setColor(backgroundColorFocused);
                    if (cornerRadiusFocused > 0){
                        gd_background.setCornerRadius(cornerRadiusFocused);
                    }else {
                        gd_background.setCornerRadii(new float[]{cornerLeftTopRadiusFocused,cornerLeftTopRadiusFocused,cornerRightTopRadiusFocused,cornerRightTopRadiusFocused,cornerRightBottomRadiusFocused,cornerRightBottomRadiusFocused,cornerLeftBottomRadiusFocused,cornerLeftBottomRadiusFocused});
                    }
                    gd_background.setStroke(strokeWidthFocused, strokeColorFocused);
                    if (textColorFocused != 0){
                        setTextColor(textColorFocused);
                    }
                    if (scaleDuration > 0 && scaleDouble > 1.0){
                        ViewCompat.animate(this)
                                .setDuration(scaleDuration)
                                .scaleX(scaleDouble)
                                .scaleY(scaleDouble)
                                .withStartAction(this::bringToFront)
                                .start();
                    }
                }
            }else {
                if (backgroundNormal != 0){
                    setBackgroundResource(backgroundNormal);
                    return;
                }else {
                    gd_background.setColor(backgroundColor);
                    if (cornerRadius > 0){
                        gd_background.setCornerRadius(cornerRadius);
                    }else {
                        gd_background.setCornerRadii(new float[]{cornerLeftTopRadius,cornerLeftTopRadius,cornerRightTopRadius,cornerRightTopRadius,cornerRightBottomRadius,cornerRightBottomRadius,cornerLeftBottomRadius,cornerLeftBottomRadius});
                    }
                    gd_background.setStroke(strokeWidth, strokeColor);
                    if (textColorNormal != 0){
                        setTextColor(textColorNormal);
                    }
                    if (scaleDuration > 0 && scaleDouble > 1.0){
                        ViewCompat.animate(this)
                                .setDuration(scaleDuration)
                                .scaleX(1F)
                                .scaleY(1F)
                                .start();
                    }
                }
            }
        }else if (openParentFocusMode && parentFocusView != null){
            if (parentFocusView.isFocused()){
                if (backgroundFocused != 0){
                    setBackgroundResource(backgroundFocused);
                    return;
                }else {
                    gd_background.setColor(backgroundColorFocused);
                    if (cornerRadiusFocused > 0){
                        gd_background.setCornerRadius(cornerRadiusFocused);
                    }else {
                        gd_background.setCornerRadii(new float[]{cornerLeftTopRadiusFocused,cornerLeftTopRadiusFocused,cornerRightTopRadiusFocused,cornerRightTopRadiusFocused,cornerRightBottomRadiusFocused,cornerRightBottomRadiusFocused,cornerLeftBottomRadiusFocused,cornerLeftBottomRadiusFocused});
                    }
                    gd_background.setStroke(strokeWidthFocused, strokeColorFocused);
                    if (textColorFocused != 0){
                        setTextColor(textColorFocused);
                    }
                    if (scaleDuration > 0 && scaleDouble > 1.0){
                        ViewCompat.animate(this)
                                .setDuration(scaleDuration)
                                .scaleX(scaleDouble)
                                .scaleY(scaleDouble)
                                .withStartAction(this::bringToFront)
                                .start();
                    }
                }
            }else {
                if (backgroundNormal != 0){
                    setBackgroundResource(backgroundNormal);
                    return;
                }else {
                    gd_background.setColor(backgroundColor);
                    if (cornerRadius > 0){
                        gd_background.setCornerRadius(cornerRadius);
                    }else {
                        gd_background.setCornerRadii(new float[]{cornerLeftTopRadius,cornerLeftTopRadius,cornerRightTopRadius,cornerRightTopRadius,cornerRightBottomRadius,cornerRightBottomRadius,cornerLeftBottomRadius,cornerLeftBottomRadius});
                    }
                    gd_background.setStroke(strokeWidth, strokeColor);
                    if (textColorNormal != 0){
                        setTextColor(textColorNormal);
                    }
                    if (scaleDuration > 0 && scaleDouble > 1.0){
                        ViewCompat.animate(this)
                                .setDuration(scaleDuration)
                                .scaleX(1F)
                                .scaleY(1F)
                                .start();
                    }
                }
            }
        }else {
            if (backgroundNormal != 0){
                setBackgroundResource(backgroundNormal);
                return;
            }else {
                gd_background.setColor(backgroundColor);
                if (cornerRadius > 0){
                    gd_background.setCornerRadius(cornerRadius);
                }else {
                    gd_background.setCornerRadii(new float[]{cornerLeftTopRadius,cornerLeftTopRadius,cornerRightTopRadius,cornerRightTopRadius,cornerRightBottomRadius,cornerRightBottomRadius,cornerLeftBottomRadius,cornerLeftBottomRadius});
                }
                gd_background.setStroke(strokeWidth, strokeColor);
            }
        }
        setBackground(gd_background);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void setBgSelector() {
        setDrawable();
    }
}
