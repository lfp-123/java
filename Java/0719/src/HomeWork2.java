import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/19 19:16
 */
public class HomeWork2 {
    public static void main(String[] args) {
        System.out.println("张三为了他的手机设置快捷拨号：");
        Scanner scanner = new Scanner(System.in);
        String data="";
        int num = scanner.nextInt();
        switch (num){
            case 1:
                data = "爸爸";
                break;
            case 2:
                data = "妈妈";
                break;
            case 3:
                data = "爷爷";
                break;
            case 4:
                data = "奶奶";
                break;
            default: data="了错误";
        }
        System.out.println("拨了"+data+"的号码");
    }
}
