package com.example.design.mode.demo.singleton;

/**
 * 懒汉式单例模式，线程安全，且通过volatile关键字修饰变量，防止指令重排序，保证内存可见，
 * 因为instance = new Singleton()不是原子操作
 * 通过volatile修饰的变量，不会被线程本地缓存，所有线程对该对象的读写都会第一时间同步到主内存，
 * 从而保证多个线程间该对象的准确性
 *
 * @author yig
 */
public class SyncAndVolatileLazySingleton {
    private volatile static SyncAndVolatileLazySingleton instance = null;

    private SyncAndVolatileLazySingleton() {
    }

    public static SyncAndVolatileLazySingleton getInstance() {
        //检查实例是否存在，如果不存在才进入同步块
        if (instance == null){
            //同步块，线程安全创建实例
            synchronized (SyncAndVolatileLazySingleton.class){
                //再次检查实例是否存在，如果不存在才创建实例
                if (instance == null){
                    instance = new SyncAndVolatileLazySingleton();
                }
            }
        }
        return instance;
    }
}
