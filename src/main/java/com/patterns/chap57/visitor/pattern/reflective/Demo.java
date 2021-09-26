package com.patterns.chap57.visitor.pattern.reflective;

public class Demo {
	public static void main(String args[]) {
		AdditionExpression e = new AdditionExpression(new DoubleExpression(2),
				new AdditionExpression(new DoubleExpression(3), new DoubleExpression(4)));
		StringBuilder sb = new StringBuilder();
		ExpressionPrinter.print(e,sb);
		System.out.println(sb);
	}
}

abstract class Expression {

}

class DoubleExpression extends Expression {

	public double value;

	public DoubleExpression(double value) {
		super();
		this.value = value;
	}
}

class AdditionExpression extends Expression {

	public Expression left, right;

	public AdditionExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}
}

class ExpressionPrinter {
	public static void print(Expression e, StringBuilder sb) {
		if (e.getClass() == DoubleExpression.class) {
			sb.append(((DoubleExpression) e).value);
		} else if (e.getClass() == AdditionExpression.class) {
			AdditionExpression ae = (AdditionExpression) e;
			sb.append("(");
			print(ae.left, sb);
			sb.append("+");
			print(ae.right, sb);
			sb.append(")");
		}
	}
}