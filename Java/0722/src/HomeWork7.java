/**
 * @author asus
 * @date 2019/7/22 19:19
 */
public class HomeWork7 {
    public static void main(String[] args) {
//        int   chicken;
//        int     rabbit;
//        int     top = 35;
//        int     foot = 94;
//
//        for(chicken=0;chicken<=top;chicken++){
//            for(rabbit=0;rabbit<=top;rabbit++){
//                if(chicken+rabbit==top&&(2*chicken+4*rabbit==foot)){
//                    System.out.println("鸡："+chicken);
//                    System.out.println("兔："+rabbit);
//                }
//
//
//
//            }
//        }
        //暴力算法，太伤了。


        int j = 0;
        for (int i = 0; i <= 35; i++) {
            j = 35 - i;
            if (2 * i + 4 * j == 94) {
                System.out.println("鸡："+i);
                System.out.println("兔："+j);
            }
        }


    }

}


