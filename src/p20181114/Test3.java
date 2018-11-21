package p20181114;

import javax.swing.*;
import java.awt.*;

public class Test3 extends JFrame{
    static Test3 keyevent1 = new Test3("打印");
    Test3(String title){
        super(title);
    }

    private void addComponentToPane(Printer aprinter){
        this.setLayout(new BorderLayout());
        JPanel p1=new JPanel(new FlowLayout());
        aprinter.textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(aprinter.textArea);
        p1.add(scrollPane);
        this.add(p1,BorderLayout.CENTER);
        this.setLocation(260,260);
    }

    public static void main(String[] args) {
        boolean isRunning = true;
        //创建一个打印机
        Printer aprinter=new Printer();
        //同步该打印机输出
        NumPrinter np=new NumPrinter(aprinter);
        AlpPrinter ap=new AlpPrinter(aprinter);
        keyevent1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        keyevent1.addComponentToPane(aprinter);
        keyevent1.pack();
        keyevent1.setVisible(true);
        np.start();
        ap.start();
    }

}
class Printer
{
    JTextArea textArea = new JTextArea(30, 50);
    //False打印数字，True打印字母
    private boolean flag=false;
    //打印数字
    int i;
    public int getI(){
        return i;
    }
    public synchronized void print1()
    {
        for (i=0;i<52;)
        {
            if (flag==false)
            {
                textArea.setText(textArea.getText() + "Thread1: ");
                i++;
                textArea.setText(textArea.getText() + i);
                //System.out.print((i+1));
                i++;
                textArea.setText(textArea.getText() + i);
                //System.out.print((i)+2);
                textArea.setText(textArea.getText() + "\n");
                //重新将flag置为true，等待输出字母
                flag=true;
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){}
                //激活其它线程
                this.notifyAll();
            }
            else
            {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }
    //输出字母打印机，同数字打印机方法
    public synchronized void print2()
    {
        for(char c='A';c<='Z';)
        {
            if (flag==true)
            {
                textArea.setText(textArea.getText() + "Thread2: ");
                textArea.setText(textArea.getText() + c);
                //System.out.print(c);
                textArea.setText(textArea.getText() + "\n");
                flag=false;
                c++;
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){}
                this.notifyAll();
            }
            else
            {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }
}
//创建数字打印机线程
class NumPrinter extends Thread {
    private Printer aprinter;

    public NumPrinter(Printer aprinter)
    {
        this.aprinter=aprinter;
    }

    public void run() {
        // TODO Auto-generated method stub
        aprinter.print1();
    }
}
//创建字母打印机线程
class AlpPrinter extends Thread {

    private Printer aprinter;

    public AlpPrinter(Printer aprinter)
    {
        this.aprinter=aprinter;
    }

    public void run() {
        // TODO Auto-generated method stub
        aprinter.print2();
    }
}

