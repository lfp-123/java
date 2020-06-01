package seecen;

/**
 * @author Shaw
 * @date 2019/7/25 16:19
 */
public class Demo07 {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + (i * j) + " ");
            }
            System.out.println();
        }
    }
}
