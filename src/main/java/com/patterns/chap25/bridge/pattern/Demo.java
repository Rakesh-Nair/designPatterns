package com.patterns.chap25.bridge.pattern;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Demo {
	public static void main(String args[]) {
//	    RasterRenderer rasterRenderer = new RasterRenderer();
//	    VectorRenderer vectorRenderer = new VectorRenderer();
//	    Circle circle = new Circle(vectorRenderer, 5);
//	    circle.draw();
//	    circle.resize(2);
//	    circle.draw();

		Injector injector = Guice.createInjector(new ShapeModule());
		Circle circle = injector.getInstance(Circle.class);
		circle.radius = 5;
		circle.draw();
		circle.resize(10.0f);
		circle.draw();
	}
}

interface Renderer {
	public void renderCircle(float r);
}

class VectorRenderer implements Renderer {

	@Override
	public void renderCircle(float r) {
		System.out.println("Drawing Circle of Radius " + r + " using VectorRenderer");

	}

}

class RastorRenderer implements Renderer {

	@Override
	public void renderCircle(float r) {
		System.out.println("Drawing Circle of Radius " + r + " using RastorRenderer");

	}

}

abstract class Shape {
	Renderer renderer;

	public Shape(Renderer renderer) {
		this.renderer = renderer;
	}

	public abstract void draw();

	public abstract void resize(float f);
}

class Circle extends Shape {

	public float radius;

	public Circle(Renderer renderer, float radius) {
		super(renderer);
		this.radius = radius;
	}

	@Inject
	public Circle(Renderer renderer) {
		super(renderer);
	}

	@Override
	public void draw() {
		renderer.renderCircle(radius);

	}

	@Override
	public void resize(float f) {
		radius *= f;
	}
}

class ShapeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Renderer.class).to(VectorRenderer.class);
	}

}