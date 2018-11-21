package p20181024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //HashMap键存储姓名，值存储票数
        HashMap<String, Integer> hm = new HashMap<>();
        //正则表达式:非字母字符串
        String regex = "[^a-zA-Z\\u4e00-\\u9fa5]+";
        for (int i = 1; i <= 10; ++i) {
            System.out.print("第" + i + "行：");
            String line = sc.nextLine();

            String[] str = line.split(regex);
            for (String string : str) {
                //如果姓名不在hm的键集合中，那么新增一个元素
                if (!hm.containsKey(string)) {
                    hm.put(string, 1);
                } else {
                    ////如果姓名在hm的键集合中存在，那么为它的票数增1
                    int value = hm.get(string) + 1;
                    hm.put(string, value);
                }
            }
        }
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(hm.entrySet());
        //利用jdk8的新特性：Lamda表达式。结果与实现Comparator接口并重写compare()方法一致
        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());
        for (Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + "↘\n       票数：" + entry.getValue());
        }
    }

}
