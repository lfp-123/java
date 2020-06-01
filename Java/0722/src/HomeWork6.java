import javax.sound.midi.MidiChannel;
import java.time.chrono.MinguoChronology;
import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/22 19:11
 */
public class HomeWork6 {
    public static void main(String[] args) {
        int i ;
        int max=0,min=0;
        int mid=0;


        for(i=0;;i++){
            System.out.print("请输入一个数(输入0结束)：");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if(num ==0){
                break;
            }
            if(mid==0){
                max =num;
                min =num;
                mid++;
            }
            if(num> max){
                max =num;
                mid++;
            }


            if(num<min){
                min=num;
                mid++;

            }


        }
        System.out.println("最大值为："+max+"最小值为："+ min);
        }

    }

