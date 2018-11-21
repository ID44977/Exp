package p20181114;

import javax.swing.*;
import java.awt.*;

public class WenBenQu extends JFrame {
    JTextArea area;
    public WenBenQu(){
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init(){
        setLayout(new FlowLayout());
        area = new JTextArea(15,40);
        add(area);
    }
}
