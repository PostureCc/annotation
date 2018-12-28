package com.chan.annotation.user;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserAnnotation {

    String token() default "";

    String role() default "";
}
