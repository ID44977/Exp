package p20181114;
/*
public class Test1_2 {
    public static void main(String[] args) {
        boolean isRunning = true;
        AddSubtract AS = new AddSubtract();
        Add th1 = new Add(AS);
        Subtract th2 = new Subtract(AS);
        th1.start();
        th2.start();
        while(isRunning){
            if(AS.getAddNum() == AS.getSubtractNum()){
                System.out.println("They meet!");
                System.exit(0);
            }
            isRunning = false;
        }
    }

}
class AddSubtract{
    private boolean flag = false;
    int AddNum = 0;
    int SubtractNum = 10;
    int getAddNum(){
        return AddNum;
    }
    int getSubtractNum(){
        return SubtractNum;
    }
    public synchronized void print1(){
        while (true){
            try{
                AddNum++;
                System.out.println("Thread1中的n=" + AddNum);
                //flag = true;
                Thread.sleep(1000);
                this.notify();
            }catch(InterruptedException e){}
        }
    }
    public synchronized void print2(){
        while (true){
            try{
                SubtractNum--;
                System.out.println("Thread1中的n=" + SubtractNum);
                //flag = false;
                Thread.sleep(1000);
                this.notify();
            }catch(InterruptedException e){}
        }

    }
}
class Add extends Thread{
    private AddSubtract AS;
    public Add(AddSubtract AS){
        this.AS = AS;
    }
    public void run(){
        // TODO Auto-generated method stub
        AS.print1();
    }
}
class Subtract extends Thread{
    private AddSubtract AS;
    public Subtract(AddSubtract AS){
        this.AS = AS;
    }
    public void run(){
        // TODO Auto-generated method stub
        AS.print2();
    }
}

/*
public class Test1_2 {
    public static void main(String[] args) {
        Add th1 = new Add(0);
        Subtract th2 = new Subtract(th1,10);
        th1.start();
        th2.start();
        while(true){
            System.out.println("--------------------");
            if(th1.getan() == th2.getsn()){
                System.out.println("They meet!");
                System.exit(0);
            }
        }
    }
}
class Add extends Thread{
    private int AddNum;
    Add(int AddNum){
        this.AddNum = AddNum;
    }
    public int getan(){
        return AddNum;
    }
    public void run(){
        while(true){
            try{
                AddNum++;
                System.out.println("Thread1中的n=" + AddNum);
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
}
class Subtract extends Thread{
    private int SubtractNum;
    private Add th1;
    Subtract(Add th1,int SubtractNum){
        this.th1 = th1;
        this.SubtractNum = SubtractNum;
    }
    public int getsn(){
        return SubtractNum;
    }
    public void run(){
        while(true){
            try{
                SubtractNum--;
                System.out.println("Thread2中的n=" + SubtractNum);
                /*if(sn == th1.getan()){
                    System.out.println("They are meet!");
                    System.exit(0);
                }

                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
}

*/

public class Test1_2 {
    public static void main(String[] args) {
        Add th1 = new Add(0);
        Subtract th2 = new Subtract(th1,10);
        th1.start();
        th2.start();
    }
}
class Add extends Thread{
    private int an;
    Add(int an){
        this.an = an;
    }
    public int getan(){
        return an;
    }
    public void run(){
        while(true){
            try{
                an++;
                System.out.println("Thread1中的n=" + an);
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
}
class Subtract extends Thread{
    private int sn;
    private Add th1;
    Subtract(Add th1,int sn){
        this.th1 = th1;
        this.sn = sn;
    }
    public int getsn(){
        return sn;
    }
    public void run(){
        while(true){
            try{
                sn--;
                System.out.println("Thread2中的n=" + sn);
                if(sn == th1.getan()){
                    System.out.println("They are meet!");
                    System.exit(0);
                }
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }
}

