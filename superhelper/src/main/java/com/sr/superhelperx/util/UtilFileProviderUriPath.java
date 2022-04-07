package com.sr.superhelperx.util;

import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.http.Http;

import java.io.File;

/**
 * Created by Hang.Yang on 2018/8/17 16:22.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class UtilFileProviderUriPath {
    public UtilFileProviderUriPath() {
    }

    public static String toPath(String target, Uri uri) {
        if(Build.VERSION.SDK_INT >= 24) {
            String temp = uri.getPath();
            temp = temp.replace(target, "");
            Http.getInstance().log("UriPath.getPath", uri.getPath() + "<- sdk>=24 ->" + temp);
            return temp;
        } else {
            Http.getInstance().log("UriPath.getPath", uri.getPath());
            return uri.getPath();
        }
    }

    public static Uri toUri(String path, String providerName) {
        if(Build.VERSION.SDK_INT >= 24) {
            Uri uri = FileProvider.getUriForFile(AppApplication.AINSTANCE, UtilApp.packageName() + providerName, new File(path));
            Http.getInstance().log("UriPath.getUri", path + "<- sdk>=24 ->" + uri.getPath());
            return uri;
        } else {
            Http.getInstance().log("UriPath.getUri", path);
            return Uri.fromFile(new File(path));
        }
    }
}