package com.example.design.mode.demo.singleton;

/**
 * 饿汉式：类加载时就实例化
 * 应用场景：热点数据的加载
 * @author yig
 */
public class HungrySingleton {
    /**
     * 创建一个实例对象
     */
    private static HungrySingleton instance = new HungrySingleton();

    /**
     * 私有构造方法，防止被实例化
     */
    private HungrySingleton() {
    }

    /**
     * 静态get方法
     */
    public static HungrySingleton getInstance() {
        return instance;
    }
}
