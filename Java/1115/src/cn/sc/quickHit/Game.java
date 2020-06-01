package cn.sc.quickHit;

import java.util.Random;

/**
 * @author ${林锋鹏}
 * @Title: Game
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/14 19:43
 */
public class Game {
    private  Player player;

    public Game(Player player) {
        this.player = player;
    }

    public Player getPlayer() {

        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }



    /*
        输出字符串，返回字符串用于和玩家输入比较。
         */
    public String printStr(){
        int strLength=0;
        //获取当前选手的关卡
        int levelNo = this.player.getLevelNo();
        for (int i = 0; i < LevelParam.levels.length; i++) {
            if(levelNo==LevelParam.levels[i].getLevelNo()){
                strLength = LevelParam.levels[i].getStrLength();
            }
        }
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        // 通过循环生成要输出的字符串
        for (int i = 0; i < strLength; i++) {
            int rand = random.nextInt(strLength); // 产生随机数
            // 根据随机数拼接字符串
            switch (rand) {
                case 0:
                    buffer.append(">");
                    break;
                case 1:
                    buffer.append("<");
                    break;
                case 2:
                    buffer.append("a");
                    break;
                case 3:
                    buffer.append("b");
                    break;
                case 4:
                    buffer.append("c");
                    break;
                case 5:
                    buffer.append("z");
                    break;
                default:
                    buffer.append("d");
                    break;

            }
        }

        return  buffer+"";
    }

    /*
    比较游戏输出out和玩家输入in，根据比较结果输出相应信息
     */
    public void printResult(String out,String in){

        if (out.equals(in)) {
            long currentTimeMillis = System.currentTimeMillis();
            if((currentTimeMillis-this.player.getStartTime())/1000 >
                    LevelParam.levels[this.player.getLevelNo()-1].getTimeLimit()){
                System.out.println("对不起，你已经超时！，游戏结束！！");
                System.exit(0);

            }else {
                /*
               计算当前玩家的所用时间
                 */
               this.player.setElapseTime((currentTimeMillis-this.player.getStartTime())/1000);
               /*
               计算当前玩家的所的积分
                */
               this.player.setCurScore(this.player.getCurScore()+
                       LevelParam.levels[this.player.getLevelNo()-1].getPerScore());

                System.out.println("输入正确，您的积分是："+this.player.getCurScore()+
                        "您的级别为："+this.player.getLevelNo()+
                        "已用时间："+this.player.getElapseTime()+"秒");
            }

        }else{
            System.out.println("输入错误，退出程序！");
            System.exit(0);
        }


    }
}
