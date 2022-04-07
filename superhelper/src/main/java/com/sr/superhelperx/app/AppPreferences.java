package com.sr.superhelperx.app;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.sr.superhelperx.secret.Secret;
import com.sr.superhelperx.secret.SecretAESDESede;
import com.sr.superhelperx.util.UtilApp;
import com.sr.superhelperx.util.UtilMD5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Hang.Yang on 2018/8/17 15:24.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi", "CommitPrefEdits"})
public abstract class AppPreferences {
    private Map<String, String> m = new HashMap();
    private Secret<String, String> s = new SecretAESDESede(UtilMD5.MD5Encode(UtilApp.packageName(), "UTF-8"), "AES/CBC/PKCS7Padding");
    private SharedPreferences b;
    private SharedPreferences.Editor a;

    public AppPreferences(String name) {
        this.b = AppApplication.AINSTANCE.getSharedPreferences(UtilMD5.MD5Encode(name, "UTF-8"), 0);
        this.a = this.b.edit();
    }

    public void putBoolean(String arg0, boolean arg1) {
        this.a.putBoolean(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1).commit();
    }

    public void putFloat(String arg0, float arg1) {
        this.a.putFloat(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1).commit();
    }

    public void putInt(String arg0, int arg1) {
        this.a.putInt(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1).commit();
    }

    public void putLong(String arg0, long arg1) {
        this.a.putLong(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1).commit();
    }

    public void putString(String arg0, String arg1) {
        try {
            this.m.put(arg0, arg1);
            this.a.putString(UtilMD5.MD5Encode(arg0, "UTF-8"), (String)this.s.encrypt(arg1)).commit();
        } catch (Exception var4) {
            ;
        }

    }

    public void putStringSet(String arg0, Set<String> arg1) {
        this.a.putStringSet(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1).commit();
    }

    public boolean getBoolean(String arg0, boolean arg1) {
        return this.b.getBoolean(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1);
    }

    public float getFloat(String arg0, float arg1) {
        return this.b.getFloat(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1);
    }

    public int getInt(String arg0, int arg1) {
        return this.b.getInt(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1);
    }

    public long getLong(String arg0, long arg1) {
        return this.b.getLong(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1);
    }

    public String getString(String arg0, String arg1) {
        String v = this.b.getString(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1);
        if(!v.equals(arg1)) {
            try {
                if(!this.m.containsKey(arg0)) {
                    this.m.put(arg0, this.s.decrypt(v));
                }

                return (String)this.m.get(arg0);
            } catch (Exception var5) {
                return "数据异常";
            }
        } else {
            return v;
        }
    }

    public Set<String> getStringSet(String arg0, Set<String> arg1) {
        return this.b.getStringSet(UtilMD5.MD5Encode(arg0, "UTF-8"), arg1);
    }

    public void putHistory(String history, int size) {
        if(history != null) {
            String t = history.trim();
            if(!t.equals("")) {
                List h = this.getHistory();
                if(h.contains(t)) {
                    h.remove(t);
                }

                h.add(0, t);
                if(h.size() > size) {
                    h.remove(h.size() - 1);
                }

                this.putString("history", h.toString().replace(" ", "").replace("[", "").replace("]", ""));
            }

        }
    }

    public void removeHistory(int position){

        if (position >= 0){

            List h = this.getHistory();

            if (h.size() > position){

                h.remove(position);

                this.putString("history", h.toString().replace(" ", "").replace("[", "").replace("]", ""));
            }
        }
    }

    public List<String> getHistory() {
        ArrayList l = new ArrayList();

        try {
            String h = this.getString("history", "");
            if(!h.equals("")) {
                l.addAll(Arrays.asList(h.split(",")));
            }
        } catch (Exception var3) {
            ;
        }

        return l;
    }

    public void clearHistory() {
        this.putStringSet("history", (Set)null);
    }

    public void putFindHistory(String history, int size) {
        if(history != null) {
            String t = history.trim();
            if(!t.equals("")) {
                List h = this.getFindHistory();
                if(h.contains(t)) {
                    h.remove(t);
                }

                h.add(0, t);
                if(h.size() > size) {
                    h.remove(h.size() - 1);
                }

                this.putString("findhistory", h.toString().replace(" ", "").replace("[", "").replace("]", ""));
            }

        }
    }

    public void removeFindHistory(int position){

        if (position >= 0){

            List h = this.getFindHistory();

            if (h.size() > position){

                h.remove(position);

                this.putString("findhistory", h.toString().replace(" ", "").replace("[", "").replace("]", ""));
            }
        }
    }

    public List<String> getFindHistory() {
        ArrayList l = new ArrayList();

        try {
            String h = this.getString("findhistory", "");
            if(!h.equals("")) {
                l.addAll(Arrays.asList(h.split(",")));
            }
        } catch (Exception var3) {
            ;
        }

        return l;
    }

    public void clearFindHistory() {
        this.putStringSet("findhistory", (Set)null);
    }

    public String getUUID() {
        String u = this.getString("UUID", "");
        if(u.equals("")) {
            this.putString("UUID", u = UUID.randomUUID().toString().replace("-", ""));
        }

        return u;
    }

    public void clear() {
        this.a.clear().commit();
    }
}
