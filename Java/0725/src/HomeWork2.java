import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/25 19:14
 */
public class HomeWork2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("请输入Java文件名：");
            String filename = scanner.next();
            System.out.println("请输入你的邮箱：");
            String email = scanner.next();
                email.indexOf(".");
                email.indexOf("@");
            if(filename.endsWith(".java")&&email.indexOf("@")-email.indexOf(".")>1){
                System.out.println("作业提交成功！");break;
            }else {
                System.out.println("作业提交失败！");

            }



        }
    }
}
