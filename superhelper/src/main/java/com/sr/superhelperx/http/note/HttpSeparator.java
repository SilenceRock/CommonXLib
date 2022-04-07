package com.sr.superhelperx.http.note;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Hang.Yang on 2018/8/17 14:54.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpSeparator {
    boolean value() default true;

    String url_separator() default "?";

    String value_separator() default "=";

    String element_separator() default "&";
}
