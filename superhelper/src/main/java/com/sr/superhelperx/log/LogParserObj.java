package com.sr.superhelperx.log;

import android.util.Pair;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hang.Yang on 2018/8/17 16:04.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class LogParserObj {
    private static final String[] TYPES = new String[]{"int", "java.lang.String", "boolean", "char", "flotage", "double", "long", "short", "byte"};

    private LogParserObj() {
    }

    public static String parseObj(Object o) {
        if(o == null) {
            return "null";
        } else {
            String s = o.getClass().getSimpleName();
            if(o.getClass().isArray()) {
                StringBuilder var10 = new StringBuilder("Temporarily not support more than two dimensional Array!");
                int var12 = LogParserArray.getArrayDimension(o);
                switch(var12) {
                    case 1:
                        Pair var14 = LogParserArray.arrayToString(o);
                        var10 = new StringBuilder(s.replace("[]", "[" + var14.first + "] {\n"));
                        var10.append(var14.second).append("\n");
                        break;
                    case 2:
                        Pair var16 = LogParserArray.arrayToObject(o);
                        Pair var18 = (Pair)var16.first;
                        var10 = new StringBuilder(s.replace("[][]", "[" + var18.first + "][" + var18.second + "] {\n"));
                        var10.append(var16.second).append("\n");
                }

                return var10 + "}";
            } else if(o instanceof Collection) {
                Collection var9 = (Collection)o;
                String var11 = "%s size = %d [\n";
                var11 = String.format(Locale.ENGLISH, var11, new Object[]{s, Integer.valueOf(var9.size())});
                if(!var9.isEmpty()) {
                    Iterator var13 = var9.iterator();

                    String var17;
                    Object var19;
                    for(int var15 = 0; var13.hasNext(); var11 = var11 + String.format(Locale.ENGLISH, var17, new Object[]{Integer.valueOf(var15), objectToString(var19), var15++ < var9.size() - 1?",\n":"\n"})) {
                        var17 = "[%d]:%s%s";
                        var19 = var13.next();
                    }
                }

                return var11 + "\n]";
            } else if(!(o instanceof Map)) {
                return objectToString(o);
            } else {
                String m = s + " {\n";
                Map a = (Map)o;
                Set ks = a.keySet();

                Object k;
                String r;
                Object v;
                for(Iterator f = ks.iterator(); f.hasNext(); m = m + String.format(r, new Object[]{objectToString(k), objectToString(v)})) {
                    k = f.next();
                    r = "[%s -> %s]\n";
                    v = a.get(k);
                }

                return m + "}";
            }
        }
    }

    protected static <T> String objectToString(T o) {
        if(o == null) {
            return "Object{object is null}";
        } else if(!o.toString().startsWith(o.getClass().getName() + "@")) {
            return o.toString();
        } else {
            StringBuilder b = new StringBuilder(o.getClass().getSimpleName() + "{");
            Field[] fs = o.getClass().getDeclaredFields();
            Field[] var3 = fs;
            int var4 = fs.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Field f = var3[var5];
                f.setAccessible(true);
                boolean l = false;
                String[] var8 = TYPES;
                int var9 = var8.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    String t = var8[var10];
                    if(f.getType().getName().equalsIgnoreCase(t)) {
                        l = true;
                        Object v = null;

                        try {
                            v = f.get(o);
                        } catch (IllegalAccessException var17) {
                            v = var17;
                        } finally {
                            b.append(String.format("%s=%s, ", new Object[]{f.getName(), v == null?"null":v.toString()}));
                        }
                    }
                }

                if(!l) {
                    b.append(String.format("%s=%s, ", new Object[]{f.getName(), "Object"}));
                }
            }

            return b.replace(b.length() - 2, b.length() - 1, "}").toString();
        }
    }
}
