package com.example.design.mode.demo.singleton;

/**
 * 懒汉式：第一次调用时才会实例化，非线程安全
 * 应用场景：很少调用的场景
 * @author yig
 */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
