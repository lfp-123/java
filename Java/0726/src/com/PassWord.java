package com;

/**
 * @author asus
 * @date 2019/7/26 15:33
 */
public class PassWord  {

    String Username;
    String password;

    public boolean login(){
        return  "admin".equals(Username)&&"123".equals(password);
    }

    public  boolean  show(String newpassword1,String newpassword2){
        if(!newpassword1.equals(newpassword2)){
            System.out.println("您两次输入的密码不正确!请重新输入");
            System.out.println();
            return  true;
        }else{
            System.out.println("用户名："+Username+"修改密码成功，新的密码为："+newpassword1);
            System.out.println();
            return  false;
            }
        }

    }




