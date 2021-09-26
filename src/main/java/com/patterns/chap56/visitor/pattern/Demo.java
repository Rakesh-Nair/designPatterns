package com.patterns.chap56.visitor.pattern;

public class Demo {
	public static void main(String args[]) {
		AdditionExpression e  = new AdditionExpression(new DoubleExpression(2),
				new AdditionExpression(new DoubleExpression(3), new DoubleExpression(4)));
		StringBuilder sb = new StringBuilder();
	    e.print(sb);
	    System.out.println(sb);
	}
}

abstract class Expression {
	public abstract void print(StringBuilder sb);
}

class DoubleExpression extends Expression {

	private double value;

	public DoubleExpression(double value) {
		super();
		this.value = value;
	}

	@Override
	public void print(StringBuilder sb) {

		sb.append(value);
	}

}

class AdditionExpression extends Expression {

	private Expression left, right;

	public AdditionExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public void print(StringBuilder sb) {
		sb.append("(");
		left.print(sb);
		sb.append("+");
		right.print(sb);
		sb.append(")");

	}

}