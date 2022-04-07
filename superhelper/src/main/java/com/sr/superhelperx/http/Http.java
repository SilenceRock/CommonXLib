package com.sr.superhelperx.http;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.dialog.WaitDialog;
import com.sr.superhelperx.http.cert.HttpCert;
import com.sr.superhelperx.http.note.HttpFinish;
import com.sr.superhelperx.http.note.HttpGZIP;
import com.sr.superhelperx.http.note.HttpNew;
import com.sr.superhelperx.http.note.HttpTimeout;
import com.sr.superhelperx.init.hh;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilClassName;
import com.sr.superhelperx.view.asy.AsyViewLayout;
import com.sr.superhelperx.view.asy.p;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import timber.log.Timber;

/**
 * Created by Hang.Yang on 2018/8/17 14:31.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
public final class Http implements DialogInterface.OnCancelListener {
    private Http.OnStartEndCreateDialog f;
    private OkHttpClient q;
    private boolean d;
    private Context c;
    private OkHttpClient.Builder b;
    private Dialog dl;

    public static Http getInstance() {
        return Http.h.a;
    }

    private Http() {
        this.b = new OkHttpClient.Builder();
        this.setOnStartEndCreateDialog(new Http.OnStartEndCreateDialog() {
            @Override
            public void show(Dialog var1) throws Exception {

            }

            @Override
            public void dismiss(Dialog var1) throws Exception {

            }

            public WaitDialog create(Context c) throws Exception {
                return new WaitDialog(c);
            }

            public Http.ProgressDialog createProgressDialog() {
                return new Http.ProgressDialog() {

                    @Override
                    public void show(Dialog var1) throws Exception {

                    }

                    @Override
                    public void dismiss(Dialog var1) throws Exception {

                    }

                    public WaitDialog create(Context c) {
                        return new WaitDialog(c);
                    }

                    @Override
                    public void progress(Dialog var1, long var2, long var4, int var6) throws Exception {

                    }

                    public void progress(WaitDialog d, long t, long c, int p) {
                        d.percentage(p);
                    }
                };
            }
        });
    }

    public void allTimeout(int time) {
        this.connectTimeout(time);
        this.writeTimeout(time);
        this.readTimeout(time);
    }

    public void connectTimeout(int time) {
        this.b.connectTimeout((long)time, TimeUnit.SECONDS);
    }

    public void writeTimeout(int time) {
        this.b.writeTimeout((long)time, TimeUnit.SECONDS);
    }

    public void readTimeout(int time) {
        this.b.readTimeout((long)time, TimeUnit.SECONDS);
    }

    public void setCache(String a, int m) {
        this.b.cache(new Cache(new File(a), (long)m));
    }

    public void setCertificates(InputStream[] certificates, InputStream bksFile, String password, String type) {
        HttpCert.SSLParams s = HttpCert.getSslSocketFactory(certificates, bksFile, password, type);
        this.b.sslSocketFactory(s.sSLSocketFactory, s.trustManager);
    }

    synchronized Object sendRequest(Asy s){
        if(g.a(this)) {
            if (this.q == null) {
                this.q = this.b.build();
            }

            Http.a a = ((HttpGZIP)s.getClass().getAnnotation(HttpGZIP.class)).value()? Http.this.new a():null;
            HttpTimeout j = (HttpTimeout)s.getClass().getAnnotation(HttpTimeout.class);
            HttpNew w = (HttpNew)s.getClass().getAnnotation(HttpNew.class);
            String n = UtilClassName.getActionName(s.getClass());

            try {
                OkHttpClient ex = Http.this.q;
                if(j != null || a != null || Http.this.b != null || w.value()) {
                    OkHttpClient.Builder r = Http.this.q.newBuilder();
                    if(j != null) {
                        r.connectTimeout((long)j.connect(), TimeUnit.SECONDS);
                        r.writeTimeout((long)j.write(), TimeUnit.SECONDS);
                        r.readTimeout((long)j.read(), TimeUnit.SECONDS);
                    }

                    if(a != null) {
                        r.addInterceptor(a);
                    }

                    ex = r.build();
                }

                s.operation();
                Response r1 = ex.newCall(s.b()).execute();
                int co = r1.code();
                Http.this.log(s.getClass() + "->code: %s", co + "");
                if(s.code(r1.isSuccessful(), co)) {
                    return s.s.parser(r1);
                }
                s.s.TOAST = "服务器连接异常";
            } catch (SocketTimeoutException var5) {
                s.s.TOAST = "网络连接超时";
                var5.printStackTrace();
            } catch (Exception var6) {
                s.s.TOAST = "网络请求异常";
                var6.printStackTrace();
            }
        }
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    @TargetApi(3)
    synchronized void g(final Context c, final boolean i, final int t, final Object e, final Asy s) {
        if(g.a(this)) {
            if(this.q == null) {
                this.q = this.b.build();
            }

            new AsyncTask() {
                private Http.a a = ((HttpGZIP)s.getClass().getAnnotation(HttpGZIP.class)).value()? Http.this.new a():null;
                private HttpTimeout j = (HttpTimeout)s.getClass().getAnnotation(HttpTimeout.class);
                private HttpNew w = (HttpNew)s.getClass().getAnnotation(HttpNew.class);
                private String n = UtilClassName.getActionName(s.getClass());

                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        OkHttpClient ex = Http.this.q;
                        if(this.j != null || this.a != null || Http.this.b != null || this.w.value()) {
                            OkHttpClient.Builder r = Http.this.q.newBuilder();
                            if(this.j != null) {
                                r.connectTimeout((long)this.j.connect(), TimeUnit.SECONDS);
                                r.writeTimeout((long)this.j.write(), TimeUnit.SECONDS);
                                r.readTimeout((long)this.j.read(), TimeUnit.SECONDS);
                            }

                            if(this.a != null) {
                                r.addInterceptor(this.a);
                            }

                            ex = r.build();
                        }

                        s.operation();
                        Response r1 = ex.newCall(s.b()).execute();
                        int co = r1.code();
                        Http.this.log(s.getClass() + "->code: %s", co + "");
                        if(s.code(r1.isSuccessful(), co)) {
                            this.c();
                            return s.s.parser(r1);
                        }

                        s.s.TOAST = "服务器连接异常";
                    } catch (SocketTimeoutException var5) {
                        s.s.TOAST = "网络连接超时";
                        var5.printStackTrace();
                    } catch (Exception var6) {
                        s.s.TOAST = "网络请求异常";
                        var6.printStackTrace();
                    }

                    this.c();
                    return null;
                }

                protected void onPreExecute() {
                    try {
                        Http.this.f.i(s);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }

                    try {
                        if(i) {
                            Http.this.show(c, s);
                        }
                    } catch (Exception var4) {
                        var4.printStackTrace();
                    }

                    try {
                        Asy var10001 = s;
                        if(t == -100) {
                            AsyViewLayout.g(c, this.n, 44);
                        }
                    } catch (Exception var3) {
                        var3.printStackTrace();
                    }

                    try {
                        s.a.onStart(t, e);
                    } catch (Exception var2) {
                        var2.printStackTrace();
                    }

                    s.pd = Http.this.f.d;
                    s.dl = Http.this.dl;
                }

                /*protected Object doInBackground(Void... params) {
                    try {
                        OkHttpClient ex = Http.this.q;
                        if(this.j != null || this.a != null || Http.this.b != null || this.w.value()) {
                            OkHttpClient.Builder r = Http.this.q.newBuilder();
                            if(this.j != null) {
                                r.connectTimeout((long)this.j.connect(), TimeUnit.SECONDS);
                                r.writeTimeout((long)this.j.write(), TimeUnit.SECONDS);
                                r.readTimeout((long)this.j.read(), TimeUnit.SECONDS);
                            }

                            if(this.a != null) {
                                r.addInterceptor(this.a);
                            }

                            ex = r.build();
                        }

                        s.operation();
                        Response r1 = ex.newCall(s.b()).execute();
                        int co = r1.code();
                        Http.this.log(s.getClass() + "->code: %s", co + "");
                        if(s.code(r1.isSuccessful(), co)) {
                            this.c();
                            return s.s.parser(r1);
                        }

                        s.s.TOAST = "服务器连接异常";
                    } catch (SocketTimeoutException var5) {
                        s.s.TOAST = "网络连接超时";
                        var5.printStackTrace();
                    } catch (Exception var6) {
                        s.s.TOAST = "网络请求异常";
                        var6.printStackTrace();
                    }

                    this.c();
                    return null;
                }*/

                protected void onPostExecute(Object o) {
                    if(o == null) {
                        Http.this.log(s.getClass() + "->result: %s", "onFail()");

                        try {
                            AsyViewLayout.g(c, this.n, 33);
                        } catch (Exception var9) {
                            var9.printStackTrace();
                        }

                        try {
                            s.a.onFail(s.TOAST, t, e);
                        } catch (Exception var8) {
                            var8.printStackTrace();
                        }
                    } else {
                        Http.this.log(s.getClass() + "->result: %s", "onSuccess()");

                        try {
                            AsyViewLayout.g(c, this.n, 22);
                        } catch (Exception var7) {
                            var7.printStackTrace();
                        }

                        try {
                            s.a.onSuccess(s.TOAST, t, e, o);
                        } catch (Exception var6) {
                            var6.printStackTrace();
                        }
                    }

                    Http.this.log(s.getClass() + "->result: %s", "onEnd()");

                    try {
                        s.a.onEnd(s.TOAST, t, e);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }

                    try {
                        if(i) {
                            Http.this.dismiss();
                        }
                    } catch (Exception var4) {
                        var4.printStackTrace();
                    }

                    try {
                        p.e().d(this.n).u(s);
                    } catch (Exception var3) {
                        ;
                    }

                }

                private void c() {
                    if(s instanceof AsyPostForm) {
                        s.c();
                    }

                }
            }.execute((Object) new Void[0]);
        }
    }

    public synchronized void show() {
        try {
            this.show(AppApplication.INSTANCE.currentActivity());
        } catch (Exception var2) {
            ;
        }

    }

    public synchronized void show(Context context) {
        this.show(context, (Asy)null);
    }

    private synchronized void show(Context context, Asy a) {
        boolean h = false;

        try {
            h = a.v != null;
        } catch (Exception var6) {
            ;
        }

        try {
            if(!h && this.dl != null && this.c == context) {
                if(!this.dl.isShowing()) {
                    this.dismiss();
                    this.f.show(this.dl);
                }
            } else {
                try {
                    this.dismiss();
                    if(h) {
                        this.f.d.show(this.dl = this.f.d.create(this.c = context));
                    } else {
                        this.f.show(this.dl = this.f.create(this.c = context));
                    }

                    this.dl.setOnCancelListener(this);
                } catch (Exception var5) {
                    this.c = context;
                }
            }
        } catch (Exception var7) {
            this.dismiss();
        }

    }

    public synchronized void dismiss() {
        try {
            this.f.dismiss(this.dl);
        } catch (Exception var4) {
            try {
                this.f.d.dismiss(this.dl);
            } catch (Exception var3) {
                ;
            }
        }

    }

    public synchronized String elog(String t, String m) {
        if(!hh.c() && this.d) {
            Log.e(t, m);
        }

        return m;
    }

    public synchronized String log(String t, String m) {
        if(!hh.c() && this.d) {
            Timber.e(t, new Object[]{m});
        }

        return m;
    }

    public synchronized void logSkip(String t, Class<?> c) {
        logSkip(t,c,"java");
    }

    public synchronized void logSkip(String t, Class<?> c,String classType) {
        if(!hh.c() && this.d) {
            Timber.e(t, "(" + UtilClassName.getName(c) + "." + classType + ":0)");
        }
    }

    public void setIsLog(boolean isLog) {
        this.d = isLog;
    }

    public void setOnStartEndCreateDialog(Http.OnStartEndCreateDialog onStartEndCreateDialog) {
        this.f = onStartEndCreateDialog;
    }

    public void cancelConn() {
        try {
            if(((HttpFinish)this.f.a.getClass().getAnnotation(HttpFinish.class)).value()) {
                AppApplication.INSTANCE.finishActivity(AppApplication.INSTANCE.currentActivity());
            } else {
                AsyViewLayout.g(this.c, UtilClassName.getActionName(this.f.a.getClass()), 33);
            }

            this.q.dispatcher().cancelAll();
            this.log(this.f.a.getClass() + "->cancel: %s", "cancelConn()");
        } catch (Exception var2) {
            ;
        }
    }

    public void onCancel(DialogInterface i) {
        this.cancelConn();
    }

    private static class h {
        private static final Http a = new Http();

        private h() {
        }
    }

    public interface ProgressDialog<D extends Dialog> {
        void show(D var1) throws Exception;

        void dismiss(D var1) throws Exception;

        D create(Context var1) throws Exception;

        void progress(D var1, long var2, long var4, int var6) throws Exception;
    }

    public abstract static class OnStartEndCreateDialog<D extends Dialog> {
        Http.ProgressDialog d = this.createProgressDialog();
        private Asy a;

        public OnStartEndCreateDialog() {
        }

        void i(Asy s) {
            this.a = s;
        }

        public abstract void show(D var1) throws Exception;

        public abstract void dismiss(D var1) throws Exception;

        public abstract D create(Context var1) throws Exception;

        public Http.ProgressDialog createProgressDialog() {
            return new Http.ProgressDialog() {

                @Override
                public void show(Dialog var1) throws Exception {

                    try {
                        OnStartEndCreateDialog.this.show((D) var1);
                    } catch (Exception var3) {
                        ;
                    }
                }

                @Override
                public void dismiss(Dialog var1) throws Exception {

                    try {
                        OnStartEndCreateDialog.this.dismiss((D) var1);
                    } catch (Exception var3) {
                        ;
                    }
                }

                public D create(Context context) {
                    try {
                        return OnStartEndCreateDialog.this.create(context);
                    } catch (Exception var3) {
                        return null;
                    }
                }

                @Override
                public void progress(Dialog var1, long var2, long var4, int var6) throws Exception {

                }

            };
        }
    }

    private class a implements Interceptor {
        private a() {
        }

        public Response intercept(Chain c) throws IOException {
            Request r = c.request();
            if(r.body() != null && r.header("Content-Encoding") == null) {
                Request re = r.newBuilder().header("Content-Encoding", "gzip").method(r.method(), this.y(r.body())).build();
                return c.proceed(re);
            } else {
                return c.proceed(r);
            }
        }

        private RequestBody y(final RequestBody w) {
            return new RequestBody() {
                public MediaType contentType() {
                    return w.contentType();
                }

                public long contentLength() {
                    return -1L;
                }

                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink z = Okio.buffer(new GzipSink(sink));
                    w.writeTo(z);
                    z.close();
                }
            };
        }
    }
}