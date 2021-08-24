package com.patterns.chap5.dependency.inversion.principle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

enum Relationship{
    PARENT,
    CHILD,
    SIBLING
}

class Person{
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }
}

class Relationships implements RelationshipBrowser{
    private List<Triplet<Person, Relationship, Person>> relations =
            new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>>  getRelations(){
        return relations;
    }

    public void addParentAndChild(Person parent, Person child){
        relations.add(new Triplet(parent, Relationship.PARENT, child));
        relations.add(new Triplet(child, Relationship.CHILD, parent));
    }


    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream().filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2).collect(Collectors.toList());
    }
}

class Research
{
//    public Research(Relationships relationships)
//    {
//        // high-level: find all of john's children
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream()
//                .filter(x -> x.getValue0().name.equals("John")
//                        && x.getValue1() == Relationship.PARENT)
//                .forEach(ch -> System.out.println("Judge has a child called " + ch.getValue2().name));
//    }

    public Research(RelationshipBrowser relationshipBrowser){
        List<Person> children = relationshipBrowser.findAllChildrenOf("Judge");
        for(Person person : children)
            System.out.println("Judge has a child named "+person.name);
    }
}

interface RelationshipBrowser{
    List<Person> findAllChildrenOf(String name);
}

public class Demo {

    public static void main(String[] args) {
        Person person = new Person("Judge");
        Person child1 = new Person("Ichiji");
        Person child2 = new Person("Niji");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(person, child1);
        relationships.addParentAndChild(person, child2);

        new Research(relationships);

    }
}
