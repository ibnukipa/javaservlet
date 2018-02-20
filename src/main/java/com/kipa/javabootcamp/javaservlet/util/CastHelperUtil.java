package com.kipa.javabootcamp.javaservlet.util;

import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
public class CastHelperUtil {
    public static <T> List<T> listAndCast(Query q) {
        return q.getResultList();
    }
}
