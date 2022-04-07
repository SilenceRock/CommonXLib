package com.sr.superhelperx.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

import com.sr.superhelperx.util.UtilToast;

/**
 * Created by Hang.Yang on 2018/8/17 16:32.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi"})
public class AppVerification extends EditText {
    public AppVerification(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground((Drawable)null);
        this.setSingleLine(true);
        this.setKeyListener(DigitsKeyListener.getInstance("1234567890"));
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        this.setInputType(3);
    }

    public String getTextString() throws Exception {
        String string = super.getText().toString();
        if(string.length() == 0) {
            UtilToast.show("请输入验证码");
            throw new Exception("");
        } else {
            return string;
        }
    }
}

