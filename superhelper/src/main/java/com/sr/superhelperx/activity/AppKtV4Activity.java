package com.sr.superhelperx.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.app.AppAsyLayoutCallBack;
import com.sr.superhelperx.app.AppCallBack;
import com.sr.superhelperx.app.AppCycle;
import com.sr.superhelperx.app.AppInterface;
import com.sr.superhelperx.bound.BoundViewHelper;
import com.sr.superhelperx.http.Http;
import com.sr.superhelperx.permission.PermissionsManager;
import com.sr.superhelperx.permission.PermissionsResultAction;
import com.sr.superhelperx.receiver.AppReceiver;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.util.UtilAsyView;
import com.sr.superhelperx.util.UtilClear;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Hang.Yang on 2018/8/17 15:02.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppKtV4Activity extends FragmentActivity implements AppInterface {
    protected Map<Class<? extends AppReceiver>, BroadcastReceiver> m = new HashMap();
    protected Context context;
    private AppCycle y;
    private Bundle b;

    public AppKtV4Activity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setFitsSystemWindows(true);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try {
            this.y.onCreate(savedInstanceState);
        } catch (Exception var4) {
            ;
        }

        this.b = savedInstanceState;
        this.context = this;

        try {
            this.getAppApplication().addActivity(this);
        } catch (Exception var3) {
            ;
        }

        /**
         * 退到后台再次进入应用时候，之前的页面不保留，App重新启动。问题主要出现在华为手机上
         */
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        }
    }

    protected void onStart() {
        super.onStart();

        try {
            this.y.onStart();
        } catch (Exception var2) {
            ;
        }

    }

    public AppApplication getAppApplication() throws Exception {
        return AppApplication.INSTANCE;
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        try {
            ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup) BoundViewHelper.boundView(this, this.getWindow().getDecorView()));
        } catch (Exception var3) {
            ;
        }

    }

    public void setContentViewAsy(int layoutResID) {
        this.setContentViewAsy(layoutResID, (AppAsyLayoutCallBack)null);
    }

    public void setContentViewAsy(final int layoutResID, final AppAsyLayoutCallBack appAsyLayoutCallBack) {

        @SuppressLint("HandlerLeak")
        UtilAsyView var10001 = new UtilAsyView() {
            protected View handler() {
                return ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup) BoundViewHelper.boundView(AppKtV4Activity.this, AppKtV4Activity.this.getLayoutInflater().inflate(layoutResID, (ViewGroup)null)));
            }

            protected void result(View view) {
                AppKtV4Activity.this.setContentView(view);

                try {
                    appAsyLayoutCallBack.onAsyLayoutInit(AppKtV4Activity.this.b);
                } catch (Exception var3) {
                    AppKtV4Activity.this.onAsyLayoutInit(AppKtV4Activity.this.b);
                }

            }
        };
    }

    public void onAsyLayoutInit(Bundle savedInstanceState) {
    }

    /**
     * 退到后台再次进入应用时候，之前的页面不保留，App重新启动。问题主要出现在华为手机上
     * @param nonRoot
     * @return
     */
    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        return super.moveTaskToBack(true);
    }

    protected void onResume() {
        super.onResume();

        try {
            this.y.onResume();
        } catch (Exception var2) {
            ;
        }

        Http.getInstance().logSkip(this.getClass().toString() + "->show: %s", this.getClass(), "kt");
    }

    protected void onRestart() {
        super.onRestart();

        try {
            this.y.onRestart();
        } catch (Exception var2) {
            ;
        }

    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ScaleScreenHelperFactory.reset();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onPause() {
        super.onPause();

        try {
            this.y.onPause();
        } catch (Exception var2) {
            ;
        }

    }

    protected void onStop() {
        super.onStop();

        try {
            this.y.onStop();
        } catch (Exception var2) {
            ;
        }

    }

    protected void onDestroy() {
        try {
            AppApplication a = this.getAppApplication();
            a.removeAppCallBack(this.getClass());
            a.finishActivity(this);
            Iterator var2 = this.m.values().iterator();

            while(var2.hasNext()) {
                BroadcastReceiver r = (BroadcastReceiver)var2.next();
                this.unregisterReceiver(r);
            }

            this.m.clear();
        } catch (Exception var5) {
            ;
        }

        UtilClear.clear(this);
        Glide.get(this).clearMemory();
        super.onDestroy();

        try {
            this.y.onDestroy();
        } catch (Exception var4) {
            ;
        }

    }

    public void addReceiver(AppReceiver receiver) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            String[] actions = receiver.createAction().split(String.format("\\%s", ","),-1);
            for (String action : actions){
                intentFilter.addAction(action);
            }
            this.registerReceiver(receiver, intentFilter);
            this.m.put(receiver.getClass(), receiver);
        } catch (Exception var3) {
            ;
        }
    }

    public void removeReceiver(Class<? extends AppReceiver> clazz) {
        try {
            this.unregisterReceiver((BroadcastReceiver)this.m.get(clazz));
            this.m.remove(clazz);
        } catch (Exception var3) {
            ;
        }
    }

    public void setAppCallBack(AppCallBack appCallBack) {
        try {
            this.getAppApplication().addAppCallBack(this.getClass(), appCallBack);
        } catch (Exception var3) {
            ;
        }

    }

    public AppCallBack getAppCallBack(Class<?> cls) throws Exception {
        AppCallBack a = null;

        try {
            a = this.getAppApplication().getAppCallBack(cls);
        } catch (Exception var4) {
            ;
        }

        return a;
    }

    @SuppressLint("HandlerLeak")
    public void delayed(long delayMillis, final OnDelayedEnd onDelayedEnd) {
        (new Handler() {
            public void handleMessage(Message msg) {
                try {
                    if(AppApplication.INSTANCE.currentActivity() == AppKtV4Activity.this) {
                        onDelayedEnd.onEnd();
                    }
                } catch (Exception var3) {
                }
            }
        }).sendEmptyMessageDelayed(0, delayMillis);
    }

    public void permission(String[] permission, PermissionsResultAction permissionsResultAction) {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permission, permissionsResultAction);
    }

    public void setAppCycle(AppCycle appCycle) {
        this.y = appCycle;
    }

    public void startVerifyActivity(Class<?> cls) {
        this.startVerifyActivity(cls, new Intent());
    }

    public void startVerifyActivity(Class<?> cls, Intent intent) {
        this.startActivity(intent.setClass(this, cls));
    }

    /**
     * 重写事件分发，目的是点击输入框以外的位置，收回软键盘
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
//                EditText v1 = (EditText) v;
//                v1.setCursorVisible(false);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v instanceof EditText) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }
}
