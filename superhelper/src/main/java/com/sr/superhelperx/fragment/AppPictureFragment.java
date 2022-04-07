package com.sr.superhelperx.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.sr.superhelperx.app.AppPictureInterface;
import com.sr.superhelperx.entity.AppCrop;
import com.sr.superhelperx.http.Http;
import com.sr.superhelperx.permission.PermissionsResultAction;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilApp;
import com.sr.superhelperx.util.UtilFile;
import com.sr.superhelperx.util.UtilFileProviderUriPath;
import com.sr.superhelperx.util.UtilPermissionManager;
import com.sr.superhelperx.util.UtilSDCard;
import com.sr.superhelperx.util.UtilToast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hang.Yang on 2018/8/17 15:46.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(11)
@SuppressLint({"SdCardPath", "SimpleDateFormat"})
public abstract class AppPictureFragment extends AppFragment implements AppPictureInterface {
    public static final int a = 4001;
    public static final int b = 4002;
    public static final int c = 4003;
    private ImageView f;
    private Uri d;
    private Uri e;

    public AppPictureFragment() {
    }

    public void startAlbum() {
        this.startAlbum((ImageView)null);
    }

    public void startCamera() {
        this.startCamera((ImageView)null);
    }

    public void startAlbum(final ImageView v) {
        if(UtilSDCard.isSDCardEnable()) {
            this.permission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionsResultAction() {
                public void onGranted() {
                    AppPictureFragment.this.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 4001);
                    AppPictureFragment.this.f = v;
                }

                public void onDenied(String permission) {
                    (new AlertDialog.Builder(AppPictureFragment.this.getActivity())).setCancelable(false).setTitle(UtilApp.appName()).setMessage("请手动开启存储权限").setPositiveButton("否", (DialogInterface.OnClickListener)null).setNegativeButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            UtilPermissionManager.show();
                        }
                    }).show();
                }
            });
        } else {
            UtilToast.show("未检测到SD卡");
        }

    }

    public void startCamera(final ImageView v) {
        startCamera(v,false);
    }

    public void startCamera(final ImageView v, final boolean isFrontCamera) {
        if(UtilSDCard.isSDCardEnable()) {
            this.permission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, new PermissionsResultAction() {
                public void onGranted() {
                    AppPictureFragment.this.startActivityForResult((new Intent("android.media.action.IMAGE_CAPTURE"))
                            .putExtra("output", AppPictureFragment.this.b())
                            .putExtra("android.intent.extras.CAMERA_FACING",isFrontCamera ? 1 : 0), 4002);
                    AppPictureFragment.this.f = v;
                }

                public void onDenied(String permission) {
                    (new AlertDialog.Builder(AppPictureFragment.this.getActivity())).setCancelable(false).setTitle(UtilApp.appName()).setMessage("请手动开启存储/相机拍照权限").setPositiveButton("否", (DialogInterface.OnClickListener)null).setNegativeButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            UtilPermissionManager.show();
                        }
                    }).show();
                }
            });
//            this.permission(new String[]{"android.permission.CAMERA"}, new PermissionsResultAction() {
//                public void onGranted() {
//                    AppPictureFragment.this.startActivityForResult((new Intent("android.media.action.IMAGE_CAPTURE"))
//                            .putExtra("output", AppPictureFragment.this.b())
//                            .putExtra("android.intent.extras.CAMERA_FACING",isFrontCamera ? 1 : 0), 4002);
//                    AppPictureFragment.this.f = v;
//                }
//
//                public void onDenied(String permission) {
//                    (new AlertDialog.Builder(AppPictureFragment.this.getActivity())).setCancelable(false).setTitle(UtilApp.appName()).setMessage("请手动开启相机拍照权限").setPositiveButton("否", (DialogInterface.OnClickListener)null).setNegativeButton("是", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            UtilPermissionManager.show();
//                        }
//                    }).show();
//                }
//            });
        } else {
            UtilToast.show("未检测到SD卡");
        }
    }

    public void onActivityResult(int q, int s, Intent t) {
        try {
            if(s == -1 && g.a(this)) {
                AppCrop e = this.getCrop();
                switch(q) {
                    case 4001:
                        if(e.f()) {
                            this.i(e.setOriginalUri(t.getData()));
                        } else {
                            this.resultPhotoPath(this.f, this.d(t.getData()));
                        }
                        break;
                    case 4002:
                        if(e.f()) {
                            if(Build.VERSION.SDK_INT >= 24) {
                                this.i(e.setOriginalUri(this.ri(new File(this.getPath(this.d)))));
                            } else {
                                this.i(this.getCrop().setOriginalUri(this.d));
                            }
                        } else {
                            this.resultPhotoPath(this.f, this.getPath(this.d));
                        }
                        break;
                    case 4003:
                        this.resultPhotoPath(this.f, this.getPath(this.e));
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        super.onActivityResult(q, s, t);
    }

    public Uri ri(File f) {
        String fp = f.getAbsolutePath();
        Cursor c = this.getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{fp}, (String)null);
        if(c != null && c.moveToFirst()) {
            int v1 = c.getInt(c.getColumnIndex("_id"));
            return Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + v1);
        } else if(f.exists()) {
            ContentValues v = new ContentValues();
            v.put("_data", fp);
            return this.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, v);
        } else {
            Http.getInstance().log("picture_file_nothing:", fp);
            return null;
        }
    }

    public String getProviderName() {
        return ".FileProvider";
    }

    private void i(AppCrop r) {
        Intent i = new Intent("com.android.camera.action.CROP");
        i.setDataAndType(r.c(), "image/*");
        i.putExtra("crop", "true");
        i.putExtra("output", this.c(r.c()));
        i.putExtra("aspectX", r.a());
        i.putExtra("aspectY", r.b());
        i.putExtra("outputX", r.d());
        i.putExtra("outputY", r.e());
        i.putExtra("scale", r.g());
        i.putExtra("scaleUpIfNeeded", r.h());
        this.startActivityForResult(i, 4003);
    }

    private String d(Uri u) {
        String p = "";
        String[] j = new String[]{"_data"};
        Cursor c = this.getActivity().managedQuery(u, j, (String)null, (String[])null, (String)null);
        if(c != null) {
            int i = c.getColumnIndexOrThrow("_data");
            if(c.getCount() > 0 && c.moveToFirst()) {
                p = c.getString(i);
            }
        }

        return p;
    }

    private boolean h(String s) {
        if(s != null && !"".equals(s)) {
            for(int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if(c != 32 && c != 9 && c != 13 && c != 10) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    private String f(String f) {
        if(this.h(f)) {
            return "";
        } else {
            int point = f.lastIndexOf(46);
            return f.substring(point + 1);
        }
    }

    private String e(Uri u) {
        String p = null;
        String r = Uri.decode(u.toString());
        String e = "file:///sdcard/";
        String s = "file:///mnt/sdcard/";
        if(r.startsWith(e)) {
            p = Environment.getExternalStorageDirectory().getPath() + File.separator + r.substring(e.length());
        } else if(r.startsWith(s)) {
            p = Environment.getExternalStorageDirectory().getPath() + File.separator + r.substring(s.length());
        }

        return p;
    }

    private Uri c(Uri u) {
        this.a();
        String p = this.e(u);
        if(this.h(p)) {
            p = this.d(u);
        }

        String ext = this.f(p);
        this.e = Uri.fromFile(new File(this.getCameraAbsolutePath() + "crop_" + this.g() + "." + (this.h(ext)?"jpg":ext)));
        return this.e;
    }

    private Uri b() {
        this.a();
        this.d = this.getUri(this.getCameraAbsolutePath() + "camera_" + this.g() + ".jpg");
        return this.d;
    }

    private void a() {
        UtilFile.createPathFile(new String[]{this.getCameraAbsolutePath()});
    }

    private String g() {
        return (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
    }

    protected String getPath(Uri uri) {
        return UtilFileProviderUriPath.toPath("/app_root_path", uri);
    }

    protected Uri getUri(String path) {
        return UtilFileProviderUriPath.toUri(path, this.getProviderName());
    }

    protected AppCrop getCrop() {
        return new AppCrop();
    }

    protected String getCameraAbsolutePath() {
        try {
            return this.getAppApplication().getCameraCropCacheFolder();
        } catch (Exception var2) {
            return "";
        }
    }

    protected abstract void resultPhotoPath(ImageView var1, String var2);
}
