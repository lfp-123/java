package cn.sc.mor;

import java.util.Random;

/**
 * @author ${林锋鹏}
 * @Title: Consumer
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/18 11:07
 */
public class Consumer implements Runnable{
    private Wares g; // 共同拥有的商品对象；


    public Consumer(){

    }
    public Consumer(Wares g) {
        this.g = g;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (g) {
                Random random = new Random();
                int i = random.nextInt(g.getMaxNum() + 1);
                if (i > g.getNum()) {
                    System.out.println("库存不足。");
                    //通知生产者生产
                    g.notifyAll();
                    try {
                        g.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    g.setNum(g.getNum() - i);
                    System.out.println(Thread.currentThread().getName() +
                            "购买了" + g.getName() + ",购买了" + i + "个," +
                            "库存还有" + g.getNum() + "个");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
