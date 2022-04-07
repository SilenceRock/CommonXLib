package com.sr.superhelperx.secret;

/**
 * Created by Hang.Yang on 2018/8/17 14:44.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public interface Secret<T, Y> {
    T encrypt(String var1) throws Exception;

    Y decrypt(String var1) throws Exception;
}
