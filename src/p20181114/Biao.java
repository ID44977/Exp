package p20181114;

import java.awt.*;
import javax.swing.*;

public class Biao {
    public static void main(String[] args){
        WenBenQu p1 = new WenBenQu();
        p1.setBounds(200,200,600,400);
        p1.setTitle("9*9");
        int i,j;
        for (i = 9; i >= 0; i--){
            for(j = 1; j <= i; j++){
                p1.area.append(j + "*" + "=" + (i*j) + " " + " ");
            }
            p1.area.append("\n");
        }
    }
}
