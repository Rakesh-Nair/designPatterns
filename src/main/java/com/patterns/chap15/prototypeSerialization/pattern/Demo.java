package com.patterns.chap15.prototypeSerialization.pattern;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class Demo {
    public static void main(String[] args) {
        Address address1 = new Address(202,"Galaxy Express","123 Street");
        Address address2 = SerializationUtils.roundtrip(address1);
        address2.flatNumber = 203;
        System.out.println("Address 1 "+ address1);
        System.out.println("Address 2 "+ address2);
    }
}

class Address implements Serializable {
    int flatNumber;
    String buildingName;
    String streetName;

    public Address(int flatNumber, String buildingName, String streetName) {
        this.flatNumber = flatNumber;
        this.buildingName = buildingName;
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "flatNumber=" + flatNumber +
                ", buildingName='" + buildingName + '\'' +
                ", streetName='" + streetName + '\'' +
                '}';
    }
}