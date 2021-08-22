package com.patterns.chap3.liskov.substitution.principal;

class Rectangle{
    int length, width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea(){
        return width*length;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", height=" + width +
                '}';
    }
}

 class RectangleFactory {
    public static Rectangle newRectange(int length, int width){
        return new Rectangle(length, width);
    }

    public static Rectangle newSquare(int side){
        return new Rectangle(side, side);
    }
}
public class Demo {
    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);
    }

    static void useIt(Rectangle r){
        int width = r.getWidth();
        r.setWidth(10);
        System.out.println("Expected area of " + (width*10) + ", got " + r.getArea());

    }
}
