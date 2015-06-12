package com.helaoshi.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by liangliu on 6/12/15.
 */
public class Manager extends ParkingMan{

    private List<ParkingMan> parkingMans = new ArrayList<>();

    public Manager(String name, List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        super(name, parkingLots, parkingStrategy);
    }

    public void addParkingMan(ParkingMan parkingMan) {
        parkingMans.add(parkingMan);
    }

    @Override
    public Car get(Car car) {
        List<ParkingLot> allLots = new ArrayList<>();
        parkingMans.stream().forEach(man -> allLots.addAll(man.getParkingLots()));
        allLots.addAll(this.getParkingLots());
        for (ParkingLot parkingLot : allLots) {
            if (parkingLot.fetch(car) != null) {
                return car;
            }
        }
        return null;
    }

    public ParkingLot assign(Car car) {
        Optional<ParkingMan> first = parkingMans.stream()
                .filter(entry -> entry.canPark())
                .findFirst();
        if (first.isPresent()) {
            return first.get().park(car);
        }
        return null;
    }

    public void print() {
        String typeString = String.format("%s:%s", getType(), getName());
        System.out.println(typeString);
        parkingMans.stream().forEach(man -> System.out.println(man.report()));
        System.out.print(getParkingLotsString());
    }
}
