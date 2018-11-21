package p20181031;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2 {
    public static void main(String[] args) {
        String path = "C:\\Program Files\\Java\\jre-10.0.2\\lib";
        File file = new File(path);
        File[] list = file.listFiles();

        for (File file2 : list) {
            String name = file2.getName();
            if (name.endsWith(".jar")) {
                long time = file2.lastModified();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String strtime = sdf.format(new Date(file2.lastModified()));
                System.out.println("文件名：" + name);
                System.out.println("大小：" + file2.length() + "byte");
                System.out.println("最近修改时间：" + strtime);
                System.out.println("-----------");
            }
        }
    }
}
