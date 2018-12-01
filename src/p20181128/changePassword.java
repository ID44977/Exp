//changePassword.java
package p20181128;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class changePassword extends JFrame {
    private JTextField accountNum;
    private JPasswordField newPassword;
    private JButton submit;
    private Socket socket;

    public changePassword() {
        mainPanel();
        initPanel();
        event();
    }

    private void mainPanel() {
        this.setLayout(new GridLayout(3,1));
        JPanel changePasswordPanel1 = new JPanel();
        JPanel changePasswordPanel2 = new JPanel();
        JPanel changePasswordPanel3 = new JPanel();

        JLabel accountNumLabel = new JLabel("My account:");
        JLabel passwordLabel = new JLabel("New password:");
        accountNum = new JTextField(20);
        newPassword = new JPasswordField(20);
        submit = new JButton("submit");

        changePasswordPanel1.add(accountNumLabel);
        changePasswordPanel1.add(accountNum);
        changePasswordPanel2.add(passwordLabel);
        changePasswordPanel2.add(newPassword);
        changePasswordPanel3.add(submit);

        this.add(changePasswordPanel1);
        this.add(changePasswordPanel2);
        this.add(changePasswordPanel3);
    }

    private void initPanel() {
        this.setTitle("Change Password");
        this.setLocation(900,250);
        this.setSize(300,250);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void event() {
        submit.addActionListener(e -> {
            String accountNumStr = accountNum.getText();
            String newPasswordStr = new String(newPassword.getPassword());
            accountNum.setText("");
            newPassword.setText("");
            int port = 8000;
            String host = "localhost";
            try {
                socket = new Socket(host, port);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("changePassword-" + accountNumStr);
                ps.println(newPasswordStr);
                JOptionPane.showOptionDialog(null,br.readLine(),"修改密码",JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE,null,new String[] {"检查账户"},"检查账户");
                JOptionPane.showMessageDialog(null,br.readLine(),"修改密码",JOptionPane.PLAIN_MESSAGE);
                //result.append(br.readLine());

            } catch (IOException e1) {}
        });
    }
}
