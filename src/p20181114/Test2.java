package p20181114;

import javax.swing.*;
import java.awt.*;

public class Test2 extends JFrame {
    static Test2 keyevent1 = new Test2("随机素数判断");

    Test2(String title) {
        super(title);
    }

    private void addComponentToPanel(JudgePrime pj) {
        this.setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new FlowLayout());


        pj.textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(pj.textArea);
        p1.add(scrollPane);

        this.add(p1, BorderLayout.CENTER);
        this.setLocation(250, 250);

    }

    public static void main(String args[]) {

        boolean isRunning = true;
        JudgePrime jp = new JudgePrime();
        Thread1 th1 = new Thread1(jp);
        Thread2 th2 = new Thread2(jp);

        keyevent1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        keyevent1.addComponentToPanel(jp);
        keyevent1.pack();
        keyevent1.setVisible(true);

        th1.start();
        th2.start();

        while (isRunning) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println(e);
            }

            if (jp.getNum() > 90) {

                if (jp.isPrime(jp.getNum())) {jp.textArea.setText(jp.textArea.getText() + "Thread2 says: " + jp.getNum() + " is a prime" + '\n'); }

                else {jp.textArea.setText(jp.textArea.getText() + "Thread2 says: " + jp.getNum() + " is not a prime" + '\n'); }

                jp.textArea.setText(jp.textArea.getText() + "Main thread says:" + jp.getNum() + " is bigger than 90" + '\n');
                jp.textArea.setText(jp.textArea.getText() + "Over." + '\n');

                isRunning = false;
            }
        }
    }
}

class JudgePrime {
    boolean flag;//false为判断，true为生成
    int num;
    JTextArea textArea = new JTextArea(30, 50);

    public int getNum() {
        return num;
    }

    public boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        } else if (num == 2) {
            return true;
        } else if (num % 2 == 0) {
            return false;
        }
        int sqrtNum = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrtNum; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public synchronized void create() {
        num = (int) (Math.random() * 100);
        textArea.setText(textArea.getText() + "Thread1 says: I create a number:" + num + '\n');
    }

    public synchronized void judge() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.notifyAll();

        if (num <= 90) {
            if (isPrime(num)) {
                textArea.setText(textArea.getText() + "Thread2 says: " + num + " is a prime" + '\n');
            } else {
                textArea.setText(textArea.getText() + "Thread2 says: " + num + " is not a prime" + '\n');
            }
        }
    }
}

class Thread1 extends Thread {
    private JudgePrime jp;

    Thread1(JudgePrime jp) {
        this.jp = jp;
    }

    public void run() {
        while (true) {
            while (jp.flag) {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            jp.create();
            jp.flag = true;
        }
    }
}

class Thread2 extends Thread {
    private JudgePrime jp;

    Thread2(JudgePrime jp) {
        this.jp = jp;
    }

    public void run() {
        boolean flag = true;
        while (flag) {
            while (!jp.flag) {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            jp.judge();

            if (jp.num > 90) {
                flag = false;
                jp.flag = true;

            } else {
                jp.flag = false;
            }
        }
    }

}
