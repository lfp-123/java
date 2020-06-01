import java.util.Scanner;

public class Text5 {
    public static void main(String[] args) {

        System.out.print("请输入会员生日：");
        Scanner input = new Scanner(System.in);
        String str = input.next();
        String red = "(((0[1-9])|(1[0-2])))-((0[1-9])|([1-2][0-9])|(3[0-1]))";
        boolean b = str.matches(red);
        System.out.println(b);
        if(b){
            System.out.println("录入的会员信息生日为"+str);
        }
    }
}
