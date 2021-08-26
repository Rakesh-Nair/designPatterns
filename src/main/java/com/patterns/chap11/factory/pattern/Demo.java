package com.patterns.chap11.factory.pattern;

public class Demo {
    public static void main(String[] args) {
        Point cp = Point.Factory.newCartesianPoint(5, 6);
        System.out.println("Cartesian Points : \n"+cp);

        cp = Point.Factory.newPolarPoint(5, 6);
        System.out.println("Polar Points : \n"+cp);
    }
}

class Point{
    private double x,y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory{
        public static Point newCartesianPoint(double x, double y){
            return new Point(x,y);
        }

        public static Point newPolarPoint(double rho, double thetha){
            return new Point(rho*Math.sin(thetha), rho*Math.cos(thetha));
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
