import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/25 20:09
 */
public class HomeWork5 {
    public static void main(String[] args) {
        double s =0;
        String[][] goods ={
                {"电风扇","123.23"},
                {"洗衣机","4500.0"},
                {"电视机","8800.9"},
                {"电冰箱","5500.88"},
                {"空调机","4556.0"}};

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String userName = scanner.next();
        System.out.print("请输入密码：");
        String passWorld = scanner.next();
        if(userName.equals("TOM")&&passWorld.equals("123")){
            System.out.println("*******欢迎进入商品批发城*******");
            System.out.println("编号"+"\t\t商品"+"\t\t价格");
            for (int i = 0; i < goods.length; i++) {
                System.out.print((i+1)+"\t");
                for (int j = 0; j <goods[i].length ; j++) {
                    System.out.print(goods[i][j]+"\t");

                }
                System.out.println();
            }
            System.out.println("*****************************");
            System.out.print("请输入您的批发的号：");
            int classNumber = scanner.nextInt();
            System.out.print("请输入批发数量：");
            int num = scanner.nextInt();
            if(classNumber==1){
                double a = Double.parseDouble(goods[0][1]);
                s = num*a;
            }
            if(classNumber==2){
                double a = Double.parseDouble(goods[1][1]);
                s = num*a;
            }
            if(classNumber==3){
                double a = Double.parseDouble(goods[2][1]);
                s = num*a;
            }
            if(classNumber==4){
                double a = Double.parseDouble(goods[3][1]);
                s = num*a;
            }
            if(classNumber==5){
                double a = Double.parseDouble(goods[4][1]);
                s = num*a;
            }
            System.out.println("您需要付款："+s);
        }
    }
}
