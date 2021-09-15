package com.patterns.chap43.interpreter.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Execution is failing... Unable to rectify
public class Demo {

	private static final String input = "(122+52)-(32+190)";

	static List<Token> lex(String input) {
		List<Token> result = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
			case '+':
				result.add(new Token(Token.Type.PLUS, "+"));
				break;
			case '-':
				result.add(new Token(Token.Type.MINUS, "-"));
				break;
			case '(':
				result.add(new Token(Token.Type.LPAREN, "("));
				break;
			case ')':
				result.add(new Token(Token.Type.RPAREN, ")"));
				break;
			default: {
				StringBuilder sb = new StringBuilder("" + input.charAt(i));
				for (int j = i + 1; j < input.length(); j++) {
					if (Character.isDigit(input.charAt(j))) {
						sb.append(input.charAt(j));
						i++;
					} else {
						result.add(new Token(Token.Type.INTEGER, sb.toString()));
						sb = new StringBuilder("");
						break;
					}
				}
			}
				break;
			}
		}
		return result;
	}

	static Element parse(List<Token> tokens) {
		BinaryOperation result = new BinaryOperation();
		boolean haveLHS = false;
		System.out.println("Inside Parse " + tokens);
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.get(i);
			System.out.println("Reached here");
			switch (token.type) {
			case PLUS:
				result.type = BinaryOperation.Type.ADDITION;
				break;
			case MINUS:
				result.type = BinaryOperation.Type.SUBTRACTION;
				break;
			case LPAREN:
				int j = 0;// Location of Right Paren
				for (; j < tokens.size(); ++j)
					if (tokens.get(j).type == Token.Type.RPAREN)
						break;
				List<Token> subExpression = tokens.stream().skip(i + 1).limit(j - i - 1).collect(Collectors.toList());
				System.out.println("Sub Expression " + subExpression);
				Element element = parse(subExpression);
				if (!haveLHS) {
					result.left = element;
					haveLHS = true;
				} else {
					result.right = element;
				}
				i = j;
				break;
			case INTEGER:
				Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
				if (!haveLHS) {
					result.left = integer;
					haveLHS = true;
				} else {
					result.right = integer;
				}
				break;
			default:

				break;
			}
		}
		return result;
	}

	public static void main(String args[]) {
		List<Token> lex = lex(input);
		// System.out.println(lex.stream().map(t ->
		// t.toString()).collect(Collectors.joining("\t")));
		System.out.println("Input " + lex);
		Element parsed = parse(lex);
		System.out.println(input + "=" + parsed.eval());
	}
}

class Token {
	public enum Type {
		INTEGER, PLUS, MINUS, LPAREN, RPAREN
	}

	public Type type;
	public String text;

	public Token(Type type, String text) {
		super();
		this.type = type;
		this.text = text;
	}

	@Override
	public String toString() {
		return "Token [type=" + type + ", text=" + text + "]";
	}

}

interface Element {
	int eval();
}

class Integer implements Element {

	private int value;

	public Integer(int value) {
		super();
		this.value = value;
	}

	@Override
	public int eval() {
		return value;
	}

}

class BinaryOperation implements Element {

	public enum Type {
		ADDITION, SUBTRACTION
	}

	public Type type;
	public Element left, right;

	@Override
	public int eval() {
		switch (type) {
		case ADDITION:
			return left.eval() + right.eval();
		case SUBTRACTION:
			return left.eval() - right.eval();
		default:
			return 0;
		}
	}

}
