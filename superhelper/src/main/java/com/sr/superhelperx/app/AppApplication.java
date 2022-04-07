package com.sr.superhelperx.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import com.sr.superhelperx.http.Http;
import com.sr.superhelperx.init.hh;
import com.sr.superhelperx.permission.PermissionsResultAction;
import com.sr.superhelperx.receiver.AppReceiver;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilFile;
import com.sr.superhelperx.util.UtilLog;
import com.sr.superhelperx.util.UtilProcess;
import com.sr.superhelperx.util.UtilSDCard;
import com.sr.superhelperx.view.asy.p;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import timber.log.Timber;

/**
 * Created by Hang.Yang on 2018/8/17 14:08.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
@AppInit
@SuppressLint({"NewApi"})
@TargetApi(9)
public class AppApplication {
    private String TAG = this.getClass().getSimpleName();
    public static AppApplication INSTANCE;
    public static Application AINSTANCE;
    public static boolean e;
    public static int k;
    private String af = "";
    private String jcf = "";
    private String icf = "";
    private String cccf = "";
    private String vcf = "";
    private String fcf = "";
    private final Map<Class<?>, AppCallBack> b = new HashMap();
    private final Stack<Activity> a = new Stack();
    private AppInit i;

    public AppApplication() {
    }

    public void setApplication(Application application){
        if (AINSTANCE == null){
            AINSTANCE = application;
            onCreate();
        }
    }

    public Application getApplication(){
        return AINSTANCE;
    }

    @TargetApi(9)
    public void onCreate() {
        INSTANCE = this;
        if(UtilProcess.verifyProcess()) {
            if((this.i = (AppInit)this.getClass().getAnnotation(AppInit.class)).initialize()) {
                hh.ze(this, this.i.name(), new hh.ck() {
                    public void s() {
                        AppApplication.this.i();
                    }

                    public void f() {
                        AppApplication.this.appExit();
                    }
                });
            } else if(g.a(this)) {
                this.i();
            } else {
                this.appExit();
            }

            if (this.i != null){
                AppApplication.this.af = UtilSDCard.getSDCardPath() + AppApplication.this.i.name() + "_app_folder" + File.separator;
                AppApplication.this.jcf = AppApplication.this.af + "json_cache" + File.separator;
                AppApplication.this.icf = AppApplication.this.af + "image_cache" + File.separator;
                AppApplication.this.cccf = AppApplication.this.af + "camera_crop_cache" + File.separator;
                AppApplication.this.vcf = AppApplication.this.af + "video_cache" + File.separator;
                AppApplication.this.fcf = AppApplication.this.af + "file_cache" + File.separator;
            }
        }
//        super.onCreate();
    }

    public void addAppCallBack(Class<?> cls, AppCallBack appCallBack) {
        this.b.put(cls, appCallBack);
    }

    public AppCallBack getAppCallBack(Class<?> cls) throws Exception {
        AppCallBack a = (AppCallBack)this.b.get(cls);
        if(a == null) {
//            throw new Exception(cls.toString() + "中未设置AppCallBack");
            UtilLog.e(TAG,cls.toString() + "中未设置AppCallBack");
            return null;
        } else {
            return a;
        }
    }

    public void removeAppCallBack(Class<?> cls) {
        this.b.remove(cls);
    }

    public void registerReceiver(AppReceiver receiver) {
        try {
            AINSTANCE.registerReceiver(receiver, new IntentFilter(receiver.createAction()));
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public void addReceiver(AppReceiver receiver) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            String[] actions = receiver.createAction().split(String.format("\\%s", ","),-1);
            for (String action : actions){
                intentFilter.addAction(action);
            }
            AINSTANCE.registerReceiver(receiver, intentFilter);
        } catch (Exception var3) {
            ;
        }
    }

    public void addActivity(Activity activity) {
        if(g.a(this)) {
            this.a.add(activity);
        }
    }

    public Activity currentActivity() {
        return (Activity)this.a.lastElement();
    }

    public void finishActivity() {
        this.finishActivity((Activity)this.a.lastElement());
    }

    public void finishActivity(Activity activity) {
        try {
            this.a.remove(activity);
            activity.finish();
        } catch (Exception var3) {
            ;
        }

    }

    public void finishActivity(Class... clss) {
        for(int i = 0; i < clss.length; ++i) {
            this.finishActivity(clss[i]);
        }

    }

    public void finishActivity(Class<? extends Activity> cls) {
        for(int i = 0; i < this.a.size(); ++i) {
            Activity y = (Activity)this.a.get(i);
            if(y.getClass().equals(cls)) {
                this.finishActivity(y);
            }
        }

    }

    public void finishAllActivity() {
        int i = 0;

        for(int size = this.a.size(); i < size; ++i) {
            if(null != this.a.get(i)) {
                ((Activity)this.a.get(i)).finish();
            }
        }

        this.a.clear();
    }

    public void retainActivity(Class... clss) {
        Stack s = (Stack)this.a.clone();
        Iterator var3 = s.iterator();

        while(var3.hasNext()) {
            Activity a = (Activity)var3.next();
            boolean k = true;
            Class[] var6 = clss;
            int var7 = clss.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Class as = var6[var8];
                if(a.getClass().equals(as)) {
                    k = false;
                }
            }

            if(k) {
                this.finishActivity(a);
            }
        }

    }

    public boolean hasActivity(Class<? extends Activity> cls) {
        for(int i = 0; i < this.a.size(); ++i) {
            if(((Activity)this.a.get(i)).getClass().equals(cls)) {
                return true;
            }
        }

        return false;
    }

    public int getActivityStackSize() {
        return this.a.size();
    }

    public void appExit() {
        try {
            this.finishAllActivity();
            this.b.clear();
            p.e().c();
            ActivityManager e = (ActivityManager)AINSTANCE.getSystemService("activity");
            e.restartPackage(AINSTANCE.getPackageName());
            Process.killProcess(Process.myPid());
            int b = Build.VERSION.SDK_INT;
            if(b > 7) {
                Intent am = new Intent("android.intent.action.MAIN");
                am.addCategory("android.intent.category.HOME");
                am.setFlags(268435456);
                AINSTANCE.startActivity(am);
                System.exit(0);
            } else {
                ActivityManager am1 = (ActivityManager)AINSTANCE.getSystemService("activity");
                am1.restartPackage(AINSTANCE.getPackageName());
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public String getAppFolder() {
        return this.af;
    }

    public String getImageCacheFolder() {
        return this.icf;
    }

    public String getJsonCacheFolder() {
        return this.jcf;
    }

    public String getCameraCropCacheFolder() {
        return this.cccf;
    }

    public String getVideoCacheFolder() {
        return this.vcf;
    }

    public String getFileCacheFolder() {
        return this.fcf;
    }

    private void i() {

        try {
            Timber.plant(new Timber.DebugTree());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        try {
            ScaleScreenHelperFactory.init(this.i.width(), this.i.scale());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        try {
            Http.getInstance().setIsLog(this.i.log());
        } catch (Exception var2) {
            ;
        }

        if(this.i.crash()) {
            (new Handler(Looper.getMainLooper())).post(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            Looper.loop();
                        } catch (Throwable var2) {
                            ;
                        }
                    }
                }
            });
        }

    }

    public void initAppRoot(AppInterface appInterface, AppApplication.OnAppRootCallBack onAppRootCallBack) {
        this.k(appInterface, onAppRootCallBack, -1L);
    }

    public void initAppRoot(AppInterface appInterface, AppApplication.OnAppRootCallBack onAppRootCallBack, long delayMillis) {
        this.k(appInterface, onAppRootCallBack, delayMillis);
    }

    private void k(final AppInterface ai, final AppApplication.OnAppRootCallBack o, long d) {
        if(d > 0L) {
            ai.delayed(d, new AppInterface.OnDelayedEnd() {
                public void onEnd() {
                    AppApplication.this.k(ai, o);
                }
            });
        } else {
            AppApplication.this.k(ai, o);
        }
    }

    @SuppressLint("CheckResult")
    private void k(AppInterface ai, final AppApplication.OnAppRootCallBack o) {

        ai.permission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionsResultAction() {
            public void onGranted() {
                try {
                    UtilFile.createPathFile(AppApplication.this.af =
                                    UtilSDCard.getSDCardPath() + AppApplication.this.i.name() + "_app_folder" + File.separator
                            , AppApplication.this.jcf = AppApplication.this.af + "json_cache" + File.separator
                            , AppApplication.this.icf = AppApplication.this.af + "image_cache" + File.separator
                            , AppApplication.this.icf + File.separator + ".nomedia"
                            , AppApplication.this.cccf = AppApplication.this.af + "camera_crop_cache" + File.separator
                            , AppApplication.this.cccf + File.separator + ".nomedia"
                            , AppApplication.this.vcf = AppApplication.this.af + "video_cache" + File.separator
                            , AppApplication.this.fcf = AppApplication.this.af + "file_cache" + File.separator);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
                try {
                    Http.getInstance().setCache(AppApplication.this.jcf, 10485760);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
                try {
                    o.onResult(true);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
            }

            public void onDenied(String permission) {
                UtilLog.e("--main--","权限获取失败");
                try {
                    o.onResult(false);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
            }
        });
    }

    public interface OnAppRootCallBack {
        void onResult(boolean isSuccess);
    }
}
