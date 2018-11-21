package p20181107;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AccountDemo implements  ActionListener {
    LinkedList<Account> mylist = new LinkedList<Account>();
    static int accountNum = 80000000;
    static JFrame frame;
    Area addFrame ,listFrame;
    Container pane;
    JMenuBar menuBar;          //菜单栏
    JMenu menu1,menu2;         //菜单
    JMenuItem  addMenuItem,checkMenuItem,deleteMenuItem,listMenuItem,exitMenuItem;    //菜单项
    JPopupMenu addpopup;
    //添加账户中的变量
    JTextField nameInput,idNumInput,balanceInput,Birthday;
    JPasswordField passInput;
    JButton Sure,Cancel,Exit;
    JRadioButton man,woman;
    String sex;

    public JMenuBar createMenuBar(){
        menuBar = new JMenuBar();      //创建菜单栏

        //创建第一个菜单"账户操作"
        menu1 = new JMenu("账户操作(F)");
        menu1.setMnemonic(KeyEvent.VK_F);  //设置键盘助记符
        menuBar.add(menu1);
        //创建第二个菜单"帮助(h)"
        menu2 = new JMenu("帮助(H)");
        menu2.setMnemonic(KeyEvent.VK_H);  //设置键盘助记符
        menuBar.add(menu2);
        //创建第一个菜单的菜单项-添加账户(A)
        addMenuItem = new JMenuItem("添加账户(A)");
        addMenuItem.setMnemonic(KeyEvent.VK_A);
        addMenuItem.addActionListener(this);
        menu1.add(addMenuItem);

        //创建第二个菜单的菜单项-查找账户(C)
        checkMenuItem = new JMenuItem("查找账户(C)");
        checkMenuItem.setMnemonic(KeyEvent.VK_C);
        checkMenuItem.addActionListener(this);
        menu1.add(checkMenuItem);

        //创建第三个菜单的菜单项-删除账户(D)
        deleteMenuItem = new JMenuItem("删除账户(D)");
        deleteMenuItem.setMnemonic(KeyEvent.VK_D);
        deleteMenuItem.addActionListener(this);
        menu1.add(deleteMenuItem);

        //创建第四个菜单的菜单项-显示账户(L)
        listMenuItem = new JMenuItem("显示账户(L)");
        listMenuItem.setMnemonic(KeyEvent.VK_L);
        listMenuItem.addActionListener(this);
        menu1.add(listMenuItem);

        //创建第五个菜单的菜单项-退出(E)
        exitMenuItem = new JMenuItem("退出(E)");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        //addMenuItem.setActionCommand("add");
        exitMenuItem.addActionListener(this);
        menu1.add(exitMenuItem);
        return menuBar;
    }

    //菜单项事件处理方法
    public void actionPerformed(ActionEvent e){
        String S = e.getActionCommand();
        if(S.equals("添加账户(A)")){
            addFrame = new Area("添加账户");
            addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addFrame.setContentPane(new addFrametopane());
            addFrame.pack();
            addFrame.setVisible(true);
        }
        else if(S.equals("查找账户(C)")){
            String s = (String)JOptionPane.showInputDialog(frame, "请输入查找账户的账户名或姓名","查找账户",JOptionPane.QUESTION_MESSAGE);
            Pattern p1 = Pattern.compile("\\D+");
            Matcher m1 = p1.matcher(s);
            Pattern p2 = Pattern.compile("\\d{8}");
            Matcher m2 = p2.matcher(s);
            if(m1.matches() || m2.matches()){
                //查找中遍历查询
                Iterator<Account> it = mylist.iterator();
                while(it.hasNext()){
                    try{
                        Account temp = new Account();
                        temp = it.next();
                        String str1 = temp.getAccountNum();
                        String str2 = temp.getName();
                        if(s.equals(str1) || s.equals(str2))
                            JOptionPane.showMessageDialog(null,"账户号           姓名              余额 \n"+temp.toString(),"账户存在",JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null,"账户不存在","查找结果",JOptionPane.PLAIN_MESSAGE);
                    }catch(Exception ee)
                    {}
                }
            }
            else
                JOptionPane.showMessageDialog(null,"输入并非账户号或姓名，请重新输入","输入错误",JOptionPane.ERROR_MESSAGE);
        }
        else if(S.equals("删除账户(D)")){
            String s = (String)JOptionPane.showInputDialog(frame, "请输入删除账户的账户名或姓名","删除账户",JOptionPane.QUESTION_MESSAGE);
            Pattern p1 = Pattern.compile("\\D+");
            Matcher m1 = p1.matcher(s);
            Pattern p2 = Pattern.compile("\\d{8}");
            Matcher m2 = p2.matcher(s);
            if(m1.matches() || m2.matches()){
                //删除中遍历查询
                Iterator<Account> it = mylist.iterator();
                while(it.hasNext()){
                    try{
                        Account temp = new Account();
                        temp = it.next();
                        String str1 = temp.getAccountNum();
                        String str2 = temp.getName();
                        if(s.equals(str1) || s.equals(str2))
                        {
                            //JOptionPane.showMessageDialog(null,"账户号           姓名              余额 \n"+temp.toString(),"账户存在",JOptionPane.PLAIN_MESSAGE);
                            String[] options = {"真的忍心删除？","算了不舍得！"};
                            int n = JOptionPane.showOptionDialog(null,"你确定要删除" + temp.getName() +"的账户吗？ ", "询问中",  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,options[0]);
                            if(n == 0)
                            {
                                int i = mylist.indexOf(temp); //获取mylist中temp的索引
                                mylist.remove(i);              //删除i位置元素
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(null,"账户不存在","查找结果",JOptionPane.PLAIN_MESSAGE);
                    }catch(Exception ee)
                    {}
                }
            }
            else
                JOptionPane.showMessageDialog(null,"输入并非账户号或姓名，请重新输入","输入错误",JOptionPane.ERROR_MESSAGE);
        }
        else if(S.equals("显示账户(L)")){
            listFrame = new Area("显示账户(L)");
            listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel listpane = new JPanel(new BorderLayout());
            JPanel pane1  = new JPanel(new GridLayout(1,3,1,1));
            JPanel pane2  = new JPanel(new BorderLayout());
            pane1.add(new JLabel("账户号"));
            pane1.add(new JLabel("姓号"));
            pane1.add(new JLabel("余额"));

            JTextArea TextArea = new JTextArea();
            pane2.add(new JScrollPane(TextArea),BorderLayout.CENTER);

            Iterator<Account> it = mylist.iterator();
            while(it.hasNext()){
                TextArea.setText(TextArea.getText()   + it.next() + "\n");  //第一项是为了加上前面已经显示的名字，以免被覆盖
            }
            listpane.add(pane1,BorderLayout.NORTH);
            listpane.add(pane2,BorderLayout.SOUTH);
            listFrame.add(listpane);
            listFrame.pack();
            listFrame.setVisible(true);
        }
        else if(S.equals("退出(E)")){
            frame.dispose();
        }

    }

    //添加账户菜单具体窗口
    class addFrametopane extends JPanel implements  ActionListener{
        private static final long serialVersionUID = 1L;
        addFrametopane() {
            super(new BorderLayout());  //
            JPanel p1=new JPanel(new GridLayout(7,2,1,1));
            JPanel p2=new JPanel(new GridLayout(1,2,3,3));

            //设置账号
            p1.add(new JLabel("账号："));
            p1.add(new JLabel((++accountNum) + ""));
            //设置名字
            p1.add(new JLabel("名字："));
            nameInput = new JTextField(5);
            p1.add(nameInput);
            //设置身份证号码
            p1.add(new JLabel("身份证号码："));
            idNumInput = new JTextField(15);
            p1.add(idNumInput);
            //设置出生日期
            p1.add(new JLabel("出生日期："));
            Birthday = new JTextField(15);
            Birthday.setEditable(false);
            p1.add(Birthday);
            //设置账户余额
            p1.add(new JLabel("余额："));
            balanceInput = new JTextField(15);
            p1.add(balanceInput);
            //设置密码
            p1.add(new JLabel("密码："));
            passInput = new JPasswordField(15);
            p1.add(passInput);

            //设置性别以及单选按钮
            p1.add(new JLabel("性别："));
            man = new JRadioButton("男");
            man.setSelected(true);
            man.setActionCommand("男");
            woman = new JRadioButton("女");
            woman.setActionCommand("女");
            ButtonGroup group = new ButtonGroup();
            group.add(man);
            group.add(woman);
            man.addActionListener(this);
            woman.addActionListener(this);
            JPanel radioPanel = new JPanel(new GridLayout(1,2));
            radioPanel.add(man);
            radioPanel.add(woman);
            p1.add(radioPanel);

            Sure = new JButton("确定");
            Sure.addActionListener(this);
            p2.add(Sure);
            Cancel = new JButton("取消");
            Cancel.addActionListener(this);
            p2.add(Cancel);
            Exit = new JButton("退出");
            Exit.addActionListener(this);
            p2.add(Exit);
            this.add(p1,BorderLayout.NORTH);
            this.add(p2,BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e){
            String S = e.getActionCommand();
            if(S.equals("男") || S.equals("女"))
                sex = S;

            if(Sure == e.getSource()){
                //以下是点击"确认"后,的操作
                //检测名字合法性
                Pattern p1 = Pattern.compile("\\D+");
                Matcher m1 = p1.matcher(nameInput.getText());
                if(!m1.matches()){
                    JOptionPane.showMessageDialog(null,"名字输入不合法，请重新输入只包含英文字符字符串","输入错误",JOptionPane.ERROR_MESSAGE);
                    nameInput.requestFocus();
                    nameInput.selectAll();
                    return ;                 //出现错误则直接退出检查，并全选名字，让用户重新输入
                }                            //否则则继续下面的检查

                //检测身份证号码的合法性
                Pattern p2 = Pattern.compile("\\d{18}");
                Matcher m2 = p2.matcher(idNumInput.getText());
                if(!m2.matches()){
                    JOptionPane.showMessageDialog(null,"身份证号码输入不合法，请重新输入18位的数字字符串","输入错误",JOptionPane.ERROR_MESSAGE);
                    idNumInput.requestFocus();
                    idNumInput.selectAll();
                    return ;
                }
                //否则身份证号码正确则添加出生日期
                Date d;
                String date1 = idNumInput.getText().substring(6,14);
                SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat b = new SimpleDateFormat("yyyy/MM/dd");

                try{
                    d  =  f.parse(date1);
                    String date2 = b.format(d);
                    Birthday.setText(date2);
                }catch(Exception ee){System.out.println("日期异常");}

                //检测余额的合法性
                double ba = Double.parseDouble(balanceInput.getText());
                if(ba<0){
                    JOptionPane.showMessageDialog(null,"余额输入不合法，请重新输入大于0的数字","输入错误",JOptionPane.ERROR_MESSAGE);
                    balanceInput.requestFocus();
                    balanceInput.selectAll();
                    return ;
                }
                //检测密码的合法性
                String pw = new String(passInput.getPassword());
                Pattern p3 = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,100}$");
                Matcher m3 = p3.matcher(pw);
                if(!m3.matches()){
                    JOptionPane.showMessageDialog(null,"密码输入不合法，请重新输入位数大于8包含数字和字母的字符串","输入错误",JOptionPane.ERROR_MESSAGE);
                    passInput.requestFocus();
                    passInput.selectAll();
                    return ;
                }
                //经过上面检测,成功后则可写入LinkedList链表里
                mylist.add(new Account(accountNum,nameInput.getText(),idNumInput.getText(),ba,pw,sex));
            }
            //点击"取消"后的操作,清除所有输入
            else if(Cancel == e.getSource())
            {
                nameInput.setText(null);
                idNumInput.setText(null);
                Birthday.setText(null);
                balanceInput.setText(null);
                passInput.setText(null);
            }
            else if(Exit == e.getSource()){
                addFrame.dispose();
            }
        }
    }

    //创建GUI以及主函数
    private static void createAndShowGUI(){
        //创建窗体
        frame = new JFrame("AcccountDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AccountDemo demo = new AccountDemo();
        frame.setJMenuBar(demo.createMenuBar());
        //获取内容区
        Container pane = frame.getContentPane();
        pane.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    //创建Account对象
    class Account implements Comparable{
        private int accountNum;
        private String name;
        private String idNum;
        private Date birthday;
        private double balance;
        private String password;
        private String sex;
        public Account(){}
        public Account(int account,String name,String idNum,double balance,String password,String sex){
            this.accountNum  = account;
            this.name = name;
            this.idNum = idNum;
            this.balance = balance;
            this.password = password;
            this.sex = sex;
            String sdate = idNum.substring(6,14);
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            try{
                birthday = f.parse(sdate);
            }catch(Exception e){
                System.out.println(e.getMessage());}
        }
        public String getName(){
            return name;
        }
        public String getAccountNum(){
            return String.valueOf(accountNum);
        }
        public int compareTo(Object p){
            Account a = (Account)p;
            return (int)(- this.balance + a.balance);//实现降序输出
        }

        public String toString(){
            return accountNum+"        "+name+"      "+balance;
        }
    }
}

