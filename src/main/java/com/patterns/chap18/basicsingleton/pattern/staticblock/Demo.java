package com.patterns.chap18.basicsingleton.pattern.staticblock;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        StaticBlockSingleton instance = StaticBlockSingleton.getInstance();
        System.out.println("Instance is "+ instance);
    }
}

class StaticBlockSingleton{
    private StaticBlockSingleton() throws IOException {
        System.out.println("Initialized Singleton");
        File.createTempFile(".",".");//Invalid code
    }

    private static StaticBlockSingleton instance;

    static {
        try{
            instance = new StaticBlockSingleton();
        }
        catch(Exception e){
            System.out.println("Exception Thrown !!");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }

    @Override
    public String toString() {
        return "StaticBlockSingleton{}";
    }
}
