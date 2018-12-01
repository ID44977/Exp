//withdraw.java
package p20181128;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class withdraw extends JFrame{
    private JTextField accountNum;
    private JTextField money;
    private JButton submit;
    private JTextArea result;
    private Socket socket;

    public withdraw() {
        mainPanel();
        initPanel();
        event();
    }

    public void mainPanel() {
        this.setLayout(new GridLayout(4,1));
        JPanel withdrawPanel1 = new JPanel();
        JPanel withdrawPanel2 = new JPanel();
        JPanel withdrawPanel3 = new JPanel();
        //JPanel withdrawPanel4 = new JPanel();

        JLabel accountNumLabel = new JLabel("My account:");
        JLabel moneyLabel = new JLabel("Withdraw amount:");
        accountNum = new JTextField(20);
        money = new JTextField(20);
        submit = new JButton("submit");
        //result = new JTextArea();

        withdrawPanel1.add(accountNumLabel);
        withdrawPanel1.add(accountNum);
        withdrawPanel2.add(moneyLabel);
        withdrawPanel2.add(money);
        withdrawPanel3.add(submit);
        //withdrawPanel4.add(result);

        this.add(withdrawPanel1);
        this.add(withdrawPanel2);
        this.add(withdrawPanel3);
        //this.add(withdrawPanel4);
    }

    public void initPanel() {
        this.setTitle("Withdraw");
        this.setLocation(600,250);
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
                ps.println("withdraw-" + accountNumStr);
                ps.println(moneyStr);
                JOptionPane.showOptionDialog(null,br.readLine(),"取款结果",JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null,new String[] {"检查账户"},"检查账户");
                JOptionPane.showMessageDialog(null,br.readLine(),"取款结果",JOptionPane.PLAIN_MESSAGE);
                //result.append(br.readLine());

            } catch (IOException e1) {}
        });
    }
}
