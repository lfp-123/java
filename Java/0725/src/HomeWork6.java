

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * @author asus
 * @date 2019/7/25 20:37
 */
public class HomeWork6 {
    public static void main(String[] args) {

        for ( int cock =0;cock<=20;cock++){
                     for ( int hen =0; hen <25 ; hen++) {
                         int chick =100 -hen- cock;
                             if(cock*15+hen*3+chick*1==300){
                                 System.out.println("公鸡："+cock+"母鸡："+hen+"小鸡："+chick);
                             }
                         }
                     }
                 }
}


