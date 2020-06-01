/**
 * @author asus
 * @date 2019/7/22 11:08
 */
public class Text3 {
    public static void main(String[] args) {

        double people = 80000;
        int year = 2006;
        double rate = 1.25;
        int newpeople = 200000;
    while (people<newpeople){
        people = people*rate;
       year++;

    }
        System.out.println(year);
    }

}
