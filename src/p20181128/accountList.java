//accountList.java
package p20181128;

import java.util.ArrayList;

public class accountList {
    private static ArrayList<Account1128> list = new ArrayList<>();
    static {
        list.add(new Account1128("Tony","123",10000));
        list.add(new Account1128("Tom","321",99));
        list.add(new Account1128("Jack","zxc",388788));
        list.add(new Account1128("Cliff","vbn",999999));

    }
    public static ArrayList<Account1128> getList() {
        return list;
    }
}

class Account1128 {
    private static int count = 80000000;
    private int accountNum;
    private String name;
    private String password;
    private double balance;

    public Account1128() {}
    public Account1128(String name, String password, double balance) {
        ++count;
        this.accountNum = count;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }
    public int getAccountNum() {
        return accountNum;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private void setBalance(double balance) {
        this.balance = balance;
    }
    /*
    取款
     */
    public boolean deposit(double money) {
        if (money <= 0) {
            return false;
        }
        balance += money;
        return true;
    }
    /*
    存款
     */
    public boolean withdraw(double money) {
        if (balance < money || money <= 0) {
            return false;
        }
        balance -= money;
        return true;
    }

    /*
    同行转账
     */
    public boolean transfer(Account1128 acc,double money) {
        if (money <= 0 || balance < money) {
            return false;
        }
        this.withdraw(money);
        acc.deposit(money);
        return true;
    }

    @Override
    public String toString() {
        return "Account [accountNum=" + accountNum + ", name=" + name + ", password=" + password + ", balance="+ balance + "]";
    }
}