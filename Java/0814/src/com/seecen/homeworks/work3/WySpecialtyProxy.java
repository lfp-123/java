package com.seecen.homeworks.work3;

import javax.swing.*;
import java.awt.*;

/**
 * @author ${林锋鹏}
 * @Title: test
 * @ProjectName Java
 * @date 2019/8/14 20:01
 */
public class WySpecialtyProxy
{
    public static void main(String[] args)
    {
        SgProxy proxy=new SgProxy();
        proxy.display();
    }
}
//抽象主题：特产
interface Specialty
{
    void display();
}
//真实主题：婺源特产
class WySpecialty extends JFrame implements Specialty
{
    private static final long serialVersionUID=1L;
    public WySpecialty()
    {
        super("韶关代理婺源特产测试");
        this.setLayout(new GridLayout(1,1));
        JLabel l1=new JLabel(new ImageIcon("src/com/seecen/homeworks/work3"));
        this.add(l1);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void display()
    {
        this.setVisible(true);
    }
}
//代理：韶关代理
class SgProxy implements Specialty
{
    private WySpecialty realSubject=new WySpecialty();
    @Override
    public void display()
    {
        preRequest();
        realSubject.display();
        postRequest();
    }
    public void preRequest()
    {
        System.out.println("韶关代理婺源特产开始。");
    }
    public void postRequest()
    {
        System.out.println("韶关代理婺源特产结束。");
    }
}
