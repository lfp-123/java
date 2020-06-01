package com.seecen;

/**
 * @author ${林锋鹏}
 * @Title: Demo1
 * @ProjectName Java
 * @date 2019/8/12 15:43
 */
public class Demo1 {


    public static void main(String[] args) {
        PrintNum p=new PrintNum();
        Thread t1=new Thread(p);
        Thread t2=new Thread(p);
        t1.setName("甲");
        t2.setName("乙");
        t1.start();
        t2.start();
    }
}
class PrintNum implements Runnable{
    int num=1;
    @Override
    public void run() {
        synchronized (this) {
            while(true){
                notify();//唤醒wait()的一个或者所有线程
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":"  + num);
                    num++;
                } else {
                    break;
                }
                try {
                    wait();//释放当前的锁，另一个线程就会进来
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
