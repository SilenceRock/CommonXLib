package com.sr.superhelperx.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Hang.Yang on 2018/8/17 14:19.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class NetWorkReceiver extends AppReceiver {
    public NetWorkReceiver() {
    }

    public void onReceive(Context c, Intent i) {
        ConnectivityManager m = (ConnectivityManager)c.getSystemService("connectivity");
        NetworkInfo a = m.getActiveNetworkInfo();
        NetworkInfo n = m.getNetworkInfo(0);
        this.onConnectChange(a != null && a.isAvailable() && a.isConnected() || n != null && n.isAvailable() && n.isConnected());
    }

    public String createAction() {
        return "android.net.conn.CONNECTIVITY_CHANGE";
    }

    protected abstract void onConnectChange(boolean var1);
}
