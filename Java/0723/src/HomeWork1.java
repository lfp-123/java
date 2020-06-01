import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;

/**
 * @author asus
 * @date 2019/7/23 18:27
 */
public class HomeWork1 {
    public static void main(String[] args) {
        char[] str =new char[]{'a','c','u','b','e','p','f','z'};
        System.out.print("原字符排列：");
        for (int i = 0; i <str.length ; i++) {
            System.out.print(str[i]+"\t");

        }
        System.out.println();
        Arrays.sort(str);
        System.out.print("升序排列后：");
        for (int i = 0; i <str.length ; i++) {
            System.out.print(str[i]+"\t");

        }
        System.out.println();
        System.out.print("逆序排列后：");
        for (int i= str.length-1;i>=0;i--){
            System.out.print(str[i]+"\t");
        }

    }


}
