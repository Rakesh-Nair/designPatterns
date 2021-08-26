package com.patterns.chap9.faceted.builder.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person").addField("name","string")
                .addField("age","int");
        System.out.println(cb);
    }
}

class CodeBuilder

{
    private String className;
    private JavaElements root = new JavaElements();

    public CodeBuilder(String className)
    {
        this.className = className;
        root.name = className;
    }

    public CodeBuilder addField(String name, String type)
    {
        JavaElements e = new JavaElements(name, type);
        root.elements.add(e);
        return this;
    }

    @Override
    public String toString()
    {
        return root.toString();
    }

}

class JavaElements{
    String name, text;
    public List<JavaElements> elements = new ArrayList<>();
    private final String newLine = System.lineSeparator();

    public JavaElements(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public JavaElements(){

    }

    private final int indentSize = 2;

    private String toStringImpl(int indent){
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent*indentSize," "));
        sb.append(String.format("%spublic class %s%s{", i, name, newLine));
        if (text != null && !text.isEmpty())
        {
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
                    .append(text)
                    .append(newLine);
        }
        for (JavaElements e : elements)
            sb.append("\npublic "+e.text + " " + e.name+";");

        sb.append(String.format("%s%s}", i, newLine));
        return sb.toString();
    }
    @Override
    public String toString()
    {
        return toStringImpl(0);
    }
}
