import java.util.Arrays;

/**
 * Created by Enzo Cotter on 2019/7/29.
 */
public class StudentsBiz {
    String[] names=new String[5];
    public void inputNames(){
        names[0]="迪丽热巴";
        names[1]="杨幂";
        names[2]="东方不败";
        names[3]="古力娜扎";
        names[4]="欧阳锋";
    }
    public String[] getNames(){
        Arrays.sort(names);
        return names;
    }
}
