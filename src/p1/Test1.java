package p1;
import java.util.Arrays;
import java.util.Scanner;

class Circle {
    public static final double PI = Math.PI;
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    public double calCircumference() {
        return 2 * PI * radius;
    }
    public double calArea() {
        return PI * radius * radius;
    }
    public boolean compareCircle(Circle c) {
        return this.radius > c.radius;
    }
    @Override
    public String toString() {
        return "Circle [radius=" + radius + "]";
    }

}

class MyCircle extends Circle implements Comparable{
    private double area;
    public MyCircle(double radius, double area) {
        super(radius);
        this.area = area;
    }

    @Override
    public int compareTo(Object o) {
        MyCircle mc = (MyCircle) o;
        return compareCircle(mc) ? 1 : -1;
    }

    public String toString() {
        return "MyCircle [radius=" + this.getRadius() + ", area=" + this.calArea() +"]";
    }
}

public class Test1 {

    public static void main(String[] args) {
        MyCircle[] mc = new MyCircle[10];
        Scanner sc = new Scanner(System.in);
        double radius = 0;
        double area = 0;
        for (int i = 0,len = mc.length; i < len; ++i) {
            System.out.printf("\n请输入第[%d]个圆的信息：\n", i + 1);
            System.out.print("半径：");
            radius = sc.nextDouble();
            mc[i] = new MyCircle(radius, area);
        }

        Arrays.sort(mc);
        System.out.println("MyCircle对象从小到大的顺序为：");
        for (MyCircle myCircle : mc) {
            System.out.println(myCircle);
        }
    }
}
