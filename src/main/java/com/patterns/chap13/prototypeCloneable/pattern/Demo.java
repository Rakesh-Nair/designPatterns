package com.patterns.chap13.prototypeCloneable.pattern;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person ichiji = new Person(new String[]{"Ichiji", "Vinsmoke"}, new Address("Germa", 666));
        System.out.println("Ichiji "+ ichiji);

        Person niji = ichiji.clone();
        niji.names[0] = "Niji";
        System.out.println("Niji "+ niji);
    }
}

class Address implements  Cloneable{
    String streetName;
    int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName=" + streetName +
                ", houseNumber=" + houseNumber +
                '}';
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }
}

class Person implements Cloneable{
    public String [] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
       return new Person(names.clone(), address.clone());
    }
}
