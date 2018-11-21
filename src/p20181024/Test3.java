package p20181024;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) throws ParseException {
        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String name, idNum, password, sex;
        while (true) {
            System.out.println("请输入学生信息：");
            System.out.print("姓名：");
            name = sc.nextLine();
            if ("00000000".equals(name)) {
                break;
            }
            while (true) {
                try {
                    checkName(name);
                    break;
                } catch (IllegalInput e) {
                    System.out.println(e.getMessage());
                    name = sc.nextLine();
                }
            }

            System.out.print("身份证号：");
            idNum = sc.nextLine();
            while (true) {
                try {
                    checkIdNum(idNum);
                    break;
                } catch (IllegalInput e) {
                    System.out.println(e.getMessage());
                    idNum = sc.nextLine();
                }
            }

            System.out.print("密码：");
            //sc.nextLine();
            password = sc.nextLine();
            while (true) {
                try {
                    checkPassword(password);
                    break;
                } catch (IllegalInput e) {
                    System.out.println(e.getMessage());
                    password = sc.nextLine();
                }
            }
            System.out.print("性别：");
            sex = sc.nextLine();
            while (true) {
                try {
                    checkSex(sex);
                    break;
                } catch (IllegalInput e) {
                    System.out.println(e.getMessage());
                    sex = sc.nextLine();
                }
            }

            list.add(new Student(name, idNum, password,sex));
        }

        //输出所建立的所有学生的信息
        for (Student student : list) {
            System.out.println(student);
        }
    }

    private static void checkSex(String sex) throws IllegalInput {
        if ("male".equals(sex)) {
            return;
        }
        if ("female".equals(sex)) {
            return;
        }
        throw new IllegalInput("性别只能为'male'或'female'，请重新输入性别!");
    }

    private static void checkPassword(String password) throws IllegalInput {
        //不全是数字，不全是字母，超过8位
        String regex = "^(?=.*[0-9].*)(?=.*[A-Z].*)(?=.*[a-z].*).{8,}$";
        if (!password.matches(regex)) {
            throw new IllegalInput("密码必须超过8位，且包含数字和大小写字母，请重新输入密码！");
        }
    }

    private static void checkIdNum(String idNum) throws IllegalInput {
        if (!idNum.matches("\\d{18}")) {
            throw new IllegalInput("身份证号必须是18位数字串，请重新输入身份证号!");
        }
    }

    private static void checkName(String name) throws IllegalInput {
        if (!name.matches("[a-zA-Z^ ]+")) {
            throw new IllegalInput("姓名只允许包含英文字符，请重新输入姓名!");
        }
    }
}

//自定义异常 IllegalInput
class IllegalInput extends Exception {
    public IllegalInput(String message) {
        super(message);
    }
}


class Student {
    private static int StuNum = 2018133000;//学号 10位数字.每增加一个用户,学号加1
    private String name;//学生姓名,只允许包含英文字符
    private String idNum;//身份证号 18位数字字符串
    private Date birthday;//出生日期，不需要用户输入，取身份证号码的一部分
    private String password;//用户密码，位数大于8的字符串。必须包含数字和字母
    private String sex;//性别,只能输入“male”或“female”
    public Student(String name, String idNum, String password,
                   String sex) throws ParseException {
        super();
        ++StuNum;
        this.name = name;
        this.idNum = idNum;
        this.birthday = birthday;
        //StringBuilder效率较StringBuffer高
        StringBuilder sb = new StringBuilder(idNum.substring(6, 14));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        birthday = sdf.parse(sb.toString());
        this.password = password;
        this.sex = sex;
    }
    //重写toString()方法
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return "Account [StuNum=" + StuNum + ", name=" + name + ", idNum=" + idNum + ", birthday=" + sdf.format(birthday)
                + ", password=" + password + ", sex=" + sex + "]";
    }
}
