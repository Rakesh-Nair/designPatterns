package com.patterns.chap9.faceted.builder.pattern;

public class Demo {
    public static void main(String[] args) {
        PersonBuilder pb = new PersonBuilder();
        Person person = pb.lives()
                .at("Raftel")
                .in("OnePiece")
                .withPostCode("421306")
                .works()
                .worksAt("Thousand Sunny")
                .withPosition("Pirate King")
                .withSalary(14111998)
                .build();
        System.out.println(person);
    }
}

class Person{
    //address
    public String streetAddress, postCode, city;
    //employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

//builder facade

class PersonBuilder{
    protected Person person = new Person();

    public Person build(){
        return person;
    }

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works(){
        return new PersonJobBuilder(person);
    }
}

class PersonAddressBuilder extends PersonBuilder{
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String address){
        person.streetAddress = address;
        return this;
    }

    public PersonAddressBuilder in(String city){
        person.city = city;
        return this;
    }
    public PersonAddressBuilder withPostCode(String post){
        person.postCode = post;
        return this;
    }

}

class PersonJobBuilder extends  PersonBuilder{
    public PersonJobBuilder(Person person) {
        this.person = person;
    }

    public PersonJobBuilder worksAt(String works){
        person.companyName = works;
        return this;
    }

    public PersonJobBuilder withPosition(String works){
        person.position = works;
        return this;
    }

    public PersonJobBuilder withSalary(int salary){
        person.annualIncome = salary;
        return this;
    }
}
