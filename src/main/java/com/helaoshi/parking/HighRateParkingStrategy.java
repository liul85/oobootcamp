package com.helaoshi.parking;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class HighRateParkingStrategy implements ParkingStrategy {

    @Override
    public Optional<ParkingLot> findParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().filter(lot -> lot.canPark()).sorted(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                if (o1.getFreeRate() == o2.getFreeRate()) {
                    return o2.getCapacity() - o1.getCapacity();
                }
                return o2.getFreeRate() - o1.getFreeRate();
            }
        }).findFirst();
    }
}
