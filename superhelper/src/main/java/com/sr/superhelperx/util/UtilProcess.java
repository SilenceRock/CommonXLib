package com.sr.superhelperx.util;

import android.app.ActivityManager;
import android.os.Process;

import com.sr.superhelperx.app.AppApplication;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 14:14.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilProcess {
    public UtilProcess() {
    }

    public static boolean verifyProcess() {
        String n = getProcessName();
        return n != null && n.equals(UtilApp.packageName());
    }

    public static String getProcessName() {
        List r = ((ActivityManager) AppApplication.AINSTANCE.getSystemService("activity")).getRunningAppProcesses();
        if(r == null) {
            return null;
        } else {
            Iterator var1 = r.iterator();

            ActivityManager.RunningAppProcessInfo i;
            do {
                if(!var1.hasNext()) {
                    return null;
                }

                i = (ActivityManager.RunningAppProcessInfo)var1.next();
            } while(i.pid != Process.myPid());

            return i.processName;
        }
    }
}
