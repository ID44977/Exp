//accountClient.java
package p20181128;

import javax.swing.*;
import java.awt.BorderLayout;

public class accountClient extends JFrame {
    private JButton query;//查询
    private JButton changePassword;//修改密码
    private JButton deposit;//存款
    private JButton withdraw;//取款
    private JButton transfer;//同行转账

    public accountClient() {
        mainPanel();
        initPanel();
        event();
    }

    private void mainPanel() {
        JPanel main = new JPanel();
        query = new JButton("query");
        changePassword = new JButton("change password");
        deposit = new JButton("deposit");
        withdraw = new JButton("withdraw");
        transfer = new JButton("transfer");
        main.add(query);
        main.add(changePassword);
        main.add(deposit);
        main.add(withdraw);
        main.add(transfer);
        this.add(main,BorderLayout.CENTER);
    }
    private void initPanel() {
        this.setTitle("Client");
        this.setLocation(600,50);
        this.setSize(300,200);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void event() {
        query.addActionListener(e -> new query());
        changePassword.addActionListener(e -> new changePassword());
        deposit.addActionListener(e -> new deposit());
        withdraw.addActionListener(e -> new withdraw());
        transfer.addActionListener(e -> new transfer());
    }
}
