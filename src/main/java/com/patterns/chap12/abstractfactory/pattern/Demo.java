package com.patterns.chap12.abstractfactory.pattern;
//import javafx.util.Pair;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class Demo{

}

interface HotDrink {
    void consume();
}

interface HotDrinkFactory{
    HotDrink prepare(int n);
}

class Tea implements  HotDrink{

    @Override
    public void consume() {
        System.out.println("This Tea is Delicious");
    }
}

class Coffee implements  HotDrink{

    @Override
    public void consume() {
        System.out.println("This Coffee is Delicious");
    }
}

class TeaFactory implements HotDrinkFactory{

    @Override
    public HotDrink prepare(int n) {
        System.out.println("Add "+n+" ml of Water, Add Tea Powder and Boil");
        return new Tea();
    }
}

class CoffeeFactory implements  HotDrinkFactory{

    @Override
    public HotDrink prepare(int n) {
        System.out.println("Add "+n+" ml of Water, Add Coffee Powder and Boil");
        return new Coffee();
    }
}

class HotDrinkMachine{
//    private List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();
//
//    new Reflections("com.patterns.chap12.abstractfactory.pattern").getSubTypesOf(HotDrinkFactory.class);
}