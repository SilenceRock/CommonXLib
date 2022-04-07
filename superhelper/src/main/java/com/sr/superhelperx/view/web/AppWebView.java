package com.sr.superhelperx.view.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Hang.Yang on 2018/8/17 16:49.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi"})
public class AppWebView extends WebView {
    @SuppressLint({"SetJavaScriptEnabled"})
    public AppWebView(Context context) {
        super(context);
        this.setLayoutParams(new LayoutParams(-1, -1, 0, 0));
        this.loadData("", "text/html", (String)null);
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.loadUrl("javascript:alert(injectedObject.toString())");
        WebSettings webSettings = this.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    }
}
