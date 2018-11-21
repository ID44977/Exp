package p20181107;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Area extends JFrame implements ActionListener {
    JButton button;
    int S;
    JTextField countInput1;
    JTextField countInput2;
    JTextField countInput3;
    JTextArea textArea;
    double d1,d2,d3;

    private static final long serialVersionUID = 1L;
    static Area keyevent1 = new Area("面积计算器");
    Area(String title){super(title);}

    private void addComponentToPane(){
        this.setLayout(new BorderLayout());
        JPanel p1=new JPanel(new FlowLayout());
        JPanel p2=new JPanel(new FlowLayout()); //将计算器的最下面文本区放在p2,位于south
        MyMouseAdapter listener = new MyMouseAdapter();
        countInput1 = new JTextField(5);

        p1.add(new JLabel("上低："));
        p1.add(countInput1);
        //设置鼠标事件监视器
        countInput1.addFocusListener(new countInput1CheckListener());
        //设置文本输入框的监听器
        countInput1.addMouseListener(listener);

        countInput2 = new JTextField(5);
        p1.add(new JLabel("下低："));
        p1.add(countInput2);
        countInput2.addFocusListener(new countInput2CheckListener());
        countInput2.addMouseListener(listener);

        countInput3 = new JTextField(5);
        p1.add(new JLabel("高："));
        p1.add(countInput3);
        countInput3.addFocusListener(new countInput3CheckListener());
        countInput3.addMouseListener(listener);

        button = new JButton("计算面积");
        button.addActionListener(this);
        p1.add(button);

        textArea = new JTextArea(15,40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        p2.add(scrollPane);
        this.add(p1,BorderLayout.NORTH);
        this.add(p2,BorderLayout.SOUTH);

    }

    private static void createAndShowGUI(){
        keyevent1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        keyevent1.addComponentToPane();
        keyevent1.pack();
        keyevent1.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    //处理点击"梯形的面积："按钮事件
    public void actionPerformed(ActionEvent e) {
        if(button == e.getSource()){
            double area = (d1 + d2) * d3 * 1/2;
            textArea.setText(textArea.getText()  + "梯形的面积：" + area + "\n");
        }
    }
    //处理鼠标事件,当点击文本框时，全选文本框内容
    class MyMouseAdapter extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            if(countInput1 == e.getSource()){
                countInput1.selectAll();
            }
            else if(countInput2 == e.getSource()){
                countInput2.selectAll();
            }
            else if(countInput3 == e.getSource()){
                countInput3.selectAll();
            }
        }
    }

    //验证3个文本输入框的内容是否符合要求
    class countInput1CheckListener extends FocusAdapter{
        public void focusLost(FocusEvent e) {
            try{
                d1 = Double.valueOf(countInput1.getText());
            }catch(Exception ee){
                JOptionPane.showMessageDialog(null,"请重新输入数字!(可以包含小数点)","输入错误",JOptionPane.ERROR_MESSAGE);
                countInput1.requestFocus();
                countInput1.selectAll();
            }
        }
    }
    class countInput2CheckListener extends FocusAdapter{
        public void focusLost(FocusEvent e) {
            try{
                d2 = Double.valueOf(countInput2.getText());
            }catch(Exception ee){
                JOptionPane.showMessageDialog(null,"请重新输入数字!(可以包含小数点)","输入错误",JOptionPane.ERROR_MESSAGE);
                countInput2.requestFocus();
                countInput2.selectAll();
            }
        }
    }
    class countInput3CheckListener extends FocusAdapter{
        public void focusLost(FocusEvent e) {
            try{
                d3 = Double.valueOf(countInput3.getText());
            }catch(Exception ee){
                JOptionPane.showMessageDialog(null,"请重新输入数字!(可以包含小数点)","输入错误",JOptionPane.ERROR_MESSAGE);
                countInput3.requestFocus();
                countInput3.selectAll();
            }
        }
    }
}
