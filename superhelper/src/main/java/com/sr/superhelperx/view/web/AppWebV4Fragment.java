package com.sr.superhelperx.view.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sr.superhelperx.fragment.AppV4PictureFragment;

/**
 * Created by Hang.Yang on 2018/8/17 16:48.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(11)
@SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
public class AppWebV4Fragment extends AppV4PictureFragment {
    private AppWebChromeClient appWebChromeClient;
    private AppProgressBar appProgressBar;
    private AppWebView appWebView;
    private String url;

    public AppWebV4Fragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = new RelativeLayout(this.getActivity());
        relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        if(this.appWebView != null) {
            this.appWebView.destroy();
        }

        this.appWebView = new AppWebView(this.getActivity());
        this.appWebView.addJavascriptInterface(new AppWebAppInterface(this.getActivity()), "Android");
        this.appWebView.setWebChromeClient(this.appWebChromeClient = new AppWebChromeClient(this.getActivity(), this.appProgressBar = new AppProgressBar(this.getActivity()), this));
        this.appWebView.setWebViewClient(new AppWebViewClient(this.appProgressBar));
        this.appWebView.setDownloadListener(new AppDownloadListener(this.getActivity()));
        this.setAppWebView(this.appWebView);
        relativeLayout.addView(this.appWebView);
        relativeLayout.addView(this.appProgressBar);
        this.loadUrl(this.url);
        return relativeLayout;
    }

    public void loadUrl(String url) {
        try {
            this.appWebView.loadUrl(this.url = url);
        } catch (Exception var3) {
            ;
        }

    }

    public boolean back() {
        boolean isBack = this.appWebView.canGoBack();
        if(isBack) {
            this.appWebView.goBack();
        }

        return isBack;
    }

    protected void resultPhotoPath(ImageView view, String photoPath) {
        this.appWebChromeClient.resultPhotoPath(photoPath);
    }

    public void onPause() {
        super.onPause();
        this.appWebView.onPause();
    }

    public void onResume() {
        this.appWebView.onResume();
        super.onResume();
    }

    protected int layoutId() {
        return 0;
    }

    public void onDestroy() {
        if(this.appWebView != null) {
            this.appWebView.destroy();
            this.appWebView = null;
        }

        super.onDestroy();
    }

    public void setAppWebView(AppWebView appWebView) {
    }
}
