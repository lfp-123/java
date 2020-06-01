package cn.sc.quickHit;

import java.util.Scanner;

/**
 * @author ${林锋鹏}
 * @Title: Player
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/14 19:41
 */
public class Player {
    static Scanner input = new Scanner(System.in);

    /*
        玩家当前级别号（levelNo）
        玩家当前级别积分（currScore）
        当前级别开始时间（startTime）
        当前级别已用时间（elapsedTime）
*/
    public Player() {

    }

    private int levelNo;
    private int curScore;
    private long startTime;
    private long elapseTime;

    public Player(int levelNo, int curScore, long startTime, long elapseTime) {
        this.levelNo = levelNo;
        this.curScore = curScore;
        this.startTime = startTime;
        this.elapseTime = elapseTime;
    }

    public long getElapseTime() {

        return elapseTime;
    }

    public void setElapseTime(long elapseTime) {
        this.elapseTime = elapseTime;
    }

    public int getLevelNo() {

        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getCurScore() {
        return curScore;
    }

    public void setCurScore(int curScore) {
        this.curScore = curScore;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    //玩游戏
    public void play() {
        Game game = new Game(this);//this代表已经创建了的对象引用player
        //外层循环，循环一次级别进一级
        for (int i = 0; i < LevelParam.levels.length; i++) {
            this.levelNo++;
            //晋级后，记时清零，积分清零
            this.startTime = System.currentTimeMillis();
            this.setCurScore(0);
            //每一关都要打多少次字符串，进行比较。
            for (int j = 0; j < LevelParam.levels[levelNo - 1].getStrTime(); j++) {
                String out = game.printStr();
                System.out.println(out);
                String in = input.nextLine();
                game.printResult(out, in);
            }

        }
    }
}


