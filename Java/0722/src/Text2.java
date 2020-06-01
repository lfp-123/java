import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 10:52
 */
public class Text2 {
    public static void main(String[] args) {
        System.out.println("合格了吗？y/n");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        while("y".equals(str)){
            System.out.println("上午阅读教材");
            System.out.println("下午上机编程");
            str = "n";
        }
        System.out.println("完成学习目标");
    }
}
