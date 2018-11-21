package p20181121;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.*;
import javax.swing.*;

public class Test2 extends JFrame{
    static Test2 keyevent1 = new Test2("取款");
    Test2(String title){
        super(title);
    }
    private void addComponentToPanel(Account account){
        this.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        account.textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(account.textArea);
        p1.add(scrollPane);
        this.add(p1,BorderLayout.CENTER);
        this.setLocation(240,240);
    }
    public static void main(String[] args) {
        Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);
        keyevent1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        keyevent1.addComponentToPanel(account);
        keyevent1.pack();
        keyevent1.setVisible(true);
        //("之前余额  取款额  当前的余额\n");
        for (int i = 1; i <= 100; i++) {
            service.execute(new Withdraw(account, 1));
        }
        service.shutdown();
        while (!service.isTerminated()) {
        }
        account.showBalance();
        //System.out.println("账户余额: " + account.getBalance());
    }
}