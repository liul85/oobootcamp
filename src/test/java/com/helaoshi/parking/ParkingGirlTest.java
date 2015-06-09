package com.helaoshi.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by liangliu on 6/9/15.
 */
public class ParkingGirlTest {
    ParkingGirl girl;
    ParkingLot parkingLot1 = new ParkingLot(3);
    ParkingLot parkingLot2 = new ParkingLot(1);
    ParkingLot parkingLot3 = new ParkingLot(2);

    @Before
    public void setUp() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);

        girl = new ParkingGirl(parkingLots);
    }

    @Test
    public void testShouldParkCarToTheLotWhichHasMoreFreeLots() throws Exception {
        ParkingLot lot = girl.park(new Car());
        assertThat(lot, is(parkingLot1));

        girl.park(new Car());

        lot = girl.park(new Car());
        assertThat(lot, is(parkingLot3));
    }
}
