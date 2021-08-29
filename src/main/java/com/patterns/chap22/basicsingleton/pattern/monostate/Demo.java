package com.patterns.chap22.basicsingleton.pattern.monostate;

import javax.swing.text.AsyncBoxView;

public class Demo {
    public static void main(String[] args) {
        ChiefExecutiveOfficer ceo1 = new ChiefExecutiveOfficer();
        ceo1.setName("Roronoa Zoro");
        ceo1.setAge(21);
        System.out.println("CEO 1 "+ceo1);
        ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
        System.out.println("CEO 2 "+ceo2);
    }
}

class ChiefExecutiveOfficer{
    private static String name;
    private static int age;

    public ChiefExecutiveOfficer() {
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ChiefExecutiveOfficer.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        ChiefExecutiveOfficer.age = age;
    }

    @Override
    public String toString() {
        return "ChiefExecutiveOfficer\n "+ "Name : "+ ChiefExecutiveOfficer.name+
                "\nAge : "+ChiefExecutiveOfficer.age;
    }
}
