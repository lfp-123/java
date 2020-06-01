package src;

public class Test2 {
    public static void main(String[] args) {
        double[] zhangHeight = new double[]{170, 60};
        double[] liHeight = zhangHeight;
        System.out.println("zhang" + zhangHeight[0] + ":" + zhangHeight[1]);
        System.out.println("li" + liHeight[0] + ":" + liHeight[1]);
        liHeight = new double[]{170, 60};
        liHeight[0] += 10;
        liHeight[1] += 5;
        System.out.println("zhang" + zhangHeight[0] + ":" + zhangHeight[1]);
        System.out.println("li" + liHeight[0] + ":" + liHeight[1]);

    }
}
