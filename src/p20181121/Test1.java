package p20181121;

import java.util.*;

class Data {
    public Queue<String> input = new LinkedList<>();
    public Queue<String> output = new LinkedList<>();

    public synchronized void save(String str) {
        input.offer(str);
        this.notifyAll();
    }

    public synchronized void show(Thread thread) {
        if (input.isEmpty()) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(input.poll());
        builder.reverse();
        output.offer(builder.toString());
        System.out.println(thread.getName() + " takes the mission and begins to operate.");
        System.out.println("The reverse is " + output.poll());
    }
}

class Runnable1 implements Runnable {
    private Data data;

    public Runnable1(Data data) {
        super();
        this.data = data;
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            System.out.println("Thread1 has saved the string.");
            data.save(in.next());
        }
        in.close();
    }
}

class Runnable2 implements Runnable {
    private Data data;

    public Runnable2(Data data) {
        super();
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            data.show(Thread.currentThread());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Test1 {
    public static void main(String[] args) {
        Data data = new Data();

        Thread thread1 = new Thread(new Runnable1(data));
        Thread thread2 = new Thread(new Runnable2(data));
        Thread thread3 = new Thread(new Runnable2(data));
        Thread thread4 = new Thread(new Runnable2(data));

        System.out.println("Please input your strings:");
        thread1.setName("Thread1");
        thread2.setName("Thread2");
        thread3.setName("Thread3");
        thread4.setName("Thread4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

