/**
 * @author asus
 * @date 2019/7/22 11:21
 */
public class Text4 {


    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int i =0;
        int s=0;
        while (i<=100){

                s= s+i;
                i= i+2;

        }
        System.out.println(s);
        long endTime = System.nanoTime();
        System.out.println(endTime-startTime);
    }
}
