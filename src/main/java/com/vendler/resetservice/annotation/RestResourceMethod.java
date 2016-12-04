package com.vendler.resetservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mattias on 2016-11-29.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestResourceMethod {
    enum RequestMethod {
        GET,
        PUT,
        POST,
        DELETE;
    }
 RequestMethod method() default RequestMethod.GET;

}
