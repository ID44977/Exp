//accountServer.java
package p20181128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class accountServer {
    private ServerSocket server;
    private Socket socket;
    private int i = 0;
    public accountServer() {
        init();
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException e1) {}

            System.out.println("Client call：" + (++i));
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        String message = br.readLine();

                        if (message.startsWith("query-")) {
                            dealQuery(ps, message);

                        } else if (message.startsWith("changePassword-")) {
                            dealChangePassword(br, ps, message);

                        } else if (message.startsWith("deposit-")) {
                            dealDeposit(br, ps, message);

                        } else if (message.startsWith("withdraw-")) {
                            dealWithdraw(br, ps, message);

                        } else {
                            dealTransfer(br, ps, message);
                        }

                    } catch (IOException e) {}
                }
            }.start();
        }
    }

    private void init() {
        int port = 8000;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {}
    }

    private Account1128 getAccount(String accountNum) {
        ArrayList<Account1128> list = accountList.getList();
        Account1128 account = null;
        for (Account1128 acc : list) {
            if ((acc.getAccountNum() + "").equals(accountNum)) {
                account = acc;
                break;
            }
        }
        return account;
    }
    private void dealTransfer(BufferedReader br, PrintStream ps, String message) throws IOException {
        String accountNum = message.substring(9);
        String targetAccountNum = br.readLine();
        Account1128 acc1 = getAccount(accountNum);
        Account1128 acc2 = getAccount(targetAccountNum);
        if (acc1 != null && acc2 != null) {
            String moneyStr = br.readLine();
            double money = new Double(moneyStr);
            if (acc1.transfer(acc2, money)) {
                ps.println("transfer successfully!");
            } else {
                ps.println("transfer failed!");
            }
        } else {
            ps.println("account does not exist!");
        }
    }

    private void dealWithdraw(BufferedReader br, PrintStream ps, String message) throws IOException {
        String accountNum = message.substring(9);
        Account1128 acc = getAccount(accountNum);
        if (acc != null) {
            String moneyStr = br.readLine();
            double money = new Double(moneyStr);
            if (acc.withdraw(money)) {
                ps.println("withdraw successfully!\n" +acc);//
            } else {
                ps.println("withdraw failed!");
            }
        } else {
            ps.println("account does not exist!");
        }
    }

    private void dealDeposit(BufferedReader br, PrintStream ps, String message) throws IOException {
        String accountNum = message.substring(8);
        Account1128 acc = getAccount(accountNum);
        if (acc != null) {
            String moneyStr = br.readLine();
            double money = new Double(moneyStr);
            if (acc.deposit(money)) {
                ps.println("deposit successfully!\n" + acc);
            } else {
                ps.println("deposit failed!");
            }
        } else {
            ps.println("account does not exist!");
        }
    }

    private void dealChangePassword(BufferedReader br, PrintStream ps, String message) throws IOException {
        String accountNum = message.substring(15);
        Account1128 acc = getAccount(accountNum);
        if (acc != null) {
            String newPass = br.readLine();
            acc.setPassword(newPass);
            ps.println("password has updated!\n" + acc);
        } else {
            ps.println("account does not exist!");
        }
    }

    private void dealQuery(PrintStream ps, String message) {
        String accountNum = message.substring(6);
        Account1128 acc = getAccount(accountNum);
        printInfo(ps, acc);
    }

    private void printInfo(PrintStream ps, Account1128 acc) {
        if (acc == null) {
            ps.println("the accountNum you typed does not exist!");
        } else {
            ps.println(acc);
        }
    }
}

