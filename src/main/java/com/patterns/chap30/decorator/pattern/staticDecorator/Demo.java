package com.patterns.chap30.decorator.pattern.staticDecorator;

import java.util.function.Supplier;

public class Demo {
	public static void main(String args[]) {
		Circle circle = new Circle(5);
		System.out.println(circle.info());

		ColouredShape <Square> blueSquare = new ColouredShape(() -> new Square(20), "Blue");
		System.out.println(blueSquare.info());

		TransparentShape <ColouredShape<Circle>> tcircle = new TransparentShape(
					() -> new ColouredShape(
							() -> new Circle(5), "Blue"), 50);
		System.out.println(tcircle.info());
		
		ColouredShape<TransparentShape<Square>> csquare = new ColouredShape(
				() -> new TransparentShape(
						() -> new Square(10), 50),"Red");
		System.out.println(csquare.info());
	}
}

interface Shape {
	String info();
}

class Circle implements Shape {

	float radius;

	public Circle() {
	}

	public Circle(float radius) {
		super();
		this.radius = radius;
	}

	@Override
	public String info() {

		return "A Circle of Radius " + radius;
	}

	public void resize(int factor) {
		radius *= factor;
	}

}

class Square implements Shape {
	private float side;

	public Square() {

	}

	public Square(float side) {
		super();
		this.side = side;
	}

	@Override
	public String info() {
		return "A Square of Side " + side;
	}

}

class ColouredShape <T extends Shape> implements Shape{
	private Shape shape;
	private String colour;
	public ColouredShape(Supplier <? extends T>ctor, String colour) {
		this.colour = colour;
		this.shape = ctor.get();
	}
	
	@Override
	public String info() {
		return shape.info() + " and has the color " + colour;
	}
	
}

class TransparentShape <T extends Shape> implements Shape{

	private Shape shape;
	private float transparency;
	
	TransparentShape(Supplier <? extends T> ctor, float transparency){
		this.transparency = transparency;
		this.shape = ctor.get();
	}
	
	@Override
	public String info() {
		return shape.info() + " and has transparency "+ transparency;
	}
	
}


