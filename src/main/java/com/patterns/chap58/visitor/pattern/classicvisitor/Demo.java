package com.patterns.chap58.visitor.pattern.classicvisitor;

public class Demo {
	public static void main(String args[]) {
		AdditionExpression e = new AdditionExpression(new DoubleExpression(2),
				new AdditionExpression(new DoubleExpression(3), new DoubleExpression(4)));
		StringBuilder sb = new StringBuilder();
		ExpressionPrinter ep = new ExpressionPrinter();
		ep.visit(e);
		System.out.println(e);
		
		ExpressionCalculator calc = new ExpressionCalculator();
		calc.visit(e);
		System.out.println(ep+" = "+ calc.result);
	}
}

abstract class Expression {
	public abstract void accept(ExpressionVisitor e);
}

interface ExpressionVisitor {
	void visit(DoubleExpression e);

	void visit(AdditionExpression e);
}

class DoubleExpression extends Expression {

	public double value;

	public DoubleExpression(double value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(ExpressionVisitor e) {
		e.visit(this);
	}

	@Override
	public String toString() {
		return "DoubleExpression [value=" + value + "]";
	}
	
	
}

class AdditionExpression extends Expression {

	public Expression left, right;

	public AdditionExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(ExpressionVisitor e) {
		e.visit(this);
	}

	@Override
	public String toString() {
		return "AdditionExpression [left=" + left + ", right=" + right + "]";
	}
	
	
}

class ExpressionPrinter implements ExpressionVisitor {

	private StringBuilder sb = new StringBuilder();

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

	@Override
	public void visit(DoubleExpression e) {
		sb.append(e.value);
	}

	@Override
	public void visit(AdditionExpression e) {
		sb.append("(");
		e.left.accept(this);
		sb.append("+");
		e.right.accept(this);
		sb.append(")");
	}

	@Override
	public String toString() {
		return "ExpressionPrinter [sb=" + sb + "]";
	}
	
	
}

class ExpressionCalculator implements ExpressionVisitor {

	public double result;

	@Override
	public void visit(DoubleExpression e) {
		result = e.value;
	}

	@Override
	public void visit(AdditionExpression e) {
		e.left.accept(this);
		double a = result;

		e.right.accept(this);
		double b = result;

		result = a + b;
	}

}