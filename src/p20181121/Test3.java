package p20181121;

import java.text.SimpleDateFormat;
import java.util.Date;

/*编写java应用程序，在主线程中有2个线程：PrintTime和Supervisor。线程PrintTime负责输出当前的时间，共输出10个

 * ，每输出一个就准备休息5s。Supervisor线程一直在吵醒PrintTime线程。当PrintTime工作完成后，Supervisor线程也结束运行。*/

public class Test3 {

    public static void main(String[] args) {

        PrintTime th1 = new PrintTime();
        th1.start();
        Supervisor th2 = new Supervisor(th1);
        th2.start();

    }
}

class PrintTime extends Thread {

    int num = 0;
    boolean flag = true;

    @Override
    public void run() {

        System.out.println("start");
        while (num < 10) {
            ///
            //数据处理
            ///
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            num++;

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                //  e.printStackTrace();
            }

        }
        //System.out.println("stop");
        //super.run();
        flag = false;
    }
}


class Supervisor extends Thread {

    PrintTime p;
    Supervisor(PrintTime p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (p.flag) {

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            p.interrupt();
            //super.run();
        }
    }
}
