package com.sr.superhelperx.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.sr.superhelperx.http.base64.AsyBase64;
import com.sr.superhelperx.http.compress.CompressFile;
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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

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
public class AsyPostForm<T> extends Asy<T> {
    public AsyPostForm() {
        super((AsyCallBack)null);
    }

    public AsyPostForm(AsyCallBack<T> asyCallBack) {
        super(asyCallBack);
    }

    protected Request b() {
        MultipartBody.Builder m = (new MultipartBody.Builder()).setType(MultipartBody.FORM);
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
                                m.addFormDataPart(n, String.valueOf(o));
                                Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + o);
                            } else if(this.d.all()) {
                                this.jsonObject.put(n, o);
                            } else {
                                String e1 = (String)o;
                                Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + LogParserJson.json(e1));
                                Http.getInstance().log(this.getClass().toString() + "->post: %s", n + "=" + (e1 = (String)this.SECRET_REQUEST.encrypt((String)o)));
                                m.addFormDataPart(n, e1);
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
                    m.addFormDataPart(this.d.key(), e2);
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
                            m.addFormDataPart(n, String.valueOf(o));
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
            return this.l(new okhttp3.Request.Builder()).url(Http.getInstance().log(this.getClass().toString() + "->post: %s", this.u() + this.k())).post((RequestBody)(this.v != null?new AsyPostForm.c(m.build(), this.a):m.build())).build();
        } catch (Exception var8) {
            Http.getInstance().log(this.getClass().toString() + "->post: %s", this.TOAST = "请求模块生成失败");
            return null;
        }
    }

    private boolean c(MultipartBody.Builder m, String n, Object o) {
        if(o instanceof File) {
            if(o != null) {
                this.a(m, n, (File)o, 0);
            }

            return true;
        } else {
            int i;
            if(o instanceof File[]) {
                if(o != null) {
                    File[] var19 = (File[])((File[])o);

                    for(i = 0; i < var19.length; ++i) {
                        this.a(m, n, var19[i], i);
                    }
                }

                return true;
            } else {
                List fs;
                if(o instanceof List && ((List)o).size() > 0) {
                    if(o != null) {
                        if(((List)o).get(0) instanceof File) {
                            fs = (List)o;

                            for(i = 0; i < fs.size(); ++i) {
                                this.a(m, n, (File)fs.get(i), i);
                            }
                        } else {
                            fs = (List)o;
                            Object var22;
                            String var24;
                            if(this.SECRET_REQUEST != null) {
                                for(i = 0; i < fs.size(); ++i) {
                                    var22 = fs.get(i);
                                    if(this.skipSecret(n)) {
                                        m.addFormDataPart(var24 = this.fieldKey(n, i), String.valueOf(var22));
                                        Http.getInstance().log(this.getClass().toString() + "->post: %s", var24 + "=" + var22);
                                    } else {
                                        try {
                                            this.jsonObject.put(this.fieldKey(n, i), var22);
                                        } catch (JSONException var13) {
                                            ;
                                        }
                                    }
                                }
                            } else {
                                for(i = 0; i < fs.size(); ++i) {
                                    var22 = fs.get(i);
                                    m.addFormDataPart(var24 = this.fieldKey(n, i), String.valueOf(var22));
                                    Http.getInstance().log(this.getClass().toString() + "->post: %s", var24 + "=" + var22);
                                }
                            }
                        }
                    }

                    return true;
                } else if(o instanceof Map && ((Map)o).size() > 0) {
                    if(o != null) {
                        boolean var18 = false;
                        Map var20 = (Map)o;
                        Iterator s = var20.values().iterator();
                        if(s.hasNext()) {
                            Object b = s.next();
                            if(!(b instanceof List) || ((List)b).size() <= 0) {
                                return true;
                            }

                            var18 = ((List)b).get(0) instanceof File;
                        }

                        Map.Entry e;
                        List fs1;
                        int i1;
                        Set var21;
                        Iterator var23;
                        if(var18) {
                            try {
                                var21 = var20.entrySet();
                                var23 = var21.iterator();

                                while(var23.hasNext()) {
                                    e = (Map.Entry)var23.next();
                                    n = (String)e.getKey();
                                    fs1 = (List)e.getValue();

                                    for(i1 = 0; i1 < fs1.size(); ++i1) {
                                        this.a(m, n, (File)fs1.get(i1), i1);
                                    }
                                }
                            } catch (Exception var16) {
                                ;
                            }
                        } else {
                            try {
                                var21 = var20.entrySet();
                                var23 = var21.iterator();

                                while(true) {
                                    while(var23.hasNext()) {
                                        e = (Map.Entry)var23.next();
                                        n = (String)e.getKey();
                                        fs1 = (List)e.getValue();
                                        Object c;
                                        String k;
                                        if(this.SECRET_REQUEST != null) {
                                            for(i1 = 0; i1 < fs1.size(); ++i1) {
                                                c = fs1.get(i1);
                                                if(this.skipSecret(n)) {
                                                    m.addFormDataPart(k = this.fieldKey(n, i1), String.valueOf(c));
                                                    Http.getInstance().log(this.getClass().toString() + "->post: %s", k + "=" + c);
                                                } else {
                                                    try {
                                                        this.jsonObject.put(this.fieldKey(n, i1), c);
                                                    } catch (Exception var14) {
                                                        ;
                                                    }
                                                }
                                            }
                                        } else {
                                            for(i1 = 0; i1 < fs1.size(); ++i1) {
                                                c = fs1.get(i1);
                                                m.addFormDataPart(k = this.fieldKey(n, i1), String.valueOf(c));
                                                Http.getInstance().log(this.getClass().toString() + "->post: %s", k + "=" + c);
                                            }
                                        }
                                    }

                                    return true;
                                }
                            } catch (Exception var15) {
                                ;
                            }
                        }
                    }

                    return true;
                } else if(o instanceof AsyBase64) {
                    if(o != null) {
                        this.e(m, n, (AsyBase64)o, 0);
                    }

                    return true;
                } else if(o instanceof AsyBase64[]) {
                    if(o != null) {
                        AsyBase64[] var17 = (AsyBase64[])((AsyBase64[])o);

                        for(i = 0; i < var17.length; ++i) {
                            this.e(m, n, var17[i], i);
                        }
                    }

                    return true;
                } else if(o instanceof List && ((List)o).size() > 0 && ((List)o).get(0) instanceof AsyBase64) {
                    if(o != null) {
                        fs = (List)o;

                        for(i = 0; i < fs.size(); ++i) {
                            this.e(m, n, (AsyBase64)fs.get(i), i);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private void a(MultipartBody.Builder m, String n, File f, int p) {
        try {
            String k = this.fieldKey(n, p);
            String fn = f.getName();
            String t = this.d(fn);
            m.addFormDataPart(k, fn, RequestBody.create(MediaType.parse(t), f instanceof CompressFile ?((CompressFile)f).c():f));
            Http.getInstance().log(this.getClass().toString() + "->post: %s", "type=" + t + " file_name=" + fn + " file=" + k);
        } catch (Exception var8) {
            ;
        }
    }

    private void e(MultipartBody.Builder m, String n, AsyBase64 f, int p) {
        try {
            String k = this.fieldKey(n, p);
            String v = f.toBase64();
            m.addFormDataPart(k, v);
            Http.getInstance().log(this.getClass().toString() + "->post: %s", k + "=" + v);
        } catch (Exception var7) {
            ;
        }

    }

    private String d(String p) {
        String c = URLConnection.getFileNameMap().getContentTypeFor(p);
        if(c == null) {
            c = "application/octet-stream";
        }

        return c;
    }

    private class c extends RequestBody {
        private BufferedSink b;
        private RequestBody r;
        private AsyCallBack a;
        private long w;
        private long l;
        private Handler h = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                try {
                    AsyPostForm.c.this.a.onProgress(AsyPostForm.c.this.l, AsyPostForm.c.this.w, msg.what);
                } catch (Exception var4) {
                }

                try {
                    AsyPostForm.this.pd.progress(AsyPostForm.this.dl, AsyPostForm.c.this.l, AsyPostForm.c.this.w, msg.what);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }

            }
        };

        public c(RequestBody r, AsyCallBack a) {
            this.r = r;
            this.a = a;
        }

        public MediaType contentType() {
            return this.r.contentType();
        }

        public long contentLength() {
            try {
                return this.r.contentLength();
            } catch (IOException var2) {
                return 0L;
            }
        }

        public void writeTo(final BufferedSink s) throws IOException {
            if(this.b == null) {
                this.b = Okio.buffer(new ForwardingSink(s) {
                    public void write(Buffer s, long b) throws IOException {
                        super.write(s, b);
                        if(AsyPostForm.c.this.l == 0L) {
                            AsyPostForm.c.this.l = AsyPostForm.c.this.contentLength();
                        }

                        AsyPostForm.c.this.w = AsyPostForm.c.this.w + b;
                        AsyPostForm.c.this.h.sendEmptyMessage((int)((float) AsyPostForm.c.this.w / (float) AsyPostForm.c.this.l * 100.0F));
                    }
                });
            }

            this.r.writeTo(this.b);
            this.b.flush();
        }
    }
}