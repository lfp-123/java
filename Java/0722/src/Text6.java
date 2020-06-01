import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 15:34
 */
public class Text6 {
    public static void main(String[] args) {
        double hua = 0;
        double she = 0;
        int i = 0;
        System.out.println("华氏氏度\t\t\t摄氏温度");
        do {
            hua = she*9/5.0+32;
            System.out.println(hua+"\t\t\t"+she);
            she = she+20;
            i++;
        }while (i<10&&she<250);


    }
}
