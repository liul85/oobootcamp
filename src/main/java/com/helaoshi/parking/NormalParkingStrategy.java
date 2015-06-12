package com.helaoshi.parking;

import java.util.List;
import java.util.Optional;

/**
 * Created by liangliu on 6/12/15.
 */
public class NormalParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingLot> findParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().filter(parkinglot -> parkinglot.canPark()).findFirst();    }
}
