package cn.sc.mor;

/**
 * @author ${林锋鹏}
 * @Title: A
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/18 10:57
 */
public  class Product implements Runnable{
private Wares g; // 共同拥有的商品对象；


    public Product(){

    }
    public Product(Wares g) {
        this.g = g;
    }
/*
    当库存不足的时候，库存的数量小于消费者购买的数量
    生产者就进行生产
    生产者结束提醒消费者购买
* */
    @Override
    public void run() {
        while (true) {
            synchronized (g) {
                if (g.getNum() < g.getMaxNum()) { //消费者提醒，2 库存不够时
                    //生产的数量
                    int i = g.getMaxNum() - g.getNum();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +
                            "生产了" + g.getName() + "," + i + "个" +
                            "库存还有" + g.getMaxNum() + "个");
                    g.setNum(g.getMaxNum());
                    System.out.println("生产结束，可以购买！");
                    //提醒消费者消费
                    try {
                        g.notifyAll();
                        g.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

