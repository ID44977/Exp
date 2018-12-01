//transfer.java
package p20181128;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class transfer extends JFrame{
    private JTextField accountNum;
    private JTextField targetAccountNum;
    private JTextField money;
    private JButton submit;
    private Socket socket;

    public transfer() {
        mainPanel();
        initPanel();
        event();
    }

    private void mainPanel() {
        this.setLayout(new GridLayout(4,1));
        JPanel Panel1 = new JPanel();
        JPanel Panel2 = new JPanel();
        JPanel Panel3 = new JPanel();
        JPanel Panel4 = new JPanel();

        JLabel accountNumLabel = new JLabel("My account:");
        JLabel targetAccountNumLabel = new JLabel("Target account:");
        JLabel moneyLabel = new JLabel("Transfer amount:");
        accountNum = new JTextField(20);
        targetAccountNum = new JTextField(20);
        money = new JTextField(20);
        submit = new JButton("submit");

        Panel1.add(accountNumLabel);
        Panel1.add(accountNum);
        Panel2.add(targetAccountNumLabel);
        Panel2.add(targetAccountNum);
        Panel3.add(moneyLabel);
        Panel3.add(money);
        Panel4.add(submit);

        this.add(Panel1);
        this.add(Panel2);
        this.add(Panel3);
        this.add(Panel4);

    }

    private void initPanel() {
        this.setTitle("Transfer");
        this.setLocation(1200,50);
        this.setSize(300,250);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void event() {
        submit.addActionListener(e -> {
            String accountNumStr = accountNum.getText();
            String targetAccountNumStr = targetAccountNum.getText();
            String moneyStr = money.getText();
            accountNum.setText("");
            targetAccountNum.setText("");
            money.setText("");
            int port = 8000;
            String host = "localhost";
            try {
                socket = new Socket(host, port);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("transfer-" + accountNumStr);
                ps.println(targetAccountNumStr);
                ps.println(moneyStr);
                JOptionPane.showMessageDialog(null,br.readLine(),"转账结果",JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e1) {}
        });
    }
}


