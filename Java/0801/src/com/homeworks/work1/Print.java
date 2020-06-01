package com.homeworks.work1;

/**
 * @author asus
 * @Title: Print
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 19:15
 */
public class Print {

      Print(HomeLetterlmpl homeLetterlmpl){

          System.out.println(homeLetterlmpl.writeTitle()+"\n\t"
                  +homeLetterlmpl.writeHello()+"\n" +"\n"
                  +homeLetterlmpl.writeBody()+"\n"+"\n"
                  +homeLetterlmpl.writeGreeting()+"\n"
          +homeLetterlmpl.writeSelft());
    }
}
