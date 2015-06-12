package com.helaoshi.parking;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by liangliu on 6/12/15.
 */
public class MostAvailableParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingLot> findParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().filter(lot -> lot.canPark()).sorted(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o2.getRemainSize() - o1.getRemainSize();
            }
        }).findFirst();
    }
}
