package com.homeworks.work2;

/**
 * @author asus
 * @Title: SoftEngineer
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 19:33
 */
public class SoftEngineer  implements BizAgent,ProGrammer {
    private String name;
    SoftEngineer(String name){
        setName(name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void giveBizSpeech() {
        System.out.println("我会讲业务！");
    }

    @Override
    public void writeProGrammer() {
        System.out.println("我会写代码！");
    }

    @Override
    public String getName(String name) {
        return name;
    }


    public void introduce() {
        System.out.println("我是一名程序员，名字是" + getName());
    }
}
