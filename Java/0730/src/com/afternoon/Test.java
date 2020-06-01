package com.afternoon;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/30.
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入你要领养的宠物名字:");
        String name = scanner.next();
        System.out.print("请输入你要领养的宠物类型(1,狗狗 2,企鹅):");
         int choose = scanner.nextInt();
         if(choose==1){
             System.out.print("请选择狗的品种1\t"+Cons.GOG_TYPE_LBLD+2+"\t"+Cons.GOG_TYPE_XNR+":");
             int dogtype = scanner.nextInt();
             String type = dogtype==1?Cons.GOG_TYPE_LBLD:Cons.GOG_TYPE_XNR;
             System.out.print("请输入狗狗的健康值:");
            int health =scanner.nextInt();
             System.out.println("宠物的自白:");
           Dog dog =  new Dog(name,health,0,type);
             System.out.println(dog);
         }
         if(choose==2){
             System.out.print("请选择企鹅的品种1\t"+Cons.PENGUIN_TYPE_MAN+2+"\t"+Cons.PENGUIN_TYPR_FEMALE+":");
             int dogtype = scanner.nextInt();
             String type = dogtype==1?Cons.PENGUIN_TYPE_MAN:Cons.PENGUIN_TYPR_FEMALE;
             System.out.print("请输入企鹅的健康值:");
            int health =scanner.nextInt();
             System.out.println("宠物的自白:");
            Penguin dog =  new Penguin(name,health,0,type);
             System.out.println(dog);
         }



    }
}
