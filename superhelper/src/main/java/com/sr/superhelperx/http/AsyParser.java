package com.sr.superhelperx.http;

import android.text.TextUtils;
import android.util.Log;

import com.sr.superhelperx.log.LogParserJson;
import com.sr.superhelperx.secret.Secret;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by Hang.Yang on 2018/8/17 14:43.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AsyParser<T> {
    protected String TOAST = "";

    public AsyParser() {
    }

    Secret<String, String> getSecret() {
        return null;
    }

    protected T parser(Response r) {
        Object t = null;

        Headers h;
        String b;
        String f;
        try {
            this.parserHeaders(h = r.headers());
            Http.getInstance().log(this.getClass().toString() + "->response_headers: \n%s", h.toString());
            f = b = r.body().string();
            //与.net进行数据交互时使用
//            f = b = removeQuotes(URLDecoder.decode(r.body().string(),"utf-8"));
            if (!b.contains("{") && !b.contains("[")){
                Http.getInstance().log(this.getClass().toString() + "->response_data: \n%s", b);
            }else if (f.length() > 3200){
                int chunkCount = f.length() / 3200;
                for (int i = 0; i <= chunkCount; i++) {
                    int max = 3200 * (i + 1);
                    if (max >= f.length()) {
                        Http.getInstance().log("%s", f.substring(3200 * i));
                    }else if (i == 0){
                        Http.getInstance().log(this.getClass().toString() + "->response_data: \n%s", f.substring(3200 * i, max));
                    }else {
                        Http.getInstance().log("%s", f.substring(3200 * i, max));
                    }
                }
            }else {
                Http.getInstance().log(this.getClass().toString() + "->response_data: \n%s", LogParserJson.json(f));
            }
        } catch (IOException var14) {
            Http.getInstance().log(this.getClass().toString() + "->response_error: %s", "无法从响应流中读取信息");
            return null;
        }

        if(this.getSecret() != null) {
            try {
                f = b = (String)this.getSecret().decrypt(b);
                Http.getInstance().log(this.getClass().toString() + "->response_data: \n%s", LogParserJson.json(f));
            } catch (Exception var13) {
                Http.getInstance().log(this.getClass().toString() + "->response_error: %s", "密文解密失败,或者不是密文");
            }
        }

        Object o;
        try {
            o = new JSONObject(b);
        } catch (JSONException var12) {
            try {
                o = new JSONArray(b);
            } catch (JSONException var11) {
                o = b;
//                Http.getInstance().log(this.getClass().toString() + "->response_error: %s", "JSON转换异常");
//                return null;
            }
        }

        if(o instanceof JSONObject) {
            try {
                t = this.parser((JSONObject)o);
            } catch (Exception var10) {
                Http.getInstance().log(this.getClass().toString() + "->response_error: %s", "JSONObject解析异常");
                var10.printStackTrace();
            }
        } else if (o instanceof JSONArray){
            try {
                t = this.parser((JSONArray)o);
            } catch (Exception var9) {
                Http.getInstance().log(this.getClass().toString() + "->response_error: %s", "JSONArray解析异常");
                var9.printStackTrace();
            }
        } else {
            try {
                t = this.parser((String) o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.parserHeaders(h, (T) t);
        return (T) t;
    }

    protected T parser(JSONObject object) throws Exception {
        return null;
    }

    protected T parser(JSONArray array) throws Exception {
        return null;
    }

    protected T parser(String str) throws Exception {
        return null;
    }

    protected void parserHeaders(Headers headers) {
    }

    protected void parserHeaders(Headers headers, T t) {
    }

    private String removeQuotes(String data) {

        if (TextUtils.isEmpty(data)) {

            return data;
        }

        if (data.startsWith("\"")) {

            return data.substring(1,data.length() - 1);
        } else {

            return data;
        }
    }

    public void e(String tag, String msg){
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;
        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        }else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
