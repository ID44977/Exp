package p20181031;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Test5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ObjectInputStream ois =  null;
        ObjectOutputStream oos = null;
        try {
            File f = new File("fileOfAccount.dat");
            OutputStream out = new FileOutputStream(f);
            LinkedList<Circle> linklist = new LinkedList<Circle>();
            oos = new ObjectOutputStream(out);
            for (int i = 0; i < 10; i++) {
                System.out.println("第[" + (i + 1) + "]个圆：");
                System.out.print("半径：");
                int radius = sc.nextInt();
                Circle acc = new Circle(radius);
                linklist.add(acc);
                oos.writeObject(acc);
            }
            sc.close();
            ois = new ObjectInputStream(new FileInputStream("fileOfAccount.dat"));
            for (int i = 0; i < 10; i++) {
                Circle acc = (Circle)ois.readObject();
                System.out.println(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
