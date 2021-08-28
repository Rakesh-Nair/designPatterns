package com.patterns.chap16.basicsingleton.pattern;

public class Demo {
    public static void main(String[] args) {
        BasicSingleton instance = BasicSingleton.getInstance();
        instance.setValue(75);
        System.out.println("Instance "+ instance);

        BasicSingleton instance2 = BasicSingleton.getInstance();
        instance.setValue(175);
        System.out.println("After creating Instance 2 ");
        System.out.println("Instance "+ instance);
        System.out.println("Instance 2  "+ instance);
    }
}

class BasicSingleton{
    private BasicSingleton(){
        
    }
    private static final BasicSingleton basic = new BasicSingleton();
    private int value = 0;
    public static BasicSingleton getInstance(){
        return basic;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BasicSingleton{" +
                "value=" + value +
                '}';
    }
}
