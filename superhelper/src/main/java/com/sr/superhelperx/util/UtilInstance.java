package com.sr.superhelperx.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Hang.Yang on 2018/8/17 16:22.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilInstance {
    private UtilInstance() {
    }

    public static <T> T Instance(Class<T> c) throws Exception {
        return c.newInstance();
    }

    public static <T> T Instance(Class<T> c, Class[] constructors, Object[] objects) {
        try {
            Constructor e = c.getDeclaredConstructor(constructors);
            e.setAccessible(true);
            return (T) e.newInstance(objects);
        } catch (NoSuchMethodException var4) {
            var4.printStackTrace();
        } catch (InstantiationException var5) {
            var5.printStackTrace();
        } catch (IllegalAccessException var6) {
            var6.printStackTrace();
        } catch (IllegalArgumentException var7) {
            var7.printStackTrace();
        } catch (InvocationTargetException var8) {
            var8.printStackTrace();
        }

        return null;
    }

    public static <T> T InstanceGenericity(Class<?> c, int i) {
        try {
            return (T) Instance(GenericityClass(c, i));
        } catch (Exception var3) {
            return null;
        }
    }

    public static Class<?> GenericityClass(Class<?> c, int i) {
        return (Class)((ParameterizedType)c.getGenericSuperclass()).getActualTypeArguments()[i];
    }
}

