package com.patterns.chap23.basicsingleton.pattern.multiton;

import java.util.HashMap;

//Restrict the number of instances to a number
public class Demo {
    public static void main(String[] args) {
        Printer main = Printer.getInstance(Subsystem.PRIMARY);
        Printer aux = Printer.getInstance(Subsystem.AUXILARY);
        Printer aux2 = Printer.getInstance(Subsystem.AUXILARY);
    }
}

class Printer{
private static int instanceCount = 0;

private Printer(){
    System.out.println("Printer object is being initialized .. ");
    instanceCount++;
    System.out.println("Total instanceCount as of now is "+ instanceCount);
}

private static HashMap<Subsystem, Printer> instances = new HashMap<>();

public static Printer getInstance(Subsystem subsystem){
    if(instances.containsKey(subsystem)){
        return instances.get(subsystem);
    }
    Printer printer = new Printer();
    instances.put(subsystem,printer);
    return printer;
}

}

enum Subsystem{
    PRIMARY,
    AUXILARY,
    FALLBACK
}
