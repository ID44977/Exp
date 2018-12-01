//query.java
package p20181128;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class query extends JFrame {
    private JButton query;
    private JLabel accountNumLabel;
    private JTextField accountNum;
    private JTextArea viewText;
    private Socket socket;

    public query() {
        mainPanel();
        initPanel();
        event();
    }

    private void mainPanel() {
        JPanel Panel1 = new JPanel();
        JPanel Panel2 = new JPanel();
        JPanel Panel3 = new JPanel();

        this.setLayout(new GridLayout(3,1));
        accountNumLabel = new JLabel("Account:");
        accountNum = new JTextField(20);
        query = new JButton("Query");
        viewText = new JTextArea();

        Panel1.add(accountNumLabel);
        Panel1.add(accountNum);

        Panel2.add(query);
        Panel2.add(viewText);

        this.add(Panel1);
        this.add(Panel2);
        this.add(Panel3);
    }

    private void initPanel() {
        this.setTitle("Query");
        this.setLocation(900,50);
        this.setSize(300,200);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void event() {
        query.addActionListener(e -> {
            String idNum = accountNum.getText();
            String regex = "\\d{8}";
            accountNum.setText("");
            if (!idNum.matches(regex)) {
                JOptionPane.showMessageDialog(null,"账号应该为8位数字!","输入错误",JOptionPane.ERROR_MESSAGE);
                //viewText.setText("账号应该为8位数字!");
            }
            else {
                int port = 8000;
                String host = "localhost";
                try {
                    socket = new Socket(host, port);
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    ps.println("query-" + idNum);
                    JOptionPane.showMessageDialog(null,br.readLine(),"查询结果",JOptionPane.PLAIN_MESSAGE);
                    //viewText.setText(br.readLine());
                } catch (IOException e1) {}
            }
        });
    }
}
