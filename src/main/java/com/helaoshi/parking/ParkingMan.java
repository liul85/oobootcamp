package com.helaoshi.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by liangliu on 6/9/15.
 */
public class ParkingMan {

    protected List<ParkingLot> parkingLots = new ArrayList<>();
    protected ParkingStrategy parkingStrategy;
    protected String name;

    public ParkingMan(String name, ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
        this.name = name;
    }

    public ParkingMan(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingMan(String name, List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
        this.parkingLots = parkingLots;
        this.name = name;
    }

    public void addParkingLot(ParkingLot lot) {
        this.parkingLots.add(lot);
    }

    public Car get(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.fetch(car) != null) {
                return car;
            }
        }
        return null;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public boolean canPark() {
        Optional<ParkingLot> lotOptional = parkingLots.stream().filter(parkinglot -> parkinglot.canPark()).findFirst();
        return lotOptional.isPresent();
    }

    public ParkingLot park(Car car) {
        Optional<ParkingLot> lotOptional = parkingStrategy.findParkingLot(parkingLots);
        if (lotOptional.isPresent()) {
            ParkingLot parkingLot = lotOptional.get();
            parkingLot.park(car);
            return parkingLot;
        }
        return null;
    }

    public String report() {
        String typeString = String.format("    %s:%s", getType(), getName());
        StringBuilder sb = getParkingLotsString();
        return String.format("%s\n%s", typeString, sb.toString());
    }

    protected String getType() {
        String cls = this.getClass().toString();
        int index = cls.lastIndexOf(".");
        return cls.substring(index + 1);
    }

    protected StringBuilder getParkingLotsString() {
        StringBuilder sb = new StringBuilder();
        this.parkingLots.stream().forEach(lot -> sb.append(String.format("    %s %s", "ParkingLot", lot.getName()).concat("\n")));
        return sb;
    }

    public String getName() {
        return name;
    }

}
