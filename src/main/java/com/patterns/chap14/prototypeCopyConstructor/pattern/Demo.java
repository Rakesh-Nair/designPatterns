package com.patterns.chap14.prototypeCopyConstructor.pattern;

public class Demo {
    public static void main(String[] args) {
        Employee sanji = new Employee("Sanji", new Address("Thousand Sunny",
                "Raftel", "North Blue"));
        Employee yonji = new Employee(sanji);

        yonji.name="Yonji";
        yonji.address.streetAddress="Germa";
        yonji.address.city="Snail";

        System.out.println("Sanji "+ sanji);
        System.out.println("Yonji "+yonji);
    }
}

class Address{
    public String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address(Address a){
        this(a.streetAddress, a.city,a.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee{
    public String name;
    public Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee e){
        this.name = e.name;
        this.address = new Address(e.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
