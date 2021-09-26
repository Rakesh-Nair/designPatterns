package com.patterns.chap54.strategy.pattern.staticeg;

import java.util.List;
import java.util.function.Supplier;

public class Demo {
	public static void main(String args[]) {
		TextProcessor<MarkdownListStrategy> tb = new TextProcessor(MarkdownListStrategy::new);
		tb.appendList(List.of("Ichiji", "Niji", "Sanji", "Yonji"));
		System.out.println("Markdown \n" + tb + "\n");

		TextProcessor<HtmlListStrategy> tb2 = new TextProcessor(HtmlListStrategy::new);
		tb2.appendList(List.of("Ichiji", "Niji", "Sanji", "Yonji"));
		System.out.println("HTML \n" + tb2);
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

class TextProcessor<LS extends ListStrategy> {
	private ListStrategy listStrategy;
	private StringBuilder sb = new StringBuilder();

	public TextProcessor(Supplier<? extends LS> ctor) {
		listStrategy = ctor.get();
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