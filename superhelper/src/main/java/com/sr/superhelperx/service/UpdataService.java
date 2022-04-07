package com.sr.superhelperx.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.sign.g;

/**
 * Created by Hang.Yang on 2018/8/17 16:15.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
@SuppressLint({"NewApi"})
@TargetApi(9)
public final class UpdataService extends Service {
    private static UpdataService i;
    private static UpdataService.CallBack c;
    private DownloadManager d;
    private UpdataService.r r;

    public UpdataService() {
    }

    public static final void StartService(UpdataService.CallBack callBack) {
        if(i == null) {
            c = callBack;
            AppApplication.AINSTANCE.startService(new Intent(AppApplication.AINSTANCE, UpdataService.class));
        } else {
            callBack.onCallBack(i);
        }

    }

    public void onCreate() {
        super.onCreate();
        if(g.a(this)) {
            i = this;
        }

        this.d = (DownloadManager)this.getSystemService("download");
        this.registerReceiver(this.r = new UpdataService.r(), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        if(c != null) {
            c.onCallBack(i);
        }

    }

    public void updata(String url, String name) {
        DownloadManager.Request o = new DownloadManager.Request(Uri.parse(url));
        o.setAllowedNetworkTypes(3);
        o.setNotificationVisibility(0);
        o.setVisibleInDownloadsUi(true);
        o.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name + ".apk");
        this.d.enqueue(o);
    }

    public void onDestroy() {
        i = null;
        c = null;
        this.unregisterReceiver(this.r);
        super.onDestroy();
    }

    public IBinder onBind(Intent i) {
        return null;
    }

    public interface CallBack {
        void onCallBack(UpdataService var1);
    }

    private class r extends BroadcastReceiver {
        private r() {
        }

        public void onReceive(Context c, Intent i) {
            Uri u = UpdataService.this.d.getUriForDownloadedFile(i.getLongExtra("extra_download_id", -1L));
            Intent s = new Intent();
            s.setAction("android.intent.action.VIEW");
            s.setFlags(268435456);
            s.setDataAndType(u, "application/vnd.android.package-archive");
            UpdataService.this.startActivity(s);
            UpdataService.this.stopSelf();
        }
    }
}
