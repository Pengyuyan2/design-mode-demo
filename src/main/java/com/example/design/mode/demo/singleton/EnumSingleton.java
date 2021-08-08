package com.example.design.mode.demo.singleton;

/**
 * 枚举单例模式：使用枚举来实现单实例控制会更加简洁，而且JVM从根本上提供保障，绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 * @author yig
 */
public enum EnumSingleton {
    /**
     * 定义一个枚举元素，代表Singleton的一个实例
     */
    Instance;
}
