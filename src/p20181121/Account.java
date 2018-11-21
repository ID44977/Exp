package p20181121;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    JTextArea textArea = new JTextArea(30, 50);
    private Lock accountLock = new ReentrantLock();
    private double balance = 10000; // 账户余额
    public void withdraw(double money) {
        accountLock.lock();
        double newBalance = balance - money;
        textArea.setText(textArea.getText() + balance + "元  " + money + "元  " + newBalance + "元\n");
        try {
            Thread.sleep(10); // 模拟此业务需要一段处理时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance = newBalance;
        accountLock.unlock();
    }
    public double getBalance() {
        return balance;
    }
    public void showBalance(){
        textArea.setText(textArea.getText() + "账户余额: " + getBalance() + "元\n");
    }
}


