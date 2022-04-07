package com.sr.superhelperx.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hang.Yang on 2018/8/17 14:41.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AsyCallBack<T> {
    private Map<Class<? extends Asy>, Asy> m = new HashMap();

    public AsyCallBack() {
    }

    void a(Asy a) {
        this.m.put(a.getClass(), a);
    }

    protected <A> A getAsy(Class<A> c) {
        return (A) this.m.get(c);
    }

    public void onStart(int type) throws Exception {
    }

    public void onStart(int type, Object o) throws Exception {
        this.onStart(type);
    }

    public void onEnd(String toast, int type) throws Exception {
    }

    public void onEnd(String toast, int type, Object o) throws Exception {
        this.onEnd(toast, type);
    }

    public void onFail(String toast, int type) throws Exception {
    }

    public void onFail(String toast, int type, Object o) throws Exception {
        this.onFail(toast, type);
    }

    public void onSuccess(String toast, int type, T t) throws Exception {
    }

    public void onSuccess(String toast, int type, Object o, T t) throws Exception {
        this.onSuccess(toast, type, t);
    }

    public void onProgress(long total, long current, int percentage) throws Exception {
    }
}
