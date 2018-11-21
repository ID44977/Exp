package p20181024;

import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一行带有数字字符的字符串:");
        String line = sc.nextLine();
        String regex = "\\D+";
        String[] str = line.split(regex);
        System.out.println("输出字符串中的全部数字字符串：");
        for (String string : str) {
            System.out.print(string + " ");
        }
    }
}
