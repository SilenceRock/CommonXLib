package com.sr.superhelperx.util;

import android.annotation.TargetApi;
import android.os.AsyncTask;

/**
 * Created by Hang.Yang on 2018/8/17 16:16.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(3)
public abstract class UtilAsyHandler<T> extends AsyncTask<Void, Void, T> {
    public UtilAsyHandler() {
        this.execute(new Void[0]);
    }

    protected T doInBackground(Void... arg0) {
        return this.doHandler();
    }

    protected void onPostExecute(T t) {
        this.doComplete(t);
    }

    protected abstract T doHandler();

    protected abstract void doComplete(T var1);
}
