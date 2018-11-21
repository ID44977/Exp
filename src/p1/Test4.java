package p1;

import java.util.HashSet;

public class Test4 {

    public static void main(String[] args) {
        //社团A
        HashSet<String> hsA = new HashSet<>();
        hsA.add("张三");
        hsA.add("李四");
        hsA.add("陈六");
        //社团B
        HashSet<String> hsB = new HashSet<>();
        hsB.add("王五");
        hsB.add("李四");
        hsB.add("蔡九");

        System.out.print("参加A社团的人有：");
        traverse(hsA);

        System.out.print("\n参加B社团的人有：");
        traverse(hsB);

        System.out.print("\n同时参加AB社团的人有：");
        bothAB(hsA, hsB);

        System.out.print("\n只参加A社团的人有：");
        onlyOne(hsA, hsB);

        System.out.print("\n只参加B社团的人有：");
        onlyOne(hsB, hsA);

    }
    private static void onlyOne(HashSet<String> hsA, HashSet<String> hsB) {
        for (String string : hsA) {
            if (!hsB.contains(string)) {
                System.out.print(string + " ");
            }
        }
    }
    public static void traverse(HashSet<String> hs) {
        for (String string : hs) {
            System.out.print(string + " ");
        }
    }
    public static void bothAB(HashSet<String> hsA, HashSet<String> hsB) {
        for (String string : hsB) {
            if (hsA.contains(string)) {
                System.out.print(string + " ");
            }
        }
    }
}
