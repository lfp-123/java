package com.homeworks.work4;

/**
 * @author asus
 * @Title: Test4
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 19:50
 */
public class Test4  {
    public static void main(String[] args) {

        Age age = new Age();
        try {
            age.setAge(800);
        }catch (Exception e){
           e.printStackTrace();
        //    System.err.println(e.getMessage());
        }


    }
}
