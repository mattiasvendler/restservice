package com.vendler.resetservice;

import com.vendler.resetservice.annotation.RestResourceMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mattias on 2016-11-29.
 */
public class RestContextMapping {
    private Map<RestResourceMethod.RequestMethod, Method> requestMethodMapping;
    private Class clazz;
    public RestContextMapping(Class clazz) {
        requestMethodMapping = new HashMap<RestResourceMethod.RequestMethod, Method>();
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void addRequestMethod(RestResourceMethod.RequestMethod requestMethod, Method m) {
        requestMethodMapping.put(requestMethod, m);
    }

    public Method getMethod(String httpMethod) {
        try {
            RestResourceMethod.RequestMethod requestMethod = RestResourceMethod.RequestMethod.valueOf(httpMethod);
            return requestMethodMapping.get(requestMethod);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
