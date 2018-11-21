package p20181031;

        import java.io.*;

public class CopyExampleTxt {
    public static void main(String[] args) throws IOException {
        File fs = new File("e:\\Hello.txt");    //源文件，必须存在
        File fd = new File("d:\\demo.txt");   //目标文件，可以不存在
        FileReader fis = new FileReader(fs);      //建立连接文件fs的输入流
        FileWriter fos = new FileWriter(fd);   //建立连接文件fd的输出流
        char[] b = new char[1024];                       //创建大小为1024的字节数组
        while(fis.read(b)!=-1){
            fos.write(b);    //将字节数组b的内容写入目标文件
        }
        fos.close();     //fos关闭；
        fis.close();    //fis关闭；
    }
}
