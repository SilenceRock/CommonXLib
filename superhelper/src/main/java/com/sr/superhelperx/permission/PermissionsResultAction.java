package com.sr.superhelperx.permission;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hang.Yang on 2018/8/17 14:20.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class PermissionsResultAction {
    private static final String TAG = PermissionsResultAction.class.getSimpleName();
    private final Set<String> mPermissions = new HashSet(1);
    private Looper mLooper = Looper.getMainLooper();

    public PermissionsResultAction() {
    }

    public PermissionsResultAction(@NonNull Looper looper) {
        this.mLooper = looper;
    }

    public abstract void onGranted();

    public abstract void onDenied(String var1);

    public synchronized boolean shouldIgnorePermissionNotFound(String permission) {
        Log.d(TAG, "Permission not found: " + permission);
        return true;
    }

    @CallSuper
    protected final synchronized boolean onResult(@NonNull String permission, int result) {
        return result == 0?this.onResult(permission, Permissions.GRANTED):this.onResult(permission, Permissions.DENIED);
    }

    @CallSuper
    protected final synchronized boolean onResult(@NonNull final String permission, Permissions result) {
        this.mPermissions.remove(permission);
        if(result == Permissions.GRANTED) {
            if(this.mPermissions.isEmpty()) {
                (new Handler(this.mLooper)).post(new Runnable() {
                    public void run() {
                        PermissionsResultAction.this.onGranted();
                    }
                });
                return true;
            }
        } else {
            if(result == Permissions.DENIED) {
                (new Handler(this.mLooper)).post(new Runnable() {
                    public void run() {
                        PermissionsResultAction.this.onDenied(permission);
                    }
                });
                return true;
            }

            if(result == Permissions.NOT_FOUND) {
                if(!this.shouldIgnorePermissionNotFound(permission)) {
                    (new Handler(this.mLooper)).post(new Runnable() {
                        public void run() {
                            PermissionsResultAction.this.onDenied(permission);
                        }
                    });
                    return true;
                }

                if(this.mPermissions.isEmpty()) {
                    (new Handler(this.mLooper)).post(new Runnable() {
                        public void run() {
                            PermissionsResultAction.this.onGranted();
                        }
                    });
                    return true;
                }
            }
        }

        return false;
    }

    @CallSuper
    protected final synchronized void registerPermissions(@NonNull String[] perms) {
        Collections.addAll(this.mPermissions, perms);
    }
}
