package cn.sc.work;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 17:08
 */
public class Test {
    static Scanner input =  new Scanner(System.in);
    public static void main(String[] args) {
        new Test().start();
    }

    public void start(){
        boolean index =true;
        do {
            Unit.show();
            int option = input.nextInt();
            switch (option) {
                case 1:
                    Unit.Registered();
                    break;
                case 2:
                    boolean login = Unit.Login();
                    if (login) {
                        Unit.secondShow();
                    } else {
                        System.out.println("登陆失败！请核对后再输入！");
                    }
                    break;
                case 3:
                    //退出
                    index = false;
                    break;
                default:
            }
        }while (index);
    }
}
