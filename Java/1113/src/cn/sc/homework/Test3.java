package cn.sc.homework;

/**
 * @author ${林锋鹏}
 * @Title: Test3
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/13 17:52
 */
public class Test3 {
    public static void main(String[] args) {

//        StringBuffer nums = new StringBuffer();
//        nums.append("[-1,-2,1,10,4,5,8]");
//        nums.replace(0, 1, "");
//        nums.replace(nums.length() - 1, nums.length() , "");

        String num ="[-79,-2,1,10,4,5,8]";
        String nums = num.replaceAll("[\\[\\]]", "");
        System.out.println(nums);

       String[] split = nums.split(",");
       int max =0;
       int[] ints = new int[split.length];
       for (int i = 0; i <split.length ; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        for (int i = 0; i < ints.length; i++) {
            if(ints[i]>max){
                max= ints[i];
            }
        }
        System.out.println("最大值："+max);
   }

}
