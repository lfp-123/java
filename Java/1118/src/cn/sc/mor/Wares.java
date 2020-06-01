package cn.sc.mor;

/**
 * @author ${林锋鹏}
 * @Title: Wares
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/18 11:07
 */
public class Wares {
    private  String name; //商品名称
    private  Integer num; //商品数量
    private  Integer maxNum; //商品最大库存

    public Wares(String name, Integer num, Integer maxNum) {
        this.name = name;
        this.num = num;
        this.maxNum = maxNum;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }
}
