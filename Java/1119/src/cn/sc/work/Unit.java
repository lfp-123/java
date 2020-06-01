package cn.sc.work;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Unit
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 15:42
 */
public class Unit {
    static  Scanner input =  new Scanner(System.in);
    static  List<User>  users = new ArrayList();
    static  User user;
    static  ObjectFile ob =  new ObjectFile();

    public static void  show(){
        System.out.println("欢迎进入注册登陆系统");
        System.out.println("1，注册");
        System.out.println("2，登陆");
        System.out.println("3，退出");
        System.out.print("请选择：");
    }
    public static  void Registered(){
        /*需要反序列化

         */
        System.out.print("请输入你注册的id:");
        int id = input.nextInt();
        System.out.print("请输入你要注册的账号:");
        String name = input.next();
        System.out.print("请输入你注册的密码:");
        String password = input.next();
        System.out.print("请输入你要注册的角色:");
        String role = input.next();
        User user = new User(id, name, password, role);
        //细节

        System.out.println("恭喜你注册成功");
        setList(user);
        //注册完后，序列化进硬盘

    }
    public static boolean Login(){
        //登陆前反序列化进程序读取
        getList();
        System.out.print("请输入你的账号：");
        String next = input.next();
        System.out.print("请输入你的密码：");
        String password = input.next();
        for (User u : users) {
            if(!next.equals(u.getUsername())&&password.equals(u.getPassword())){
            }else {
                user= u;
                return  true; }
        }
        return  false;
    }
    public static  void secondShow(){
        System.out.println("登陆成功！！");
        System.out.println("1,展示用户列表");
        System.out.println("2,删除用户");
        System.out.println("3,修改用户");
        System.out.println("请选择：");
        int i = input.nextInt();
        switch (i){
            case 1:
                Iterator<User> its = users.iterator();
                System.out.println("id\t账号\t身份");
                while (its.hasNext()){
                    User xiaouser = its.next();
                    Integer id = xiaouser.getId();
                    String username = xiaouser.getUsername();
                    String role = xiaouser.getRole();
                    System.out.println(id+"\t"+username+"\t"+role);
                }
                break;
            case 2:
                if("超级管理员".equals(user.getRole())||"管理员".equals(user.getRole())){
                    System.out.println("请输入你要删除的用户：");
                    String name = input.next();
                    for (int z = 0; z < users.size(); z++) {
                        if(name.equals(users.get(z).getUsername())){
                            users.remove(z);
                            System.out.println("删除成功！");
                            break;
                        }
                    }
                    //序列化更新数据
                }else {
                    System.out.println("对不起您的权限不够，无法做次操作！");
                }

                break;
            case 3:
                if("超级管理员".equals(user.getRole())||"管理员".equals(user.getRole())){
                    System.out.println("请输入你要修改的用户：");
                    String name = input.next();
                    for (int j = 0; j < users.size(); j++) {
                        if(name.equals(users.get(j).getUsername())){
                            System.out.println("请输入您的新密码：");
                            String newpassword = input.next();
                            users.get(j).setPassword(newpassword);
                            System.out.println("修改成功！");
                            break;
                        }else {
                            System.out.println("用户不存在！");
                            break;
                        }
                    }
                    //序列化更新数据
                }else {
                    System.out.println("对不起您的权限不够，无法做次操作！");
                }break;
            default:
        }
    }
    //反序列化方法
    public static void getList() {
        ArrayList userlist = ob.objectInputFile();
        users = userlist;
    }
    //序列化方法
    public static void setList(User user) {
            users.add(user);
            ob.objectOutputFile(users);
    }

    public static boolean checkUsername(String name){
     if(users.size()!=0 &&users!=null){

     }
            return  true;
    }

}
