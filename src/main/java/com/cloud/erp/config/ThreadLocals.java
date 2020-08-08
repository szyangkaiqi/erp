package com.cloud.erp.config;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.google.common.collect.Maps;

/**
 * @author YANGKAIQI1
 */
public class ThreadLocals {
    private static final ThreadLocal<Map<Object, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static void init(Map<Object, Object> map) {
        THREAD_LOCAL.set(map);
    }

    public static void destroy() {
        THREAD_LOCAL.remove();
    }

    public static Object put(Object key, Object value) {
        Map<Object, Object> m = getMap();
        return m.put(key, value);
    }

    public static final <T> T put(T value) {
        return (T)put(value.getClass(), value);
    }

    public static Object get(Object key) {
        Map<Object, Object> m = getMap();
        return m.get(key);
    }

    public static <T> T get(Class<T> key) {
        Map<Object, Object> m = getMap();
        return (T)m.get(key);
    }

    public static Object get(Object key, Function<Object, Object> valueFunction) {
        Map<Object, Object> m = getMap();
        return m.computeIfAbsent(key, valueFunction);
    }

    private static Map<Object, Object> getMap() {
        Map<Object, Object> m = THREAD_LOCAL.get();
        return Optional.ofNullable(m).orElse(Maps.newHashMap());
    }

}
