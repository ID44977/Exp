package p20181121;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 {
    public static void main(String[] args) {
        Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);
        System.out.println("之前余额  取款额  当前的余额\n");
        for (int i = 1; i <= 100; i++) {
            service.execute(new Withdraw(account, 1));
        }
        service.shutdown();
        while (!service.isTerminated()) {
        }
        System.out.println("账户余额: " + account.getBalance());
    }
}