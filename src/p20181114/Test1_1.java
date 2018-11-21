package p20181114;

public class Test1_1 {
    public static void main(String[] args) {
        Create tag = new Create();
        Thread th = new Thread(tag);
        th.start();
    }
}

class Create implements Runnable{
    private int num;
    public void run(){
        System.out.println("实现Runnable接口:");
        for(int i = 0;i < 50;i++)
        {
            num = (int)(Math.random()*100);
            System.out.println("第" + (i+1) + "个随机数字:" + num);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){}
        }
    }
}
