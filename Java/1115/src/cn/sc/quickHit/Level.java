package cn.sc.quickHit;

/**
 * @author ${林锋鹏}
 * @Title: Level
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/14 19:47
 */
public class Level {
    /*
        各级别号（levelNo）
        各级别一次输出字符串的长度（strLength）
        各级别输出字符串的次数（strTimes）
        各级别闯关的时间限制（timeLimit）
        各级别正确输入一次的得分（perScore）

        */
    private int levelNo;
    private int strLength;
    private int strTime;
    private int timeLimit;
    private int perScore;

    public Level(int levelNo, int strLength, int strTime, int timeLimit, int perScore) {
        this.levelNo = levelNo;
        this.strLength = strLength;
        this.strTime = strTime;
        this.timeLimit = timeLimit;
        this.perScore = perScore;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getStrLength() {
        return strLength;
    }

    public void setStrLength(int strLength) {
        this.strLength = strLength;
    }

    public int getStrTime() {
        return strTime;
    }

    public void setStrTime(int strTime) {
        this.strTime = strTime;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getPerScore() {
        return perScore;
    }

    public void setPerScore(int perScore) {
        this.perScore = perScore;
    }


}
