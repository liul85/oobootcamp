package com.helaoshi.parking;

import java.util.List;
import java.util.Optional;

/**
 * Created by liangliu on 6/9/15.
 */
public interface ParkingStrategy {
    Optional<ParkingLot> findParkingLot(List<ParkingLot> parkingLots);
}
