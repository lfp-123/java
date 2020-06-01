/**
 * @author asus
 * @date 2019/7/22 19:27
 */
public class HomeWork8 {
    public static void main(String[] args) {
        int man;
        int woman;
        int child;
//        for(man=0;man<=30;man++){
//            for(woman=0;woman<=30;woman++){
//                for(child=0;child<=30;child++){
//                    if((man*3+woman*2+child*1==50)&&(man+woman+child==30)){
//                        System.out.println("男人："+man+"女人："+woman+"小孩："+child);
//                    }
//                }
//            }
//        }
// 第一次 能暴力直接暴力了。。
//        for(man=0;man<=10;man++){
//            for(woman=0;woman<=20;woman++){
//                    child = 30-woman-man;
//                if((man*3+woman*2+child*1==50)&&(man+woman+child==30)){
//                    System.out.println("男人："+man+"女人："+woman+"小孩："+child);
//                }
//
//            }
//        }
// 这个也还是太暴力
         for(man=0;man<=10;man++){
                woman = 20- 2*man;
                child = 30-woman-man;
              // if(man*3+woman*2+child*1==50)
             System.out.println("男人："+man+"女人："+woman+"小孩："+child);



       }





    }

}
