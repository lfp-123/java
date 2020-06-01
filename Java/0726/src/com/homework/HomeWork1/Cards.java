package com.homework.HomeWork1;

/**
 * @author asus
 * @date 2019/7/26 19:01
 */
public class Cards {
    int score;
    String card ;

    public void show(){
        if((card.equals("金卡")&&score>1000)|| (card.equals("普卡")&&score>5000)){

            System.out.println("积分回馈500");
        }

    }
}
