package p20181128;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ChatFrame extends JFrame{

    private JButton send;//发送消息按钮
    private JButton clear;//清空消息按钮
    private JTextArea sendText;//发送框
    private JTextArea viewText;//接收框
    @SuppressWarnings("unused")
    private Socket socket;
    private BufferedReader br;
    private PrintStream ps;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public ChatFrame(String name, Socket socket) {
        this.setTitle(name);
        this.socket = socket;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
        } catch (Exception e) {}

        //放置两个按钮：发送消息(左) & 清空消息(右)
        southPanel();

        //放置两个文本区域：接收框(上) & 发送框(下)
        centerPanel();

        //JFrame的一些初始化工作,eg.setSize \ setVisible ..
        init();

        //一些监听事件,eg.鼠标点击按钮,关闭窗口等
        event();

        //消息显示
        write();
    }

    private void write() {
        boolean isExit = true;
        while (isExit) {
            String message;
            try {
                message = br.readLine();
                viewText.append(getCurrentTime() + " 对方\r\n" + message + "\r\n\r\n");
            } catch (IOException e) {
                //当一方关闭时，不再获取输入，结束循环
                isExit = false;
            }
        }
    }

    private void send() {
        //获取发送框的内容
        String message = sendText.getText();
        //发送框清空
        sendText.setText("");

        message = message.replaceAll("\r|\n", "");
        //将内容追加到接收框
        viewText.append(getCurrentTime() + " 我\r\n" + message + "\r\n\r\n");

        //将消息发送到另一方
        ps.println(message);

    }

    private String getCurrentTime() {
        return sdf.format(new Date());
    }

    //事件监听
    private void event() {
        send.addActionListener(e -> send());
        clear.addActionListener(e -> viewText.setText(""));
        sendText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send();
                }
            }
        });

    }

    //中间的Panel 放接收框和发送框
    private void centerPanel() {
        JPanel center = new JPanel();
        viewText = new JTextArea();
        sendText = new JTextArea(9, 1);

        //接收框禁止输入
        viewText.setEditable(false);

        //设置发送框和输出框的滚动条
        JScrollPane viewTextJsp = new JScrollPane(viewText);
        JScrollPane sendTextJsp = new JScrollPane(sendText);

        //设置字体样式
        viewText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        sendText.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        //设置发送框的边框
        viewText.setBorder(new LineBorder(new Color(127,157,185), 1, false));
        center.setLayout(new BorderLayout());

        center.add(viewTextJsp, BorderLayout.CENTER);//接收框在上
        center.add(sendTextJsp, BorderLayout.SOUTH);//发送框在下
        this.add(center, BorderLayout.CENTER);
    }

    //下方的Panel 放按钮
    private void southPanel() {
        JPanel south = new JPanel();
        send = new JButton("发送消息(Enter)");
        clear = new JButton("清空消息");
        south.add(send);
        south.add(clear);
        this.add(south, BorderLayout.SOUTH);
    }

    //JFrame初始化
    private void init() {
        this.setLocation(500, 50);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
