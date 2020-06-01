public class Text1 {
    public static void main(String[] args) {

        int day = 46;
        int week = 7;
        int newWeek;
        int newDay;


        newWeek = day / week;
        newDay = day % week;
        System.out.println("剩余的天数：" + newDay);
        System.out.println("周数：" + newWeek);

        System.out.println("------------------------------------------");
        double radius = 1.5;
        double area = Math.PI * radius * radius;
        System.out.println("圆的半径为：" + radius);
        System.out.println("圆的面积为：" + area);


    }
}