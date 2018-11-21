package p20181031;

import java.io.*;
import java.util.Scanner;

public class Test3 {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static void main(String[] args) throws IOException {
        int i = 0;
        Scanner sc = new Scanner(System.in);
        FileWriter fw = new FileWriter("d:\\Test3.txt");
        while (i < 10) {
            String str = sc.nextLine();
            fw.write(str + LINE_SEPARATOR);
            i++;
        }
        fw.close();
        sc.close();
        FileReader fis = new FileReader("d:\\Test3.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream("d:\\Test3.txt"));
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        while((lineTxt = bufferedReader.readLine()) != null){
            System.out.println(lineTxt);
        }
        bufferedReader.close();
        fis.close();
    }
}
