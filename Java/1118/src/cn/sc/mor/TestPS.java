package cn.sc.mor;

/**
 * @author ${林锋鹏}
 * @Title: TestPS
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/18 11:38
 */
public class TestPS {
    public static void main(String[] args) {
        Wares wares = new Wares("糖果",0,10);
        Consumer consumer = new Consumer(wares);
        Product product = new Product(wares);
        Thread lib = new Thread(consumer, "李白");
        Thread xiao = new Thread(product, "小卖部");
        lib.start();
        xiao.start();
    }
}
