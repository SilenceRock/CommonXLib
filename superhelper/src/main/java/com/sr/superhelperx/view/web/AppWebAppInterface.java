package com.sr.superhelperx.view.web;

import android.content.Context;

import com.sr.superhelperx.util.UtilToast;

/**
 * Created by Hang.Yang on 2018/8/17 16:47.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class AppWebAppInterface {
    private Context context;

    public AppWebAppInterface(Context context) {
        this.context = context;
    }

    public void showToast(String toast) {
        UtilToast.show(toast);
    }
}
