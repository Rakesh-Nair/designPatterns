package com.patterns.chap34.flyweight.pattern.formatText;

import java.util.ArrayList;
import java.util.List;

public class Demo {
	public static void main(String args[]) {
		BetterFormattedText bft = new BetterFormattedText("Make America Great Again");
	    bft.getRange(13, 18).capitalize = true;
	    System.out.println(bft);
	}
}

class BetterFormattedText {

	private String plainText;
	private List<TextRange> formatting = new ArrayList<>();
	public BetterFormattedText(String plainText) {
		super();
		this.plainText = plainText;
	}

	  public TextRange getRange(int start, int end)
	  {
	    TextRange range = new TextRange(start, end);
	    formatting.add(range);
	    return range;
	  }
	  
	  @Override
	  public String toString()
	  {
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < plainText.length(); ++i)
	    {
	      char c = plainText.charAt(i);
	      for (TextRange range : formatting)
	        if (range.covers(i) && range.capitalize)
	          c = Character.toUpperCase(c);
	      sb.append(c);
	    }
	    return sb.toString();
	  }
}

class TextRange{
	public int start, end;
    public boolean capitalize, bold, italic;

    public TextRange(int start, int end)
    {
      this.start = start;
      this.end = end;
    }
    
    public boolean covers(int position)
    {
      return position >= start && position <= end;
    }
    
}
