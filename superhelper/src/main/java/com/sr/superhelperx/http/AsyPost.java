package com.sr.superhelperx.http;

import com.sr.superhelperx.http.note.HttpCache;
import com.sr.superhelperx.http.note.HttpDebugServer;
import com.sr.superhelperx.http.note.HttpEncoder;
import com.sr.superhelperx.http.note.HttpFiltration;
import com.sr.superhelperx.http.note.HttpFinish;
import com.sr.superhelperx.http.note.HttpGZIP;
import com.sr.superhelperx.http.note.HttpGet;
import com.sr.superhelperx.http.note.HttpInlet;
import com.sr.superhelperx.http.note.HttpNew;
import com.sr.superhelperx.http.note.HttpSecret;
import com.sr.superhelperx.http.note.HttpSeparator;
import com.sr.superhelperx.http.note.HttpServer;
import com.sr.superhelperx.log.LogParserJson;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Hang.Yang on 2018/8/17 15:00.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@HttpNew
@HttpGZIP
@HttpCache
@HttpInlet
@HttpFinish
@HttpServer
@HttpDebugServer
@HttpSecret
@HttpEncoder
@HttpSeparator
@HttpFiltration
public class AsyPost<T> extends Asy<T> {

    public AsyPost() {
        super((AsyCallBack)null);
    }

    public AsyPost(AsyCallBack<T> asyCallBack) {
        super(asyCallBack);
    }

    protected Request b() throws Exception {
        FormBody.Builder m = new FormBody.Builder();
        RequestBody body = null;
        String json;
        List f = this.f();
        String n;
        Object o;
        Iterator e;
        Field s;
        if(this.SECRET_REQUEST != null) {
            e = f.iterator();

            while(e.hasNext()) {
                s = (Field)e.next();

                try {
                    //设置属性总是允许访问
                    s.setAccessible(true);
                    n = s.getName();
                    o = s.get(this);
                    if(s.getAnnotation(HttpGet.class) == null) {
                        if(this.w(n) && !this.c(m, n, o) && this.b(o)) {
                            o = this.x(o);
                            if(this.skipSecret(n)) {
                                m.add(n, String.valueOf(o));
                                Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + o);
                            } else if(this.d.all()) {
                                this.jsonObject.put(n, o);
                            } else {
                                String e1 = (String)o;
                                Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + LogParserJson.json(e1));
                                Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + (e1 = (String)this.SECRET_REQUEST.encrypt((String)o)));
                                m.add(n, e1);
                            }
                        }
                    } else {
                        try {
                            if(this.w(n) && this.b(o)) {
                                this.v(n, this.x(o));
                            }
                        } catch (Exception var12) {
                            var12.printStackTrace();
                        }
                    }
                } catch (Exception var13) {
                    ;
                }
            }

            if(this.d.all()) {
                String e2 = Http.getInstance().log(this.getClass().toString() + "->post: %s", this.jsonObject.toString());

                try {
                    Http.getInstance().log(this.getClass().toString() + "->post: %s", this.d.key() + "=" + (e2 = (String)this.SECRET_REQUEST.encrypt(e2)));
                    m.add(this.d.key(), e2);
                } catch (Exception var11) {
                    Http.getInstance().log(this.getClass().toString() + "->post: %s", this.TOAST = "明文加密失败");
                }
            }
        } else {

            e = f.iterator();

            while(e.hasNext()) {
                s = (Field)e.next();

                try {
                    //设置属性总是允许访问
                    s.setAccessible(true);
                    n = s.getName();
                    o = s.get(this);
                    if(s.getAnnotation(HttpGet.class) == null) {
                        if(this.w(n) && !this.c(m, n, o) && this.b(o)) {
                            o = this.x(o);
                            m.add(n, String.valueOf(o));
                            Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + o);
                        }
                    } else {
                        try {
                            if(this.w(n) && this.b(o)) {
                                this.v(n, this.x(o));
                            }
                        } catch (Exception var9) {
                            var9.printStackTrace();
                        }
                    }
                } catch (Exception var10) {
                    ;
                }
            }
        }

        try {
            return this.l(new okhttp3.Request.Builder()).url(Http.getInstance().log(this.getClass().toString() + "->post: %s", this.u() + this.k())).post(m.build()).build();
        } catch (Exception var8) {
            Http.getInstance().log(this.getClass().toString() + "->post: %s", this.TOAST = "请求模块生成失败");
            return null;
        }
    }

    private boolean c(FormBody.Builder m, String n, Object o) {
        if(o instanceof List && ((List)o).size() > 0) {
            if(o != null) {
                List var13 = (List)o;
                int var14;
                Object var15;
                String var16;
                if(this.SECRET_REQUEST != null) {
                    for(var14 = 0; var14 < var13.size(); ++var14) {
                        var15 = var13.get(var14);
                        if(this.skipSecret(n)) {
                            m.add(var16 = this.fieldKey(n, var14), String.valueOf(var15));
                            Http.getInstance().log(this.getClass().toString() + "->post: %s", var16 + "=" + var15);
                        } else {
                            try {
                                this.jsonObject.put(this.fieldKey(n, var14), var15);
                            } catch (JSONException var11) {
                                ;
                            }
                        }
                    }
                } else {
                    for(var14 = 0; var14 < var13.size(); ++var14) {
                        var15 = var13.get(var14);
                        m.add(var16 = this.fieldKey(n, var14), String.valueOf(var15));
                        Http.getInstance().log(this.getClass().toString() + "->post: %s", var16 + "=" + var15);
                    }
                }
            }

            return true;
        } else if(o instanceof Map && ((Map)o).size() > 0) {
            if(o != null) {
                Set s = ((Map)this.p).entrySet();
                Iterator i = s.iterator();

                while(true) {
                    while(i.hasNext()) {
                        Map.Entry e = (Map.Entry)i.next();
                        n = (String)e.getKey();
                        List fs = (List)e.getValue();
                        int i1;
                        Object c;
                        String k;
                        if(this.SECRET_REQUEST != null) {
                            for(i1 = 0; i1 < fs.size(); ++i1) {
                                c = fs.get(i1);
                                if(this.skipSecret(n)) {
                                    m.add(k = this.fieldKey(n, i1), String.valueOf(c));
                                    Http.getInstance().log(this.getClass().toString() + "->post: %s", k + "=" + c);
                                } else {
                                    try {
                                        this.jsonObject.put(this.fieldKey(n, i1), c);
                                    } catch (Exception var12) {
                                        ;
                                    }
                                }
                            }
                        } else {
                            for(i1 = 0; i1 < fs.size(); ++i1) {
                                c = fs.get(i1);
                                m.add(k = this.fieldKey(n, i1), String.valueOf(c));
                                Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + k);
                            }
                        }
                    }

                    return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
