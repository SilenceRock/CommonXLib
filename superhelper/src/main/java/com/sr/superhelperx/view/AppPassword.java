package com.sr.superhelperx.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.sr.superhelperx.util.UtilToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hang.Yang on 2018/8/17 16:31.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi"})
public class AppPassword extends EditText {
    public AppPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackground((Drawable)null);
        this.setSingleLine(true);
        this.setKeyListener(DigitsKeyListener.getInstance("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM"));
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20), new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}\':;\',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";
                Log.e("filter", source.toString());
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                return matcher.find()?"":null;
            }
        }});
        this.setInputType(128);
        this.isPassword(true);
    }

    public void isPassword(boolean isPassword) {
        this.setTransformationMethod((TransformationMethod)(isPassword? PasswordTransformationMethod.getInstance(): HideReturnsTransformationMethod.getInstance()));
        this.setSelection(this.getText().toString().length());
    }

    public String getTextString(AppPassword appPassword) throws Exception {
        String string = super.getText().toString();
        if(string.length() == 0) {
            UtilToast.show("密码不能为空");
            throw new Exception("");
        } else if(string.length() < 6) {
            UtilToast.show("密码不能小于6位");
            throw new Exception("");
        } else if(appPassword != null && !string.equals(appPassword.getTextString((AppPassword)null))) {
            UtilToast.show("两次密码输入不符");
            throw new Exception("");
        } else {
            return string;
        }
    }
}