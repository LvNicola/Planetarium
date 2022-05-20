package net.planetarium;

public class Point implements Comparable<Point>{
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Point p){
        return Math.abs(Math.sqrt(Math.pow(x-p.x, 2) + Math.pow(y-p.y, 2)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point p) {
        if (x != p.x)
            return Double.compare(x, p.x);
        return Double.compare(y, p.y);
    }
}
