package com.sr.superhelperx.view.web;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sr.superhelperx.app.AppPictureInterface;

import java.io.File;

/**
 * Created by Hang.Yang on 2018/8/17 16:47.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class AppWebChromeClient extends WebChromeClient {
    private AppPictureInterface appPictureInterface;
    private ValueCallback<Uri[]> uploadMsgs;
    private ValueCallback<Uri> uploadMsg;
    private ProgressBar progressBar;
    private Context context;

    public AppWebChromeClient(Context context, ProgressBar progressBar, AppPictureInterface appPictureInterface) {
        this.context = context;
        this.progressBar = progressBar;
        this.appPictureInterface = appPictureInterface;
    }

    public void onProgressChanged(WebView view, int newProgress) {
        this.progressBar.setProgress(newProgress);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        this.uploadMsg = uploadMsg;
        this.fileChooser();
    }

    public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
        this.uploadMsg = uploadMsg;
        this.fileChooser();
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        this.uploadMsg = uploadMsg;
        this.fileChooser();
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        try {
            this.uploadMsgs.onReceiveValue(null);
        } catch (Exception var5) {
            ;
        }

        this.uploadMsgs = filePathCallback;
        this.fileChooser();
        return true;
    }

    @TargetApi(7)
    private void fileChooser() {
        (new AlertDialog.Builder(this.context)).setNegativeButton("相机", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                AppWebChromeClient.this.appPictureInterface.startCamera((ImageView)null);
            }
        }).setPositiveButton("相册", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                AppWebChromeClient.this.appPictureInterface.startAlbum((ImageView)null);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                AppWebChromeClient.this.uploadMsg = null;
                AppWebChromeClient.this.uploadMsgs = null;
            }
        }).show();
        this.onHideCustomView();
    }

    public void resultPhotoPath(String photoPath) {
        if(this.uploadMsg != null || this.uploadMsgs != null) {
            Uri uri = Uri.fromFile(new File(photoPath));
            if(Build.VERSION.SDK_INT >= 21) {
                this.uploadMsgs.onReceiveValue(new Uri[]{uri});
            } else {
                this.uploadMsg.onReceiveValue(uri);
            }

            this.uploadMsg = null;
            this.uploadMsgs = null;
        }

    }
}
