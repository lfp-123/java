public class Text2 {
    public static void main(String[] args) {
        double discount = 0.8;
        int a=490, b=570,c=320;
        double allMoney = a+b+c;
        double disAllMoney = allMoney*discount;
        double Money= 1500;
       int integral=(int)(disAllMoney*0.03);
        System.out.println("----------------购物清单--------------------");
        System.out.println("购买物品\t单价\t\t个数\t\t金额");
        System.out.println("T恤\t\t"+"￥"+a+"\t2"+"\t\t￥"+2*a);
        System.out.println("网球鞋\t"+"￥"+b+"\t1"+"\t\t￥"+1*b);
        System.out.println("网球拍\t"+"￥"+c+"\t2"+"\t\t￥"+1*c);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("折扣："+discount);
        System.out.println("消费总金额："+allMoney*discount);
        System.out.println("实际交钱："+Money);
        System.out.println("找钱："+(Money-allMoney*discount));
        System.out.println("本次消费的总积分是："+integral);
    }
}
