//deposit.java
package p20181128;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class deposit extends JFrame{
    private JTextField accountNum;
    private JTextField money;
    private JButton submit;
    private JTextArea result;
    private Socket socket;

    public deposit() {
        mainPanel();
        initPanel();
        event();
    }

    public void mainPanel() {
        this.setLayout(new GridLayout(4,1));
        JPanel depositPanel1 = new JPanel();
        JPanel depositPanel2 = new JPanel();
        JPanel depositPanel3 = new JPanel();
        //JPanel withdrawPanel4 = new JPanel();

        JLabel accountNumLabel = new JLabel("My account:");
        JLabel moneyLabel = new JLabel("Deposit amount:");
        accountNum = new JTextField(20);
        money = new JTextField(20);
        submit = new JButton("submit");
        //result = new JTextArea();

        depositPanel1.add(accountNumLabel);
        depositPanel1.add(accountNum);
        depositPanel2.add(moneyLabel);
        depositPanel2.add(money);
        depositPanel3.add(submit);
        //withdrawPanel4.add(result);

        this.add(depositPanel1);
        this.add(depositPanel2);
        this.add(depositPanel3);
        //this.add(withdrawPanel4);
    }

    public void initPanel() {
        this.setTitle("Deposit");
        this.setLocation(1200,300);
        this.setSize(300,250);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void event() {
        submit.addActionListener(e -> {
            String accountNumStr = accountNum.getText();
            String moneyStr = money.getText();
            accountNum.setText("");
            money.setText("");
            int port = 8000;
            String host = "localhost";
            try {
                socket = new Socket(host, port);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("deposit-" + accountNumStr);
                ps.println(moneyStr);
                JOptionPane.showOptionDialog(null,br.readLine(),"存款结果",JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null,new String[] {"检查账户"},"检查账户");
                JOptionPane.showMessageDialog(null,br.readLine(),"存款结果",JOptionPane.PLAIN_MESSAGE);
                //result.append(br.readLine());

            } catch (IOException e1) {}
        });
    }
}

