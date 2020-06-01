/**
 * @author asus
 * @date 2019/7/29 10:57
 */
public class Test2 {
    int java;
    int C;
    int Db;
    public int allScore(int a, int b,int c ){
        this.java =a;
        this.C =b;
        this.Db =c;

        return  this.java+this.C+this.Db;
    }
    public  double avg(int a,int b,int c){
        this.java =a;
        this.C =b;
        this.Db =c;
        double  s =(this.java+this.C+this.Db)/3;
        return s;
    }
}
