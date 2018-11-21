package p20181121;

public class Withdraw implements Runnable {
    private Account account; // 存入账户
    private double money; // 存入金额
    public Withdraw(Account account, double money) {
        this.account = account;
        this.money = money;
    }
    @Override
    public void run() {
        synchronized (account){
            account.withdraw(money);
        }
    }
}
