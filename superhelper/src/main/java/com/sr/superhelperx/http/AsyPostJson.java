package com.sr.superhelperx.http;

import com.sr.superhelperx.http.note.HttpCache;
import com.sr.superhelperx.http.note.HttpDebugServer;
import com.sr.superhelperx.http.note.HttpEncoder;
import com.sr.superhelperx.http.note.HttpFiltration;
import com.sr.superhelperx.http.note.HttpFinish;
import com.sr.superhelperx.http.note.HttpGZIP;
import com.sr.superhelperx.http.note.HttpInlet;
import com.sr.superhelperx.http.note.HttpNew;
import com.sr.superhelperx.http.note.HttpSecret;
import com.sr.superhelperx.http.note.HttpSeparator;
import com.sr.superhelperx.http.note.HttpServer;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Create by hangyang in chuangtu
 * 2019/3/14 14:27
 * Keep Calm and Carry On
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
public class AsyPostJson<T> extends Asy<T> {

    public AsyPostJson() {
        super((AsyCallBack)null);
    }

    public AsyPostJson(AsyCallBack<T> asyCallBack) {
        super(asyCallBack);
    }

    protected Request b() throws Exception {

        RequestBody body = null;
        String json;
        List f = this.f();
        Iterator e = f.iterator();
        Field s = (Field)e.next();
        //设置属性总是允许访问
        s.setAccessible(true);
        json = String.valueOf(s.get(this));

        Http.getInstance().log(this.getClass().toString() + "->post: %s", s.getName() + "=" + json);

        body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        try {
            return this.l(new Request.Builder())
                    .url(Http.getInstance().log(this.getClass().toString() + "->post: %s", this.u() + this.k()))
                    .post(body)
                    .build();
        } catch (Exception var8) {
            Http.getInstance().log(this.getClass().toString() + "->post: %s", this.TOAST = "请求模块生成失败");
            return null;
        }
    }
}
