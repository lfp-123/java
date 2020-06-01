import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 14:08
 */
public class Text5 {
    public static void main(String[] args) {
        String items1 ="T恤";
        double price1 = 245;
        String items2 ="网球鞋";
        double price2 = 350;
        String items3 ="网球拍";
        double price3 = 500;
        double number = 0;
        double all1 =0;
        double all2 =0;
        double all3 =0;
        double rate = 0.8;
        System.out.println("***********************************");
        System.out.println("请选择购买的商品编号");
        System.out.println("1."+items1+"\t\t2."+items2+"\t\t3."+items3);
        System.out.println("************************************");

//        if(input==2){
//            System.out.println("网球鞋的价格为：570");
//        }
//        System.out.println("是否继续（y/n）");
//        String str= scanner.next();
//
//        if(str.equals("y")){
//            System.out.println("请输入商品编号：");
//            int num = scanner.nextInt();
//            if(num ==1){
//                System.out.println("T恤的价格为:245");
//            }
//        }
        boolean str = true;
        while(str) {
            System.out.print("请输入商品编号：");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            System.out.print("请输入商品数量：");
            int num = scanner.nextInt();
            switch (input) {
                case 1:
                        all1= num*price1;
                        number = all1+number;
                    System.out.println(items1+"\t\t"+"数量："+num+"\t\t￥"+all1);

                    break;
                case 2:
                    all2= num*price2;
                    number = all2+number;
                    System.out.println(items2+"\t\t"+"数量："+num+"\t\t￥"+all2);
                break;
                case 3:
                   all3= num*price3;
                    number = all3+number;
                    System.out.println(items3+"\t\t"+"数量："+num+"\t\t￥"+all3);
                    break;
                default:
                    System.out.println("输入错误");
            }


                System.out.print("是否继续y/n: ");
                String str1 = scanner.next();
                if(str1.equals("y")){

                    }else {
                    System.out.println("折扣："+rate);
                    System.out.println("应付金额："+number*rate);
                    System.out.print("实付金额：");
                    int realnumber = scanner.nextInt();
                    int realnumber1 = (int)(realnumber-number*rate);
                    System.out.println("找钱："+realnumber1);
                    str = false;
                    }
        }
    }
}
