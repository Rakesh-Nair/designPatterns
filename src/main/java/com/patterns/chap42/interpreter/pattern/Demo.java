package com.patterns.chap42.interpreter.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {

	static List<Token> lex(String input) {
		List<Token> result = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
			case '+':
				result.add(new Token(Token.Type.PLUS,"+") );
				break;
			case '-':
				result.add(new Token(Token.Type.MINUS,"-") );
				break;
			case '(':
				result.add(new Token(Token.Type.LPAREN,"(") );
				break;
			case ')':
				result.add(new Token(Token.Type.RPAREN,")") );
				break;
				default:
				{
					StringBuilder sb = new StringBuilder(""+input.charAt(i));
					for(int j=i+1; j<input.length(); j++) {
						if(Character.isDigit(input.charAt(j))) {
							sb.append(input.charAt(j));
							i++;
						}
						else {
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

	public static void main(String args[]) {
		List<Token> lex = lex("(2+52)+(32+190)");
		System.out.println(lex.stream().map(t -> t.toString()).collect(Collectors.joining("\t")));
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
