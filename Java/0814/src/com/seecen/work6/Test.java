package com.seecen.work6;

/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/14 17:17
 */
public class Test {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        WarehouseThread zgl = new WarehouseThread("诸葛亮", warehouse, 1);
        WarehouseThread zy = new WarehouseThread("周瑜", warehouse, 1);
        WarehouseThread gj = new WarehouseThread("郭嘉", warehouse, 1);
        WarehouseThread smy = new WarehouseThread("司马懿", warehouse, 1);

        WarehouseThread gy = new WarehouseThread("关羽", warehouse, 2);
        WarehouseThread zf = new WarehouseThread("张飞", warehouse, 2);
        WarehouseThread zzl = new WarehouseThread("赵云", warehouse, 2);
        WarehouseThread mc = new WarehouseThread("马超", warehouse, 2);
        WarehouseThread hz = new WarehouseThread("黄忠", warehouse, 2);
        zgl.start();
        zy.start();
        gj.start();
        gy.start();
        zf.start();
        zzl.start();
        mc.start();
        hz.start();
    }




}
