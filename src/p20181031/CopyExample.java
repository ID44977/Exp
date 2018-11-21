package p20181031;

import java.io.*;
public class CopyExample{
    public static void main(String[] args) throws IOException{
        File fs = new File("e:\\Wallpaper-2560x1600-ZHS.jpg");    //源文件，必须存在
        File fd = new File("d:\\demo.jpg");   //目标文件，可以不存在
        FileInputStream fis = new FileInputStream(fs);      //建立连接文件fs的输入流
        FileOutputStream fos = new FileOutputStream(fd);   //建立连接文件fd的输出流
        byte[] b = new byte[1024];                       //创建大小为1024的字节数组
        while(fis.read(b)!=-1){
            fos.write(b);    //将字节数组b的内容写入目标文件
        }
        fos.close();     //fos关闭；
        fis.close();    //fis关闭；
    }
}

