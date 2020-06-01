package cn.sc.mor.Exam;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Exam
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 16:14
 */
public class Exam {
    static Scanner input =  new Scanner(System.in);
    static Exam ex =  new Exam();
    static Question user = new Question();
    static double index =0;
    static int i=0;
    static String[] answer =  new String[5];

    public static void main(String[] args) {
        System.out.println("欢迎进入考试系统");
        System.out.println("请输入账号：");
        String Username = input.next();
        System.out.println("请输入密码：");
        String password = input.next();
        if(Username.equals("admin")&&password.equals("123")){
            start();
        }else {
            System.out.println("账户密码输入不正确，请重新输入：");
            main(null);
        }

    }
    public static void start(){
        System.out.println("欢迎进入考试系统");
        System.out.println("请选择你要考试的范围：");
        System.out.println("1.java");
        System.out.println("2.c++");
        System.out.println("3.python");
        System.out.println("4.Oracle");
        int next = input.nextInt();
        switch (next){
            case 1:
                ex.javas();
                break;
            case 2:break;
            case 3:break;
            case 4:break;
            default:
        }
    }
    @Test
    public  void javas(){
        Iterator<TestPager> its =Init.listPaper.iterator();
        while (its.hasNext()){
            TestPager next = its.next();
            List<Question> list = next.getList();
            Iterator<Question> itss = list.iterator();
            while (itss.hasNext()){
                Question next1 = itss.next();
                Integer id = next1.getId();
                String name = next1.getName();
                String answerA = next1.getAnswerA();
                String answerB = next1.getAnswerB();
                String answerC = next1.getAnswerC();
                String answerD = next1.getAnswerD();
                System.out.println(id
                        + name+"\n"
                        +answerA+"\n"
                        +answerB+"\n"
                        +answerC+"\n"
                        +answerD+"\n请作答：");
                String useranswer = input.next();

                answer[i++]=useranswer;
                if(useranswer.equals(next1.getAnswer())){
                    index++;
                }

            }
            for (int i = 1; i < 6; i++) {
                System.out.println("第"+i+"题您的答案是："+answer[i-1]);
            }
            System.out.println("您的正确率是："+(index/5));
        }
    }

}
