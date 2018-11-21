package p20181031;

import java.io.Serializable;

class Circle implements Serializable{
    private static final long serialVersionUID = 1L;
    private int radius;
    private double area;
    public Circle(int radius) {
        super();
        this.radius = radius;
        area = radius*radius*3.14;
    }
    public int getradius() {
        return radius;
    }
    public void setradius(int radius) {
        this.radius = radius;
        area = radius*radius*3.14;
    }
    public double getarea() {
        return area;
    }
    @Override
    public String toString() {
        return "Account [radius=" + radius + ", area=" + area + "]";
    }
}
