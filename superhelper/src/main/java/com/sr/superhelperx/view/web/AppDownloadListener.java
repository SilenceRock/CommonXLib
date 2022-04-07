package com.sr.superhelperx.view.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/**
 * Created by Hang.Yang on 2018/8/17 16:47.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class AppDownloadListener implements DownloadListener {
    private Context context;

    public AppDownloadListener(Context context) {
        this.context = context;
    }

    public void onDownloadStart(String arg0, String arg1, String arg2, String arg3, long arg4) {
        this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(arg0)));
    }
}
