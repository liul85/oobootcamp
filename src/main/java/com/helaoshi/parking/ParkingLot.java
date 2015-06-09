package com.helaoshi.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangliu on 6/9/15.
 */
public class ParkingLot {

    private int size;

    private List<Car> cars;

    public ParkingLot(int size) {
        this.size = size;
        cars = new ArrayList<>(size);
    }

    public boolean canPark() {
        return cars.size() < size;
    }

    public Car park(Car car) {
        if (cars.size() < size) {
            cars.add(car);
            return car;
        }
        return null;
    }

    public Car fetch(Car car) {
        if (cars.contains(car)) {
            cars.remove(car);
            return car;
        }
        return null;
    }
}
