package p1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test3 {

    public static void main(String[] args) throws ParseException {
        System.out.println("编写应用程序，根据用户输入的两个日期，计算两个日期之间间隔的天数,日期输入格式为：xxxx年xx月xx日");
        int times = 1;
        while ((times--) > 0) {
            Scanner sc = new Scanner(System.in);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            System.out.println("请输入第一个日期：");
            String date1 = sc.nextLine();
            Date d1 = sdf.parse(date1);
            System.out.println("请输入第二个日期：");
            String date2 = sc.nextLine();
            Date d2 = sdf.parse(date2);
            System.out.println("两日期相差：" + calcDays(d1, d2) + "天");
            System.out.println("-----------------");
        }
    }
    //calcDays()方法用于计算两日期之间的天数
    public static int calcDays(Date d1, Date d2) {
        long s = d1.getTime() - d2.getTime();
        if (s < 0) {
            s *= -1;
        }
        int d = (int) (s / 1000 / 60 / 60 / 24 );
        return d;
    }
}
