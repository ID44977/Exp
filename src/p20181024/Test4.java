package p20181024;

public class Test4 {
    public static void main(String[] args) {
        Machine machine = new Machine();
        Goods[] goods = new Goods[3];
        goods[0] = new Goods(false);
        goods[1] = new Goods(true);
        goods[2] = new Goods(false);
        for (Goods good : goods) {
            System.out.println("请将包裹放在机器检查处！");
            try {
                machine.checkBag(good);
                System.out.println("物品正常！\n");
            } catch (DangerException e) {
                System.out.println("行李已被扣留！\n");
            }
        }
    }
}

class DangerException extends Exception {
    public DangerException() {
        this.toShow();
    }
    public void toShow() {
        System.out.println("属于危险品");
    }
}

class Machine {
    public void checkBag(Goods goods) throws DangerException {
        if (goods.isDanger) {
            throw new DangerException();
        }
    }
}
class Goods {
    boolean isDanger;
    public Goods(boolean isDanger) {
        this.isDanger = isDanger;
    }
}
