package com.cloud.erp.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author YANGKAIQI1
 */
public final class BeanUtils extends org.springframework.beans.BeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    private BeanUtils() {}

    /**
     * 实例化对象
     * 
     * @param clazz
     *            类
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        return (T)instantiateClass(clazz);
    }

    /**
     * 实例化对象
     * 
     * @param clazzStr
     *            类名
     * @return 对象
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取Bean的属性
     * 
     * @param bean
     *            bean
     * @param propertyName
     *            属性名
     * @return 属性值
     */
    public static Object getProperty(Object bean, String propertyName) {
        Objects.requireNonNull(bean);
        Objects.requireNonNull(propertyName);
        PropertyDescriptor pd = null;
        try {
            pd = getPropertyDescriptor(bean.getClass(), propertyName);
        } catch (BeansException e) {
            logger.error(e.getMessage(), e);
        }
        if (pd == null) {
            throw new RuntimeException(bean.getClass().getName() + "Could not read property '" + propertyName
                + "' from bean PropertyDescriptor is null");
        }
        Method readMethod = pd.getReadMethod();
        if (readMethod == null) {
            throw new RuntimeException("Could not read property '" + propertyName + "' from bean readMethod is null");
        }
        if (!readMethod.isAccessible()) {
            readMethod.setAccessible(true);
        }
        try {
            return readMethod.invoke(bean);
        } catch (Throwable ex) {
            throw new RuntimeException("Could not read property '" + propertyName + "' from bean", ex);
        }
    }

    /**
     * 设置Bean属性
     * 
     * @param bean
     *            bean
     * @param propertyName
     *            属性名
     * @param value
     *            属性值
     */
    public static void setProperty(Object bean, String propertyName, Object value) {
        PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
        if (pd == null) {
            throw new RuntimeException(
                "Could not set property '" + propertyName + "' to bean PropertyDescriptor is null");
        }
        Method writeMethod = pd.getWriteMethod();
        if (writeMethod == null) {
            throw new RuntimeException("Could not set property '" + propertyName + "' to bean writeMethod is null");
        }
        if (!writeMethod.isAccessible()) {
            writeMethod.setAccessible(true);
        }
        try {
            writeMethod.invoke(bean, value);
        } catch (Throwable ex) {
            throw new RuntimeException("Could not set property '" + propertyName + "' to bean", ex);
        }
    }

    /**
     * copy 对象属性到另一个对象，默认不使用Convert
     * 
     * @param src
     * @param clazz
     *            类名
     * @return T
     */
    public static <T> T copy(Object src, Class<T> clazz) {
        BeanCopier copier = BeanCopier.create(src.getClass(), clazz, false);

        T to = newInstance(clazz);
        copier.copy(src, to, null);
        return to;
    }

    /**
     * 将一个对象列表，转换成另一个对象的列表
     * 
     * @param srcList
     * @param clazz
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copy(List<S> srcList, Class<T> clazz) {
        return Optional.ofNullable(srcList).isPresent()
            ? srcList.stream().map(srcObj -> copy(srcObj, clazz)).collect(Collectors.toList()) : new ArrayList<T>();
    }

    /**
     * 拷贝对象
     * 
     * @param src
     *            源对象
     * @param dist
     *            需要赋值的对象
     */
    public static void copy(Object src, Object dist) {
        BeanCopier copier = BeanCopier.create(src.getClass(), dist.getClass(), false);

        copier.copy(src, dist, null);
    }

}
