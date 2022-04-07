package com.sr.superhelperx.util;

import android.annotation.TargetApi;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 16:23.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilKeyBoard {
    private UtilKeyBoard() {
    }

    @TargetApi(3)
    public static void openKeybord(EditText editText) {
        try {
            editText.findFocus();
        } catch (Exception var2) {
            ;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) AppApplication.AINSTANCE.getSystemService("input_method");
        inputMethodManager.showSoftInput(editText, 2);
        inputMethodManager.toggleSoftInput(2, 1);
    }

    @TargetApi(3)
    public static void closeKeybord(EditText editText) {
        try {
            editText.clearFocus();
        } catch (Exception var2) {
            ;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) AppApplication.AINSTANCE.getSystemService("input_method");
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
