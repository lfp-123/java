package com.seecen.homeworks.work2;



/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/14 18:39
 */
public class Test {
    private static  Object a= new Object();
    public static void main(String[] args) throws InterruptedException {

        int u=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int z = 0; z <10; z++) {

                    synchronized (a) {
                        for (int i = 1; i < 11; i++) {
                            System.out.println("这是子线程" + i);
                        }
                        a.notify();
                        try {
                            a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

for (;u<10;u++){
    synchronized (a) {
        a.wait();
        for (int j = 1; j < 101; j++) {
            System.out.println("这是主线程" + j);

        }
        a.notify();


    }
}


        System.out.println("总共循环"+u+"次！");
    }
}

