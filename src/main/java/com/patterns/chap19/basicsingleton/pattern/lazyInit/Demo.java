package com.patterns.chap19.basicsingleton.pattern.lazyInit;

public class Demo {
    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(instance);

        LazySingleton instance2 = LazySingleton.getInstance();
        System.out.println("Creating 2nd Instance");
        System.out.println(instance2);
    }
}

class LazySingleton{
    private LazySingleton(){
        System.out.println("Initializing Singleton");
    }
    private static LazySingleton instance;
    //Make it thread safe using Synchronized
//    public static synchronized LazySingleton getInstance(){
//        if(instance == null){
//            System.out.println("Instance not initialized ...calling constructor");
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    //double check locking

    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            synchronized (LazySingleton.class){
                if(instance == null){
                    System.out.println("Instance not initialized ...calling constructor");
                    instance = new LazySingleton();
                }
            }

        }
        return instance;
    }

    @Override
    public String toString() {
        return "LazySingleton{}";
    }
}