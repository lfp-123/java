package com.homeworks.work1;

/**
 * @author asus
 * @Title: HomeLetterlmpl
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 19:08
 */
public class HomeLetterlmpl implements HomeLetter {
    @Override
    public String writeTitle() {
        return  "亲爱的爸爸、妈妈：";
    }

    @Override
    public String writeHello() {
        return "你们好吗";
    }

    @Override
    public String writeBody() {
        return "\t我在这里挺好的，\n"+
                "\t我会努力学习的，已经学到Java OOP\n" +"\t您二老保重身体啊";
    }

    @Override
    public String writeGreeting() {
        return "此致\n"+"\t敬礼";
    }

    @Override
    public String writeSelft() {
        return "\t\t\t周杰 \n\t\t\t2010年6月1号";
    }
}
