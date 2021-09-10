package com.patterns.chap25.bridge.pattern.test;

import com.google.inject.AbstractModule;


class Test {
	public static void main(String a[]) {

	}
}

abstract class Shape {
	Renderer renderer;
	Shape(Renderer r){
		this.renderer = r;
	}
	abstract String getName();
}

class Triangle extends Shape {
	Triangle(Renderer r) {
		super(r);
	}

	@Override
	public String getName() {
		return "Triangle";
	}
}

class Square extends Shape {
	Square(Renderer r) {
		super(r);
	}

	@Override
	public String getName() {
		return "Square";
	}
}

class VectorSquare extends Square {
	VectorSquare(Renderer r) {
		super(r);
	}

	@Override
	public String toString() {
		return String.format("Drawing %s as lines", getName());
	}
	
	class ShapeModule extends AbstractModule {


	}
}

class RasterSquare extends Square {
	RasterSquare(Renderer r) {
		super(r);
	}

	@Override
	public String toString() {
		return String.format("Drawing %s as pixels", getName());
	}
}

interface Renderer {
	public String whatToRenderAs();
}

// imagine VectorTriangle and RasterTriangle are here too
class VectorTriangle extends Triangle {
	VectorTriangle(Renderer r) {
		super(r);
	}

	@Override
	public String toString() {
		return String.format("Drawing %s as lines", getName());
	}
}

class RasterTriangle extends Triangle {
	RasterTriangle(Renderer r) {
		super(r);
	}

	@Override
	public String toString() {
		return String.format("Drawing %s as pixels", getName());
	}
}