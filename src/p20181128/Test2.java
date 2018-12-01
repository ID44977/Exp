//Test2.java
package p20181128;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> new accountServer()).start();
        Thread.sleep(1);
        new Thread(() -> new accountClient()).start();
    }
}
