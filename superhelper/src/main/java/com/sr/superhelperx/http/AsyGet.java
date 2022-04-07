package com.sr.superhelperx.http;

import com.sr.superhelperx.http.note.HttpCache;
import com.sr.superhelperx.http.note.HttpEncoder;
import com.sr.superhelperx.http.note.HttpFiltration;
import com.sr.superhelperx.http.note.HttpFinish;
import com.sr.superhelperx.http.note.HttpGZIP;
import com.sr.superhelperx.http.note.HttpNew;
import com.sr.superhelperx.http.note.HttpSecret;
import com.sr.superhelperx.http.note.HttpSeparator;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Request;

/**
 * Created by Hang.Yang on 2018/8/17 14:59.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@HttpNew
@HttpGZIP
@HttpCache
@HttpFinish
@HttpSecret
@HttpEncoder
@HttpSeparator
@HttpFiltration
public class AsyGet<T> extends Asy<T> {
    public AsyGet() {
        super((AsyCallBack)null);
    }

    public AsyGet(AsyCallBack<T> asyCallBack) {
        super(asyCallBack);
    }

    protected Request b() {
        List f = this.f();
        if(this.x.value()) {
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
                        if(this.w(n) && !this.c(n, o) && this.b(o)) {
                            o = this.x(o);
                            if(this.skipSecret(n)) {
                                this.v(n, o);
                            } else {
                                this.jsonObject.put(s.getName(), o);
                            }
                        }
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }

                try {
                    this.v(this.d.key(), URLEncoder.encode((String)this.SECRET_REQUEST.encrypt(Http.getInstance().log(this.getClass().toString() + "->get: %s", this.jsonObject.toString())), "UTF-8"));
                } catch (Exception var9) {
                    Http.getInstance().log(this.getClass().toString() + "->get: %s", this.TOAST = "明文加密失败");
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
                        if(this.w(n) && !this.c(n, o) && this.b(o)) {
                            this.v(n, this.x(o));
                        }
                    } catch (Exception var8) {
                        var8.printStackTrace();
                    }
                }
            }
        }

        try {
            return this.l(new Request.Builder()).url(Http.getInstance().log(this.getClass().toString() + "->get: %s", this.u() + this.k())).build();
        } catch (Exception var7) {
            Http.getInstance().log(this.getClass().toString() + "->get: %s", this.TOAST = "请求模块生成失败");
            return null;
        }
    }

    private boolean c(String n, Object o) {
        if(o instanceof List && ((List)o).size() > 0) {
            if(o != null) {
                List var13 = (List)o;
                int var14;
                Object var15;
                if(this.SECRET_REQUEST != null) {
                    for(var14 = 0; var14 < var13.size(); ++var14) {
                        var15 = var13.get(var14);
                        if(this.skipSecret(n)) {
                            this.v(this.fieldKey(n, var14), var15);
                        } else {
                            try {
                                this.jsonObject.put(this.fieldKey(n, var14), var15);
                            } catch (JSONException var10) {
                                ;
                            }
                        }
                    }
                } else {
                    for(var14 = 0; var14 < var13.size(); ++var14) {
                        var15 = var13.get(var14);
                        this.v(n, var15);
                    }
                }
            }

            return true;
        } else if(o instanceof Map && ((Map)o).size() > 0) {
            if(o != null) {
                try {
                    Set s = ((Map)this.p).entrySet();
                    Iterator i = s.iterator();

                    while(true) {
                        while(i.hasNext()) {
                            Map.Entry e = (Map.Entry)i.next();
                            n = (String)e.getKey();
                            List fs = (List)e.getValue();
                            int i1;
                            Object c;
                            if(this.SECRET_REQUEST != null) {
                                for(i1 = 0; i1 < fs.size(); ++i1) {
                                    c = fs.get(i1);
                                    if(this.skipSecret(n)) {
                                        this.v(this.fieldKey(n, i1), c);
                                    } else {
                                        try {
                                            this.jsonObject.put(this.fieldKey(n, i1), c);
                                        } catch (Exception var11) {
                                            ;
                                        }
                                    }
                                }
                            } else {
                                for(i1 = 0; i1 < fs.size(); ++i1) {
                                    c = fs.get(i1);
                                    this.v(this.fieldKey(n, i1), c);
                                }
                            }
                        }

                        return true;
                    }
                } catch (Exception var12) {
                    ;
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
