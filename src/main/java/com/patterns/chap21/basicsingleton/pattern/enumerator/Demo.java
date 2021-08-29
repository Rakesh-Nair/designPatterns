package com.patterns.chap21.basicsingleton.pattern.enumerator;

import java.io.*;

enum EnumBasedSingleton{
    INSTANCE;
    EnumBasedSingleton(){
        value = 34;
    }
    int value;

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }
}
public class Demo {
    public static void main(String[] args)  throws Exception{
        String fileName = "Singleton.txt";
        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
        singleton.setValue(111);
        System.out.println("Singleton "+singleton.getValue());
        saveToFile(singleton,fileName);
        EnumBasedSingleton singleton2 = readFromFile(fileName);
        System.out.println("Singleton2 "+singleton2.getValue());

    }

    static void saveToFile(EnumBasedSingleton singleton, String fileName) throws IOException {
        try(FileOutputStream fo = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fo);){
            os.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String fileName) throws IOException, ClassNotFoundException {
        try(FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream oi = new ObjectInputStream(fi);){
            return (EnumBasedSingleton) oi.readObject();
        }
    }
}
