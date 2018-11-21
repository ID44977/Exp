package p1;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeMap;

public class Test5 {
    public static void main(String[] args) {
        //测试程序
        demo1();
        demo2();
    }
    private static void demo1() {
        System.out.println("按半径从大到小输出风扇信息如下：");
        TreeMap<Fan, String> tm = new TreeMap<>();
        Random r = new Random();
        for (int i = 0; i < 10; ++i) {
            //随机产生风扇半径和价格，控制在3-6
            int radius = r.nextInt(4) + 3;
            int price = r.nextInt(4) + 3;
            tm.put(new Fan(radius, price),"风扇");
        }

        //遍历输出风扇信息
        for (Fan fan : tm.keySet()) {
            System.out.println(fan);
        }
    }
    private static void demo2() {
        System.out.println("————————————————————————————————");
        System.out.println("按价格从小到大输出风扇信息如下：");
        TreeMap<Fan, String> tm = new TreeMap<>(new Comparator<Fan>() {
            //按价格从小到大
            @Override
            public int compare(Fan f1, Fan f2) {
                int num = (int)(f1.getPrice() - f2.getPrice());
                return num ==0 ? 1: num;
            }
            //按半径从小到大
			/*@Override
			public int compare(Fan f1, Fan f2) {
				int num = (int)(f1.getRadius() - f2.getRadius());
				return num ==0 ? 1: num;
			}*/
        });

        Random r = new Random();
        for (int i = 0; i < 10; ++i) {
            int radius = r.nextInt(4) + 3;
            int price = r.nextInt(4) + 3;
            tm.put(new Fan(radius, price),"风扇");
        }

        for (Fan fan : tm.keySet()) {
            System.out.println(fan);
        }
    }


}

//实现Comparable接口方式，并重写compareTo()方法
class Fan implements Comparable{
    private int radius;
    private float price;
    public Fan(int radius, float price) {
        super();
        this.radius = radius;
        this.price = price;
    }

    public int getRadius() {
        return radius;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Fan [radius=" + radius + ", price=" + price + "]";
    }

    //按价格从小到大
	/*
	@Override
	public int compareTo(Object o) {
		Fan f = (Fan) o;
		int num = (int)(price - f.getPrice());
		return num ==0 ? 1: num;
	}
	*/

    //按半径从大到小输出
    @Override
    public int compareTo(Object o) {
        Fan f = (Fan) o;
        int num = (int)(f.getRadius() - radius);
        return num ==0 ? 1: num;
    }
}
