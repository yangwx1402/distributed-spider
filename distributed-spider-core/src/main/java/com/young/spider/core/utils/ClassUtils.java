package com.young.spider.core.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yangyong3 on 2017/7/14.
 */
public class ClassUtils {

    public static <T> T getInstance(String classname) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(classname);
        return (T) getInstance(clazz);
    }

    public static <T> T getInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static <T> T getInstance(Class<T> clazz, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }
        Constructor<T> constructor = clazz.getConstructor(classes);
        return constructor.newInstance(args);
    }

    public static <T> T getInstance(String classname, Object... args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName(classname);
        return (T) getInstance(clazz, args);
    }
}
