

import java.util.Scanner;

public class PrizeWinner {
    public static String[][] usersDB = new String[5][];
    // 记录登录人的usersDB下标,没登录为-1
    public static int loginIndex = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            showMenu();// 显示菜单
            System.out.print("请选择菜单:");
            int menu = input.nextInt();
            if (menu == 1) {
                System.out.println("[奖客富翁系统 > 注册]");
                System.out.println("请填写个人注册信息:");
                System.out.print("用户名:");
                String username = input.next();
                System.out.print("密码:");
                String password = input.next();
                register(username, password);
            } else if (menu == 2) {
                System.out.println("[奖客富翁系统 > 登录]");
                System.out.print("请输入用户名：");
                String username = input.next();
                System.out.print("请输入密码：");
                String password = input.next();
                login(username, password);
            } else if (menu == 3) {
                System.out.println("[奖客富翁系统 > 抽奖]");
                if(loginIndex == -1) {
                    System.out.println("请先登录！");
                    continue;
                }
                System.out.print("请输入您的卡号:");
                String cardNum = input.next();
                luckdraw(cardNum);
            }
            else {
                System.out.println("菜单选择错误!");
                continue;
            }
            System.out.print("是否继续（y/n）:");
            if (input.next().equals("n")) break;
        }
        System.out.println("系统退出,谢谢使用!");
    }

    public static void showMenu() {
        System.out.println("*****奖客富翁系统欢迎进入*****");
        System.out.println("\t\t1. 注册");
        System.out.println("\t\t2. 登录");
        System.out.println("\t\t3. 抽奖");
        System.out.println("******************************");
    }
    public static void register(String username, String password) {
        // 判断系统是否已经注册满了,是否已经有重复的用户名
        int i = 0;
        boolean flag = false; // 假设DB中是不存在的
        for (; i < usersDB.length; i++) {
            // 找出可以存储的位置，如果都不会null，那么i最后会等于数组的长度值
            if (usersDB[i] == null) break;
            // 不为空就取出里面的三个长度的数组
            // 把DB中每一个用户名拿出来和输入的对比
            String[] users = usersDB[i];
            if (users[0].equals(username)) {
                // 记录要注册的账户名已经存在
                flag = true;
                break;// 跳出循环，因为后续无序再比
            }
        }
        // 如果索引值等于数组长度，代表注册满了，没有位置可用
        if(i == usersDB.length) {
            System.out.println("注册失败，系统已经注册满额!");
        } else if(flag) {
            System.out.println("要注册的用户名已经存在，请更换后重试！");
        } else {
            // 执行到这里代表可以注册
            String[] newUsers = new String[3];
            newUsers[0] = username;
            newUsers[1] = password;
            newUsers[2] = "" + ((int)(Math.random() * 9000) + 1000);
            // 保存到db
            usersDB[i] = newUsers;
            System.out.println("\n注册成功，请记好您的会员卡号");
            System.out.println("用户名\t\t密码\t\t会员卡号");
            System.out.println(newUsers[0]+"\t\t"+newUsers[1]+"\t\t" + newUsers[2]);
        }
    }
    public static void login(String username, String password) {
        for (int i = 0; i < usersDB.length; i++) {
            // 如果数据库为null，代表没有注册，或者没有匹配到
            if(usersDB[i] == null) break;
            String[] users = usersDB[i];
            // 如果用户名和密码等于当前循环到的这个位置的用户名和密码
            if(users[0].equals(username) && users[1].equals(password)) {
                // 记录当前登录匹配的位置
                loginIndex = i;
                System.out.println("欢迎您：" + username);
                // 不加return会执行下面的用户名或密码错误。
                return;
            }
        }
        System.out.println("用户名或密码错误!");
    }

    public static void luckdraw(String cardNum) {
        // 首先校验是否为登录的人的卡号
        if(cardNum.equals(usersDB[loginIndex][2])) {
            // 抽取3位不重复的会员
            int luckIndex1 = 0, luckIndex2 = 0, luckIndex3 = 0;
            // 声明3个变量存储随机出的幸运会员卡号
            String luckCard1, luckCard2, luckCard3;
            // 不能抽到相同的幸运会员位置
            while(luckIndex1 == luckIndex2 ||
                    luckIndex1 == luckIndex3 ||
                    luckIndex2 == luckIndex3) {
                luckIndex1 = (int) (Math.random() * usersDB.length);
                luckIndex2 = (int) (Math.random() * usersDB.length);
                luckIndex3 = (int) (Math.random() * usersDB.length);
            }
            // 有可能抽到的位置为null,所以转换为""
            // null是不能使用的，会报错
            luckCard1 = usersDB[luckIndex1] == null ? "" : usersDB[luckIndex1][2];
            luckCard2 = usersDB[luckIndex2] == null ? "" : usersDB[luckIndex2][2];
            luckCard3 = usersDB[luckIndex3] == null ? "" : usersDB[luckIndex3][2];
            System.out.println("本日的幸运会员为:" + luckCard1 + "\t" + luckCard2 + "\t" + luckCard3);
            if(cardNum.equals(luckCard1) || cardNum.equals(luckCard2) || cardNum.equals(luckCard3)) {
                System.out.println("恭喜，中奖了!");
            } else {
                System.out.println("抱歉！您不是本日的幸运会员！");
            }
        } else {
            System.out.println("卡号输入错误，请检查您登录的会员卡号是否录入正确！");
        }
    }
}
