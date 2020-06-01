import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/19 18:45
 */
public class HomeWork1 {
    public static void main(String[] args) {
        System.out.println("请输入赵本山的考试成绩：");
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        if(score==100){
            System.out.println("爸爸给他买辆车");
        }else if(score>=90) {
            System.out.println("");
        }else if(score<90&&score>=60){
            System.out.println("");
        }else if(score<60){
            System.out.println("不及格，什么都不买");
        }
    }
}
