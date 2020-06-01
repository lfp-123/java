/**
 * @author asus
 * @date 2019/7/22 18:28
 */
public class HomeWork1 {
    public static void main(String[] args) {
        int s=0;
        for(int i = 0;i<=100;i++){
            if(!(i%3==0)){
                s= s+i;
            }
        }
        System.out.println(s);
    }
}
