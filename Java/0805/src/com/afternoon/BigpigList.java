package com.afternoon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author 林锋鹏
 * @Date 2019/8/5 11:29
 * @Description
 */
public class BigpigList {
    private Object[] datas = {}; // 数据存储的数组
    private int size; // 记录数据的个数
    private static final int DEFAULT_CAPACITY = 10;// 默认长度为10

    public int size() {
        return this.size;
    }
    public void add(Object data) {

        kuorong(size + 1);
        datas[size++] = data; // 把数据放到这个位置
    }
    public Object get(int i) {
        if(i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return datas[i];
    }
    public void remove(int i) {
        if(i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        datas[i] = null;
        for (int j = i; j < size-1; j++) {
            datas[j] = datas[j + 1];
        }
        datas[--size] = null;
    }
    private void kuorong(int size) {
        // size大于数组长度,存不下了,要扩容了
        if(size > datas.length) {
            int newSize = datas.length == 0 ? DEFAULT_CAPACITY : datas.length + (datas.length >> 1);
            datas = Arrays.copyOf(datas, newSize);
            System.out.println("扩容了,当前长度为:" + datas.length);
        }
    }


}
