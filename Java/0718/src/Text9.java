import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/18 17:11
 */
public class Text9 {
    public static void main(String[] args) {
        System.out.println("请输入是否为会员，是请输入：是，否则输入：否");
        Scanner vip = new Scanner(System.in);
        String isVip= vip.next();
        double discount = 0.75;
        double discount1= 0.8;
        double discount2 =0.9;

        if("是".equals(isVip)){
            System.out.println("尊敬的会员，您将享受会员服务");
            System.out.println("请输入你的消费金额：");
            Scanner money = new Scanner(System.in);
            double realMoney = money.nextDouble();
            if(realMoney>200){
                System.out.println("由于你消费大于200所以你将享受7.5折的优惠");
                System.out.println("您需要支付金额的为："+realMoney*discount);
            }else {
                System.out.println("您需要支付的金额为："+realMoney*discount1);
            }

        }else {
            System.out.println("请输入你的消费金额：");
            Scanner money = new Scanner(System.in);
            double realMoney = money.nextDouble();
            System.out.println("由于你不是会员，你讲享受的折扣是九折");
            System.out.println("您需要支付的金额为："+realMoney*discount2);
        }

    }
}
