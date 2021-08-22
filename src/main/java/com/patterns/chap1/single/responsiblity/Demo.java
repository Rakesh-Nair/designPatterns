package com.patterns.chap1.single.responsiblity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class Demo {
    public static void main(String[] args) throws IOException {
        Journal journal = new Journal();
        journal.addEntry("Read Manga");
        journal.addEntry("Slept");
        System.out.println(journal);

        Persistence p = new Persistence();
        String fileName = "c:\\temp\\journal.txt";
        Runtime.getRuntime().exec("notepad.exe "+ fileName);
    }
}

class Journal{
    public static int count= 0;
    public List<String> entries = new ArrayList<>();

    public void addEntry(String s){
        entries.add(" "+(++count)+" : "+s);
    }

    public void removeEntry(int s){
        entries.remove(s);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(),entries);
    }

    public void save(String fileName) throws FileNotFoundException {
        try(PrintStream out = new PrintStream(fileName)){
            out.println(toString());
        }
    }
    public void load(String filename) {}
    public void load(URL url) {}
}

class Persistence{
    public void saveToFile(Journal journal, String fileName, Boolean bool) throws FileNotFoundException {
        if(bool || new File(fileName).exists()){
            try(PrintStream out = new PrintStream(fileName)){
                out.println(toString());
            }
        }
    }
    public void load(Journal journal, String filename) {}
    public void load(Journal journal, URL url) {}
}
