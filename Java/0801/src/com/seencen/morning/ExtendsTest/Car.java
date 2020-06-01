package com.seencen.morning.ExtendsTest;

public final class Car extends MotoVehicle {
    // 车型
    private String vehicleType;
    public Car(String no, String vehicleType) {
        setNo(no);
        setVehicleType(vehicleType);
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    @Override
    public double calcPrice(int days) {
        int price = 0;
        if(vehicleType.equals(Cons.CAR_BKSWGL8)) {
            price = Cons.CAR_BKSWGL8_PRICE;
        }
        if(vehicleType.equals(Cons.CAR_BMW550I)) {
            price = Cons.CAR_BMW550I_PRICE;
        }
        if(vehicleType.equals(Cons.CAR_BKLYDD)) {
            price = Cons.CAR_BKLYDD_PRICE;
        }
        return price * days;
    }
}
