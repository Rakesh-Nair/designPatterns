package com.patterns.chap2.open.closed.principle;

import java.util.List;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        Product Apple = new Product("Apple", Color.RED, Size.SMALL);
        Product Orange = new Product("Orange", Color.ORANGE, Size.SMALL);
        Product Home = new Product("Home", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(Apple, Orange, Home);
        ProductFilter pf = new ProductFilter();
        pf.filterByColor(products, Color.RED).forEach(p -> System.out.println("Red Products with Old Filter "+ p.name));

        BetterFilter bf = new BetterFilter();
        bf.filter(products, new ColorSpecification(Color.RED)).forEach(p -> System.out.println("Red Products with " +
                "New Filter "+ p.name));

        bf.filter(products, new SizeSpecification(Size.LARGE)).forEach(p -> System.out.println("Large Product with" +
                " new Filter "+p.name));

        bf.filter(products, new AndSpecification<Product>(new ColorSpecification(Color.RED),
                new SizeSpecification(Size.SMALL) )).forEach(p -> System.out.println("RED and SMALL Product "+ p.name));
    }
}

enum Color{
    RED, BLUE, ORANGE
}

enum Size{
    SMALL, MEDIUM, LARGE, HUGE
}

class Product{
    public String name;
    public Color color;
    public Size size;

    Product(String name, Color color, Size size){
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter{
    public Stream<Product> filterByColor(List<Product> products, Color color){
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color){
        return products.stream().filter(p -> p.size == size && p.color == color);
    }

}

interface Specification<T>{
    boolean isSatisfied(T item);
}

interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements  Specification<Product>{

    Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements  Specification<Product>{

    Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class AndSpecification<T> implements  Specification<T>{
    Specification first, second;

    public AndSpecification(Specification first, Specification second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item) ;
    }
}

class BetterFilter implements  Filter<Product>{

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}