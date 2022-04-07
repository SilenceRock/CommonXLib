package com.sr.superhelperx.http.note;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpDebugServer {
    String value() default "hghHkgQ6whyqn09IrWKTCFVbaOpj/l5tlrkWeLXzImE=";
}
