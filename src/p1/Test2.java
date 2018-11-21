package p1;

import java.util.Random;
import java.util.Scanner;

interface PrintColor {
    public abstract String howToPrint();
}

class MCircle implements PrintColor {
    @Override
    public String howToPrint() {
        Random r = new Random();
        String[] colors = {
                "",
                "Red",
                "Orange",
                "Yellow",
                "Green",
                "Cyan",
                "Blue",
                "Purple",
                "White",
                "Black",
                "Pink"
        };
        int i = r.nextInt(10) + 1;
        return colors[i];
    }
}

public class Test2 {
    public static void main(String[] args) {
        System.out.println("请输入MCircle的个数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        MCircle[] mc = new MCircle[num];
        System.out.println("随机生成的MCircle颜色如下：");
        for (MCircle mCircle : mc) {
            mCircle = new MCircle();
            System.out.println(mCircle.howToPrint());
        }
    }
}
