package com.vendler.resetservice.annotation;

import com.vendler.resetservice.RestContextMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vendler.resetservice.annotation.RestResourceMethod.*;

/**
 * Created by mattias on 2016-11-27.
 */
public class RestContextProcessor {


    public static Map<String, RestContextMapping> process(List<Class> resourceClasses) {
        Map<String, RestContextMapping> stringRestContextMappingMap = new HashMap<String, RestContextMapping>();
        for (Class c : resourceClasses) {
            if (c.isAnnotationPresent(RestContext.class)) {
                RestContext a = (RestContext) c.getAnnotation(RestContext.class);
                RestContextMapping restContextMapping = new RestContextMapping(c);

                String path = a.path();
                Method[] methods = c.getMethods();
                for (Method m : methods) {
                    if (m.getAnnotation(RestResourceMethod.class) != null) {
                        RestResourceMethod restResourceMethod = m.getAnnotation(RestResourceMethod.class);
                        restContextMapping.addRequestMethod(restResourceMethod.method(), m);
                    }
                }
                stringRestContextMappingMap.put(path, restContextMapping);
            }
        }
        return stringRestContextMappingMap;
    }
}
