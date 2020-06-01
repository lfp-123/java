package com.seecen.homeworks.work1;

/**
 * @author ${林锋鹏}
 * @Title: PrintSelf
 * @ProjectName Java
 * @date 2019/8/14 17:35
 */

    public class PrintSelf implements Runnable{

        private String name;
        private Object pre;
        private Object self;
        public PrintSelf(String name,Object pre,Object self){
            this.name=name;
            this.pre=pre;
            this.self=self;
        }
    @Override
        public void run() {

            int count=10;
            while(count>0){
                synchronized(pre){
                    synchronized(self){
                        System.out.print(name);
                        count--;
                        self.notifyAll();
                    }
                    try{
                        pre.wait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }



}
