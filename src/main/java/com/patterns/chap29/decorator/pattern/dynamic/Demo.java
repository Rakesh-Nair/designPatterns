package com.patterns.chap29.decorator.pattern.dynamic;

public class Demo {
	public static void main(String args[]) {
		Circle circle = new Circle(5);
		System.out.println(circle.info());
		
		ColouredShape ccircle = new ColouredShape(circle, "Red");
		System.out.println(ccircle.info());
		
		TransparentShape tcircle = new TransparentShape(ccircle, 50);
		System.out.println(tcircle.info());
	}
}

interface Shape{
	String info();
}

class Circle implements Shape{
	
	float radius;
	
	public Circle() {}
	public Circle(float radius) {
		super();
		this.radius = radius;
	}

	@Override
	public String info() {
		
		return "A Circle of Radius "+radius;
	}
	
	public void resize(int factor) {
		radius *= factor;
	}
	
	
}

class Square implements Shape{
	private float side;
	public Square() {
		
	}
	public Square(float side) {
		super();
		this.side = side;
	}
	@Override
	public String info() {
		return "A Square of Side "+side;
	}
	
}

class ColouredShape implements Shape{
	
	private Shape shape;
	private String colour;

	
	public ColouredShape(Shape shape, String colour) {
		super();
		this.shape = shape;
		this.colour = colour;
	}


	@Override
	public String info() {
		// TODO Auto-generated method stub
		return shape.info() + " and has colour "+ colour;
	}
}

class TransparentShape implements Shape {

	private Shape shape;
	private float transparency;
	
	
	public TransparentShape(Shape shape, float transparency) {
		super();
		this.shape = shape;
		this.transparency = transparency;
	}
	

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return shape.info()+ " and has transparency "+ transparency;
	}
	
}
