
/**
 * @author asus
 * @date 2019/7/29 9:45
 */
public class Test1 {

    String color = "黄色";
    public void run(){
        System.out.println("正在跑！");
    }
    public String bark(){
        String round = "大声吼叫";
        return round;
    }
    public String getColor(){
        return color;
    }
    public String showLine(){
        return "这是一个"+getColor();
    }


}
