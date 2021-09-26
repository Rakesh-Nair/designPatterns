package com.patterns.chap53.strategy.pattern;

import java.util.List;

public class Demo {
	public static void main(String args[]) {
		TextProcessor tb = new TextProcessor(OutputFormat.MARKDOWN);
		tb.appendList(List.of("Ichiji", "Niji", "Sanji", "Yonji"));
		System.out.println("Markdown \n" + tb + "\n");
		tb.clear();

		tb = new TextProcessor(OutputFormat.HTML);
		tb.appendList(List.of("Ichiji", "Niji", "Sanji", "Yonji"));
		System.out.println("HTML \n" + tb);
	}
}

enum OutputFormat {
	MARKDOWN, HTML
}

interface ListStrategy {
	default void start(StringBuilder sb) {
	}

	default void stop(StringBuilder sb) {
	}

	void addListItem(StringBuilder sb, String item);
}

class MarkdownListStrategy implements ListStrategy {

	@Override
	public void addListItem(StringBuilder sb, String item) {
		sb.append(" * ").append(item).append(System.lineSeparator());

	}

}

class HtmlListStrategy implements ListStrategy {

	@Override
	public void start(StringBuilder sb) {
		sb.append(" <ul>").append(System.lineSeparator());
	}

	@Override
	public void stop(StringBuilder sb) {
		sb.append(" </ul>").append(System.lineSeparator());
	}

	@Override
	public void addListItem(StringBuilder sb, String item) {

		sb.append(" <li>").append(item).append(" </li>").append(System.lineSeparator());
	}

}

class TextProcessor {
	private ListStrategy listStrategy;
	private StringBuilder sb = new StringBuilder();

	public TextProcessor(OutputFormat format) {
		switch (format) {
		case HTML:
			listStrategy = new HtmlListStrategy();
			break;
		case MARKDOWN:
			listStrategy = new MarkdownListStrategy();
			break;
		default:
			break;
		}
	}

	public void appendList(List<String> items) {
		listStrategy.start(sb);
		for (String item : items) {
			listStrategy.addListItem(sb, item);
		}
		listStrategy.stop(sb);
	}

	public void clear() {
		sb.setLength(0);
	}

	@Override
	public String toString() {
		return "TextProcessor [listStrategy=" + listStrategy + ", sb=" + sb + "]";
	}

}