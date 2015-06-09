package com.helaoshi.parking;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liangliu on 6/9/15.
 */
public class ParkingGirl {
    private List<ParkingLot> parkingLots;

    public ParkingGirl(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingLot park(Car car) {
        List<ParkingLot> collect = parkingLots.stream().filter(lot -> lot.canPark()).sorted(new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot o1, ParkingLot o2) {
                return o2.getRemainSize() - o1.getRemainSize();
            }
        }).collect(Collectors.toList());

        ParkingLot parkingLot = collect.get(0);
        parkingLot.park(car);
        return parkingLot;
    }
}
