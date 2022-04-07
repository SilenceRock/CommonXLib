package com.sr.superhelperx.view.web;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.sr.superhelperx.util.UtilApp;

/**
 * Created by Hang.Yang on 2018/8/17 16:49.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class AppWebViewClient extends WebViewClient {
    private ProgressBar progressBar;

    public AppWebViewClient(AppProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(!url.contains("http://") && !url.contains("https://")) {
            String[] tel;
            if(url.contains("sms:")) {
                try {
                    tel = url.split(":");
                    UtilApp.sms(tel[1], "");
                } catch (Exception var4) {
                    ;
                }

                return true;
            }

            if(url.contains("tel:")) {
                try {
                    tel = url.split(":");
                    UtilApp.call(tel[1]);
                } catch (Exception var5) {
                    ;
                }

                return true;
            }
        } else {
            view.loadUrl(url);
        }

        return false;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        this.progressBar.setVisibility(0);
    }

    public void onPageFinished(WebView view, String url) {
        this.progressBar.setVisibility(8);
    }
}
