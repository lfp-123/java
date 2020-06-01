package com.seencen.morning.ExtendsTest;

public final class Bus extends MotoVehicle {

    private int seatCount;// 座位数

    public Bus(String no, int seatCount) {
        setNo(no);
        setSeatCount(seatCount);
    }
@Override
    public double calcPrice(int days) {
        int price = Cons.BUS_JB_PRICE;
        if(this.getSeatCount() > 16) {
            price = Cons.BUS_JL_PRICE;
        }
        return price * days;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
