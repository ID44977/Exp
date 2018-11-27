package p20181128;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.net.Socket;
import java.text.*;
import java.util.*;

public class Framework extends JFrame{
    private JButton send;
    private JButton reset;
    private JTextArea receiveArea;  //接收框
    private JTextArea inputArea;    //输入框
    private Socket socket;
    private BufferedReader br;
    private PrintStream ps;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Framework(String name, Socket socket) {
        this.setTitle(name);
        this.socket = socket;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
        } catch (Exception e) {}

        mainPanel();
        buttonPanel();
        initPanel();
        event();
        showText();
    }

    private String getCurrentTime() {
        return sdf.format(new Date());
    }

    private void showText() {
        boolean isExit = true;
        while (isExit) {
            String message;
            try {
                message = br.readLine();
                receiveArea.append(getCurrentTime() + "\nSever:\n" + message + "\n");
            } catch (IOException e) {
                isExit = false;
            }
        }
    }

    private void send() {
        String message = inputArea.getText();
        inputArea.setText("");
        message = message.replaceAll("\n","");
        receiveArea.append(getCurrentTime() + "\nMe:\n" + message + "\n");
        ps.println(message);
    }

    private void event() {
        send.addActionListener(e -> send());
        reset.addActionListener(e -> inputArea.setText(""));
        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send();
                }
            }
        });
    }

    private void mainPanel() {
        JPanel center = new JPanel();
        receiveArea = new JTextArea();
        inputArea = new JTextArea(6, 1);


        receiveArea.setEditable(false);

        JScrollPane receiveAreaJsp = new JScrollPane(receiveArea);
        JScrollPane inputAreaJsp = new JScrollPane(inputArea);

        receiveArea.setFont(new Font("Monaco", Font.PLAIN, 15));
        inputArea.setFont(new Font("Monaco", Font.PLAIN, 15));

       // receiveArea.setBorder(new LineBorder(new Color(127,157,185), 1, false));
        center.setLayout(new BorderLayout());

        center.add(receiveAreaJsp, BorderLayout.CENTER);
        center.add(inputAreaJsp, BorderLayout.SOUTH);
        this.add(center, BorderLayout.CENTER);
    }

    private void buttonPanel() {
        JPanel button = new JPanel();
        send = new JButton("send");
        reset = new JButton("reset");
        button.add(send);
        button.add(reset);
        this.add(button, BorderLayout.SOUTH);
    }

    private void initPanel() {
        this.setLocation(500, 50);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}