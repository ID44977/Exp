package p20181031;

import java.io.*;
        import java.util.Scanner;
public class Test4 {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static void main(String[] args) throws IOException {
        int i = 0;
        Scanner sc = new Scanner(System.in);
        FileWriter fw = new FileWriter("d:\\Hello.txt");
        while (i < 10) {
            int str = sc.nextInt();
            fw.write(str+LINE_SEPARATOR);
            i++;
        }
        fw.close();
        sc.close();
        FileReader fis = new FileReader("d:\\Hello.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream("d:\\Hello.txt"));
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        String [] line2 = new String[10];
        int p = 0;
        while((lineTxt = bufferedReader.readLine()) != null){
            System.out.println(lineTxt);
            line2[p] = lineTxt;
            p++;
        }
        for(p=9;p>=0;p--){
            System.out.println(line2[p]);
        }
        bufferedReader.close();
        fis.close();
    }
}
