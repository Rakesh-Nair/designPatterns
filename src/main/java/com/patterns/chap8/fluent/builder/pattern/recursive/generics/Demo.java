package com.patterns.chap8.fluent.builder.pattern.recursive.generics;

public class Demo {
    public static void main(String[] args) {
        EmployeeBuilder pb = new EmployeeBuilder();
        Person luffy = pb.withName("luffy").worksAt("Thousand Sunny").build();
        System.out.println(luffy);

        PersonBuilder eb = new PersonBuilder();
        Person zoro = eb.withName("Zoro").build();
        System.out.println(zoro);
    }
}


class Person{
    String name, position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>>{
    protected Person  person  = new Person();

    public SELF withName(String s){
        person.name = s;
        return self();
    }

    protected SELF self() {
        System.out.println("Calling self at Personbuilder");
        return (SELF) this;
    }

    public Person build(){
        return person;
    }
}

class EmployeeBuilder extends  PersonBuilder<EmployeeBuilder>{
    public EmployeeBuilder worksAt(String s){
        person.position = s;
        System.out.println("Calling self in EmployeeBuilder");
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}