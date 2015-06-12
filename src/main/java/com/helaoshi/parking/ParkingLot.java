package com.helaoshi.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangliu on 6/9/15.
 */
public class ParkingLot {

    private int capacity;

    private List<Car> cars = new ArrayList<>();
    private String name;

    public ParkingLot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new ArrayList<>(capacity);
    }

    public boolean canPark() {
        return cars.size() < capacity;
    }

    public int getRemainSize() {
        return capacity - cars.size();
    }

    public int getFreeRate() {
        return (capacity - cars.size()) / capacity;
    }

    public Car park(Car car) {
        if (cars.size() < capacity) {
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

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                ", cars=" + cars +
                '}';
    }

    public String getName() {
        return this.name;
    }
}
