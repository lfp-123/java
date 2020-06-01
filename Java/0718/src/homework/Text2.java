package homework;

import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/18 19:01
 */
public class Text2 {
    public static void main(String[] args) {

        System.out.println("请输入你的考试成绩：");
        Scanner score = new Scanner(System.in);
        double realScore = score.nextDouble();
        if(realScore==100) {
            System.out.println("父亲奖励一辆小汽车");
        }
        else if(realScore>=90){
         System.out.println("母亲给她买台笔记本电脑");
        }

        else  if(realScore>60){
           System.out.println("母亲给她买部手机");
        }
        if(realScore<60){
            System.out.println("不及格，处罚全部零花钱存款");
        }
    }
}
