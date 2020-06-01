import java.util.Scanner;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class TestStudents {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Students students = new Students();

        while (true) {
            System.out.print("请输入学生姓名：");
            String names = scanner.next();
            students.addStudents(names);
            System.out.print("请输入是否继续（y/n）：");
            String input = scanner.next();
            if(!input.equals("y")) {
                break;
            }
        }

        students.show();
        System.out.print("请输入你要查询的客户姓名：");
        String bigpig = scanner.next();
        students.search(bigpig);
        System.out.print("请输入要修改的学生姓名：");
        String a = scanner.next();
        System.out.print("请输入新要修改的学生姓名：");
        String b = scanner.next();
        students.midfy(a,b);
        students.show();
    }
}
