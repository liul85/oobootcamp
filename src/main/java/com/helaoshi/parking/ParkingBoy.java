package com.helaoshi.parking;

import java.util.List;
import java.util.Optional;

/**
 * Created by liangliu on 6/9/15.
 */
public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {

        this.parkingLots = parkingLots;
    }

    public ParkingLot park(Car car) {
        Optional<ParkingLot> lotOptional = parkingLots.stream().filter(parkinglot -> parkinglot.canPark()).findFirst();
        if (lotOptional.isPresent()) {
            ParkingLot parkingLot = lotOptional.get();
            parkingLot.park(car);
            return parkingLot;
        }
        return null;
    }

    public Car get(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.fetch(car) != null) {
                return car;
            }
        }
        return null;
    }

    public boolean canPark() {
        Optional<ParkingLot> lotOptional = parkingLots.stream().filter(parkinglot -> parkinglot.canPark()).findFirst();
        return lotOptional.isPresent();
    }
}
