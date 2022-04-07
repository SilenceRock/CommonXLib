package com.sr.superhelperx.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.app.AppCallBack;
import com.sr.superhelperx.app.AppCycle;
import com.sr.superhelperx.app.AppInterface;
import com.sr.superhelperx.bound.BoundViewHelper;
import com.sr.superhelperx.http.Http;
import com.sr.superhelperx.permission.PermissionsManager;
import com.sr.superhelperx.permission.PermissionsResultAction;
import com.sr.superhelperx.receiver.AppReceiver;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.util.UtilClear;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Hang.Yang on 2018/8/17 15:46.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
@TargetApi(15)
public abstract class AppFragment extends Fragment implements AppInterface {
    protected Map<Class<? extends AppReceiver>, BroadcastReceiver> m = new HashMap();

    public AppFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.layoutId(), container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup) BoundViewHelper.boundView(this, this.getView()));
        } catch (Exception var3) {
            ;
        }

    }

    public AppApplication getAppApplication() throws Exception {
        return AppApplication.INSTANCE;
    }

    public void onResume() {
        super.onResume();
        Http.getInstance().logSkip(this.getClass().toString() + "->show: %s", this.getClass());
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void permission(String[] permission, PermissionsResultAction permissionsResultAction) {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permission, permissionsResultAction);
    }

    public void onDetach() {
        try {
            this.getAppApplication().removeAppCallBack(this.getClass());
            Iterator var1 = this.m.values().iterator();

            while(var1.hasNext()) {
                BroadcastReceiver r = (BroadcastReceiver)var1.next();
                this.getActivity().unregisterReceiver(r);
            }

            this.m.clear();
        } catch (Exception var3) {
            ;
        }

        UtilClear.clear(this);
        super.onDetach();
    }

    public void addReceiver(AppReceiver receiver) {
        try {
            this.getActivity().registerReceiver(receiver, new IntentFilter(receiver.createAction()));
            this.m.put(receiver.getClass(), receiver);
        } catch (Exception var3) {
            ;
        }

    }

    public void removeReceiver(Class<? extends AppReceiver> clazz) {
        try {
            this.getActivity().unregisterReceiver((BroadcastReceiver)this.m.get(clazz));
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
                    if(AppApplication.INSTANCE.currentActivity() == AppFragment.this.getActivity()) {
                        onDelayedEnd.onEnd();
                    }
                } catch (Exception var3) {
                    ;
                }

            }
        }).sendEmptyMessageDelayed(0, delayMillis);
    }

    public void startVerifyActivity(Class<?> cls) {
        this.startVerifyActivity(cls, new Intent());
    }

    public void startVerifyActivity(Class<?> cls, Intent intent) {
        this.startActivity(intent.setClass(this.getActivity(), cls));
    }

    public void setAppCycle(AppCycle appCycle) {
    }

    protected abstract int layoutId();
}
