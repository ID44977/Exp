package p20181107;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DealCards extends JFrame implements  ActionListener{

    private static final long serialVersionUID = 1L;
    JPanel pane1,pane2;
    JButton button;
    JLabel JL[] = new JLabel[5];
    ImageIcon img[] = new ImageIcon[5];
    int one[] = {1,2,3,4,5,6};
    int two[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    DealCards(String title){super(title);}
    static DealCards area = new DealCards("发放纸牌");

    private void addComponentToPane(){
        super.setLayout(new BorderLayout());
        pane1  = new JPanel(new GridLayout(1,5));
        pane2  = new JPanel(new FlowLayout());

        String str1 = "images/rear.gif";
        //设置图片放置位置
        ImageIcon img = new ImageIcon(str1);
        for(int i = 0;i<5;i++)
            JL[i] = new JLabel(img);
        for(int i = 0;i<5;i++)
            pane1.add(JL[i]);
        this.add(pane1,BorderLayout.CENTER);
        //设置按钮
        button = new JButton("发牌");
        button.setPreferredSize(new java.awt.Dimension(90, 60));
        //设置 按钮监听器
        button.addActionListener(this);
        pane2.add(button);
        this.add(pane2,BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e){
        if(button == e.getSource()){
            String str[] = new String[5];
            for(int i = 0;i < 5;i++){
                int first = (int)(Math.random() * 6);
                if(first == 4){
                    str[i] = "images/s1.gif";
                    img[i] = new ImageIcon(str[i]);
                    JL[i].setIcon(img[i]);
                }
                else if(first == 5){
                    str[i] = "images/s2.gif";
                    img[i] = new ImageIcon(str[i]);
                    JL[i].setIcon(img[i]);
                }
                else{
                    int second = (int)(Math.random() * 13);
                    str[i] = "images/" + one[first] + "-" + two[second] + ".gif";
                    img[i] = new ImageIcon(str[i]);
                    JL[i].setIcon(img[i]);
                }
            }
        }
    }
    private  static void createAndShowGUI(){
        area.setSize(800,500);
        area.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        area.addComponentToPane();
        area.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });
    }
}

