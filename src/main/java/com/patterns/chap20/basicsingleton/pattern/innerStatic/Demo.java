package com.patterns.chap20.basicsingleton.pattern.innerStatic;
//This provides Synchronization

public class Demo {
    public static void main(String[] args) {
        InnerStaticSingleton instance = InnerStaticSingleton.getInstance();
        System.out.println(instance);
    }
}

class InnerStaticSingleton{
    private InnerStaticSingleton(){
        System.out.println("Initializing Singleton object");
    }

    private static class Impl {
        private static final InnerStaticSingleton Instance = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance(){
        return Impl.Instance;
    }

    @Override
    public String toString() {
        return "InnerStaticSingleton{}";
    }
}
