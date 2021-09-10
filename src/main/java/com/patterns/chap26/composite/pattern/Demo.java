package com.patterns.chap26.composite.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {
	public static void main(String args[]) {
		GraphicObject drawing = new GraphicObject();
	    drawing.setName("My Drawing");
	    drawing.children.add(new Square("Red"));
	    drawing.children.add(new Circle("Yellow"));

	    GraphicObject group = new GraphicObject();
	    group.children.add(new Circle("Blue"));
	    group.children.add(new Square("Blue"));
	    drawing.children.add(group);

	    System.out.println(drawing);
	}

}

class GraphicObject{
	protected String name = "group";
	public String color;
	public List<GraphicObject> children = new ArrayList<>();

	public GraphicObject() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb  = new StringBuilder();
		print(sb, 0);
		return sb.toString();
	}

	private void print(StringBuilder sb, int i) {
		sb.append(String.join("", Collections.nCopies(i, "*")))
		.append(i > 0?" " : "")
		.append((color == null || color.isEmpty()) ? "" : color + " ")
		.append(getName())
		.append(System.lineSeparator());
		
		for(GraphicObject child : children) {
			child.print(sb,  i+1);
		}
		
	}	
}

class Circle extends GraphicObject
{
  public Circle(String color)
  {
    name = "Circle";
    this.color = color;
  }
}

class Square extends GraphicObject
{
  public Square(String color)
  {
    name = "Square";
    this.color = color;
  }
}


