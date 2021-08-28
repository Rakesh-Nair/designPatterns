package com.patterns.chap17.basicsingleton.pattern.Serialization;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BasicSingleton instance = BasicSingleton.getInstance();
        instance.setValue(75);
        System.out.println("Instance "+ instance);
        String fileName = "Singleton.txt";
        saveToFile(instance, fileName);
        BasicSingleton instance2 = readFromFile(fileName);
        instance2.setValue(123);
        System.out.println(instance == instance2);
        System.out.println("Instance "+instance);
        System.out.println("Instance 2 "+ instance2);
         }

    static void saveToFile(BasicSingleton singleton, String fileName) throws IOException {
        try(FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);){
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String fileName) throws IOException, ClassNotFoundException {
        try(FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);){
            return (BasicSingleton) in.readObject();
        }
    }
}

class BasicSingleton implements Serializable {
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

    protected Object readResolve(){
        return basic;
    }
}
