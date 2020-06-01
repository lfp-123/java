import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/18 16:25
 */
public class Text7 {
    public static void main(String[] args) {
//        int money = 200;
//        System.out.println("我有200万 买什么车呢?");
//        if(money>500){
//            System.out.println("买个路虎吧");
//        }else if (money>100){
//            System.out.println("买个奥迪吧");
//        }else if (money>50){
//            System.out.println("帕瑟特吧");
//        }else  if (money>10){
//            System.out.println("买个奥托");
//        }else {
//            System.out.println("不买了，穷");
//        }
        System.out.println("请输入你的成绩：");
        Scanner input = new Scanner(System.in);
        int score = input.nextInt();
        if(score<10){
            System.out.println("请输入你的性别：");
            Scanner gender= new Scanner(System.in);
            String genders = gender.next();
            if("男".equals(genders)){
                System.out.println("恭喜你进入男子组决赛");
            }else {
                System.out.println("恭喜你进入女子组决赛");
            }

        }else {
            System.out.println("很遗憾，你没有进入决赛的资格");
        }


    }
}
