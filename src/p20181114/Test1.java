package p20181114;

public class Test1 {
    public static void main(String[] args) {
        Createnum tag = new Createnum();
        tag.start();
    }
}
class Createnum extends Thread{
    private int num;
    public void run(){
        System.out.println("继承Thread类: ");
        for(int i = 0;i < 50;i++)
        {
            num = (int)(Math.random()*100);
            System.out.println("第" + (i+1) + "个随机数:" + num);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){}
        }
    }
}

