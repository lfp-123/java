package com.seecen.work1;


public class MyList{

//    private   String[] nums = new String[0];
//    public void add(String s){
//        String[] dest = new String[nums.length+1];
//        System.arraycopy(nums,0,dest,0,nums.length);
//
//        dest[dest.length-1]= s;
//            nums = dest;
//
//    }
    private   Object[] nums = new Object[0];
    public void add(Object s){
        Object[] dest = new Object[nums.length+1];
        System.arraycopy(nums,0,dest,0,nums.length);

        dest[dest.length-1]= s;
            nums = dest;

    }


    public void show(){
        for (Object a:nums) {
            System.out.println(a);
        }
    }
}
