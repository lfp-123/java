import java.util.Scanner;

public class work5 {

    public static void main(String[] args) {
        // 用来接收客户端输入的类型
        Scanner aa = new Scanner(System.in);
        System.out.print("请输入HTML的成绩：");
        double htmlScore = aa.nextDouble();
        System.out.print("请输入CSS的成绩：");
        double cssScore = aa.nextDouble();
        System.out.print("请输入JS的成绩：");
        double jsScore = aa.nextDouble();
        System.out.println("-------------------------");
        System.out.println("HTML\t\tCSS\t\t\tJS");
        System.out.println(htmlScore + "\t\t" + cssScore + "\t\t" + jsScore);
        System.out.println("-------------------------");
        double cha = htmlScore - cssScore;
        double avg = (htmlScore + cssScore + jsScore) / 3;
        System.out.println("HTML和CSS的成绩差为:" + cha );
        System.out.println("三门课的平均分为:" + avg );
    }
}