package com.sr.superhelperx.http;

import android.app.Dialog;
import android.content.Context;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.http.Http.ProgressDialog;
import com.sr.superhelperx.http.base64.AsyBase64;
import com.sr.superhelperx.http.note.HttpCache;
import com.sr.superhelperx.http.note.HttpDebugServer;
import com.sr.superhelperx.http.note.HttpEncoder;
import com.sr.superhelperx.http.note.HttpFiltration;
import com.sr.superhelperx.http.note.HttpInlet;
import com.sr.superhelperx.http.note.HttpProgress;
import com.sr.superhelperx.http.note.HttpSecret;
import com.sr.superhelperx.http.note.HttpSeparator;
import com.sr.superhelperx.http.note.HttpServer;
import com.sr.superhelperx.secret.Secret;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Request;

/**
 * Created by Hang.Yang on 2018/8/17 14:41.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class Asy<T> extends AsyParser<T> {
    public static final int ASY_WAIT = -100;
    protected Secret<String, String> SECRET_RESPONSE;
    protected Secret<String, String> SECRET_REQUEST;
    protected Secret<String, String> SECRET_SERVER;
    protected Secret<String, String> SECRET_DEBUG_SERVER;
    protected Secret<String, String> SECRET_INLET;
    protected Context context;
    protected boolean isShow;
    protected int type;
    Class<? extends Asy> g = this.getClass();
    HttpFiltration c;
    HttpSeparator x;
    HttpProgress v;
    HttpEncoder t;
    HttpServer r;
    HttpDebugServer rDebug;
    HttpSecret d;
    HttpInlet i;
    HttpCache p;
    ProgressDialog pd;
    AsyCallBack<T> a;
    AsyParser<T> s;
    String l;
    JSONObject jsonObject;
    Dialog dl;
    private Map<String, String> h;

    public Asy(AsyCallBack<T> asyCallBack) {
        this.c = (HttpFiltration)this.g.getAnnotation(HttpFiltration.class);
        this.x = (HttpSeparator)this.g.getAnnotation(HttpSeparator.class);
        this.v = (HttpProgress)this.g.getAnnotation(HttpProgress.class);
        this.t = (HttpEncoder)this.g.getAnnotation(HttpEncoder.class);
        this.r = (HttpServer)this.g.getAnnotation(HttpServer.class);
        this.rDebug = (HttpDebugServer)this.g.getAnnotation(HttpDebugServer.class);
        this.d = (HttpSecret)this.g.getAnnotation(HttpSecret.class);
        this.i = (HttpInlet)this.g.getAnnotation(HttpInlet.class);
        this.p = (HttpCache)this.g.getAnnotation(HttpCache.class);
        this.l = "";
        this.h = new HashMap();
        this.s = this;

        try {
            (this.a = asyCallBack).a(this);
        } catch (Exception var3) {
            ;
        }

    }

    public void operation() {
    }

    public Asy asyParser(AsyParser<T> asyParser) {
        this.s = asyParser;
        return this;
    }

    public Asy callBack(AsyCallBack<T> asyCallBack) {
        this.a = asyCallBack;
        return this;
    }

    public void e() {
        this.execute(this.context, this.isShow, this.type, (Object)null);
    }

    public void execute() {
        this.execute((Context) AppApplication.INSTANCE.currentActivity());
    }

    public void execute(Object object) {
        this.execute(AppApplication.INSTANCE.currentActivity(), object);
    }

    public void execute(boolean isShow) {
        this.execute(AppApplication.INSTANCE.currentActivity(), isShow);
    }

    public void execute(int type) {
        this.execute(AppApplication.INSTANCE.currentActivity(), type);
    }

    public void execute(boolean isShow, Object object) {
        this.execute(AppApplication.INSTANCE.currentActivity(), isShow, object);
    }

    public void execute(int type, Object object) {
        this.execute(AppApplication.INSTANCE.currentActivity(), type, object);
    }

    public void execute(boolean isShow, int type) {
        this.execute(AppApplication.INSTANCE.currentActivity(), isShow, type);
    }

    public void execute(boolean isShow, int type, Object object) {
        this.execute(AppApplication.INSTANCE.currentActivity(), isShow, type, object);
    }

    public void execute(Context context) {
        this.execute(context, true, 0, (Object)null);
    }

    public void execute(Context context, Object object) {
        this.execute(context, true, 0, object);
    }

    public void execute(Context context, boolean isShow) {
        this.execute(context, isShow, 0, (Object)null);
    }

    public void execute(Context context, int type) {
        this.execute(context, true, type, (Object)null);
    }

    public void execute(Context context, boolean isShow, Object object) {
        this.execute(context, isShow, 0, object);
    }

    public void execute(Context context, int type, Object object) {
        this.execute(context, true, type, object);
    }

    public void execute(Context context, boolean isShow, int type) {
        this.execute(context, isShow, type, (Object)null);
    }

    public void execute(Context context, boolean isShow, int type, Object object) {
        this.l = "";
        this.jsonObject = new JSONObject();
        Http.getInstance().g(this.context = context, this.isShow = isShow, this.type = type, object, this);
    }

    public T executeReturnData() {
        this.l = "";
        this.jsonObject = new JSONObject();
        return (T) Http.getInstance().sendRequest(this);
    }

    Secret<String, String> getSecret() {
        return this.SECRET_RESPONSE;
    }

    protected String server() {
        return null;
    }

    protected String inlet() {
        return null;
    }

    protected String serverInler(String url) {
        return url;
    }

    protected String customAttribute() {
        return "";
    }

    protected boolean skipSecret(String key) {
        return false;
    }

    protected boolean code(boolean isSuccessful, int code) {
        return isSuccessful || code == 200;
    }

    protected String fieldKey(String fieldName, int position) {
        return fieldName;
    }

    public void header(String name, String value) {
        this.h.put(name, value);
    }

    public void clear() {
        Field[] f = this.getClass().getDeclaredFields();
        Field[] var2 = f;
        int var3 = f.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field s = var2[var4];

            try {
                s.set(this, (Object)null);
            } catch (Exception var7) {
                ;
            }
        }

    }

    public List<Field> f() {
        Http.getInstance().logSkip(this.g.toString() + "->conn: %s", this.g);
        ArrayList t = new ArrayList();
        Field[] f = this.g.getDeclaredFields();
        Field[] var3 = f;
        int var4 = f.length;

        int var5;
        Field s;
        for(var5 = 0; var5 < var4; ++var5) {
            s = var3[var5];
            t.add(s);
        }

        f = this.g.getSuperclass().getDeclaredFields();
        var3 = f;
        var4 = f.length;

        for(var5 = 0; var5 < var4; ++var5) {
            s = var3[var5];
            t.add(s);
        }

        return t;
    }

    void c() {
        Field[] f = this.getClass().getDeclaredFields();
        Field[] var2 = f;
        int var3 = f.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field s = var2[var4];

            try {
                Object o = s.get(this);
                if(!(o instanceof File) && !(o instanceof AsyBase64)) {
                    if(o instanceof List && o != null && ((List)o).size() > 0 && ((List)o).get(0) instanceof File) {
                        ((List)o).clear();
                    } else if(o instanceof Map) {
                        Map p = (Map)o;
                        Iterator var8 = p.entrySet().iterator();

                        while(var8.hasNext()) {
                            Map.Entry e = (Map.Entry)var8.next();
                            ((List)e.getValue()).clear();
                        }

                        p.clear();
                    }
                } else {
                    s.set(this, (Object)null);
                }
            } catch (Exception var10) {
                ;
            }
        }

    }

    Request.Builder l(Request.Builder b) {
        String v = this.p.name() + (this.p.name().equals("max-stale")?"=" + this.p.time():"");
        b.header("cache-control", v);
        Http.getInstance().log(this.getClass().toString() + "->header: %s", "[cache-control:" + v + "]");
        Set s = this.h.entrySet();
        Iterator var4 = s.iterator();

        while(var4.hasNext()) {
            Map.Entry e = (Map.Entry)var4.next();
            b.addHeader((String)e.getKey(), (String)e.getValue());
            Http.getInstance().log(this.getClass().toString() + "->header: %s", "[" + (String)e.getKey() + ":" + (String)e.getValue() + "]");
        }

        return b;
    }

    public String u() {
        String server = HttpServerAddress.getServerAddress().isEmpty() ? HttpServerAddress.getDebugAddress() ? this.rDebug.value() : this.r.value() : HttpServerAddress.getServerAddress();
        return this.serverInler(this.k(this.server(), server, HttpServerAddress.getDebugAddress() ? this.SECRET_DEBUG_SERVER : this.SECRET_SERVER) + this.k(this.inlet(), this.i.value(), this.SECRET_INLET));
//        return this.serverInler(this.k(this.server(), this.r.value(), this.SECRET_SERVER) + this.k(this.inlet(), this.i.value(), this.SECRET_INLET));
//        return this.serverInler(this.k(this.server(), HttpServerAddress.getServerAddress(), this.SECRET_SERVER) + this.k(this.inlet(), this.i.value(), this.SECRET_INLET));
    }

    private String k(String k, String m, Secret<String, String> s) {
        String u = "";

        try {
            u = k != null && !k.equals("")?k:m;
            u = s != null?(String)s.decrypt(u):u;
        } catch (Exception var6) {
            ;
        }

        return u;
    }

    boolean b(Object o) {
        String[] t = this.c.value();

        for(int i = 0; i < t.length; ++i) {
            String k = t[i];
            if(k.equals("[null]") && o == null || o != null && o.equals(k)) {
                return false;
            }
        }

        return true;
    }

    Object x(Object o) {
        return o == null?"":o;
    }

    String c(String o, String a) {
        try {
            return URLEncoder.encode(o, a);
        } catch (Exception var4) {
            return "";
        }
    }

    void v(String n, Object o) {
        this.l = this.l + n + this.x.value_separator() + (o instanceof String?(this.t.value()?this.c((String)o, this.t.charset()):o):String.valueOf(o)) + this.x.element_separator();
    }

    public String k() {
        return this.l != ""?(this.x.value()?this.x.url_separator() + (this.l = this.l.length() == 0?"":this.l.substring(0, this.l.length() - 1)):this.customAttribute()):"";
    }

    boolean w(Object o) {
        return !o.equals("$change") && !o.equals("serialVersionUID");
    }

    protected abstract Request b() throws Exception;
}
