package com.sr.superhelperx.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 16:27.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilUri {
    public UtilUri() {
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Uri uri, String selection, String[] selectionArgs) {
        Cursor c = null;
        String l = "_data";

        String var5;
        try {
            c = AppApplication.AINSTANCE.getContentResolver().query(uri, new String[]{"_data"}, selection, selectionArgs, (String)null);
            if(c == null || !c.moveToFirst()) {
                return null;
            }

            var5 = c.getString(c.getColumnIndexOrThrow("_data"));
        } finally {
            if(c != null) {
                c.close();
            }

        }

        return var5;
    }

    @SuppressLint({"NewApi"})
    public static String getPath(Context context, Uri uri) {
        if(Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, uri)) {
            String[] s;
            if(isExternalStorageDocument(uri)) {
                s = DocumentsContract.getDocumentId(uri).split(":");
                if("primary".equalsIgnoreCase(s[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + s[1];
                }
            } else {
                if(isDownloadsDocument(uri)) {
                    return getDataColumn(ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String)null, (String[])null);
                }

                if(isMediaDocument(uri)) {
                    s = DocumentsContract.getDocumentId(uri).split(":");
                    String t = s[0];
                    Uri c = null;
                    if("image".equals(t)) {
                        c = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if("video".equals(t)) {
                        c = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if("audio".equals(t)) {
                        c = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    return getDataColumn(c, "_id=?", new String[]{s[1]});
                }
            }
        } else {
            if("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(uri, (String)null, (String[])null);
            }

            if("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }
}
