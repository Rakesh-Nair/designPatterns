package com.patterns.chap7.fluent.builder.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        String hello = "hello";
        System.out.println("<p>"+hello+"</p>");

        String words[] = {"Hello", "World"};
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for(String word : words){
            sb.append(String.format(" <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        System.out.println(sb.toString());



        HTMLBuilder builder = new HTMLBuilder("ul");
        builder.addChild("li", "hello");
        builder.addChild("li", "world");
        System.out.println(builder);

        // fluent builder
        builder.clear();
        builder.addChildFluent("li", "hello")
                .addChildFluent("li", "world");
        System.out.println(builder);

    }
}

class HTMLElement{
    public String name, text;
    public List<HTMLElement> elements = new ArrayList<>();
    private final String newLine = System.lineSeparator();

    public HTMLElement(String name, String text) {
        this.name = name;
        this.text = text;
    }
    public HTMLElement(){

    }
    private final int indentSize = 2;

    private String toStringImpl(int indent){
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent*indentSize," "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty())
        {
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
                    .append(text)
                    .append(newLine);
        }
        for (HTMLElement e : elements)
            sb.append(e.toStringImpl(indent + 1));

        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }
    @Override
    public String toString()
    {
        return toStringImpl(0);
    }
}

class HTMLBuilder{
    private String rootName;
    private HTMLElement root = new HTMLElement();

    public HTMLBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    public void addChild(String childName, String childText){
        HTMLElement e = new HTMLElement(childName, childText);
        root.elements.add(e);
    }

    public HTMLBuilder addChildFluent(String childName, String childText){
        HTMLElement e = new HTMLElement(childName, childText);
        root.elements.add(e);
        return this;
    }

    public void clear(){
        root = new HTMLElement();
        root.name = rootName;
    }
    @Override
    public String toString()
    {
        return root.toString();
    }
}
