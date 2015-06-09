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
public class SmartBoyTest {
    SmartBoy smartBoy;
    ParkingLot parkingLot1 = new ParkingLot(8);
    ParkingLot parkingLot2 = new ParkingLot(4);
    ParkingLot parkingLot3 = new ParkingLot(7);

    @Before
    public void setUp() throws Exception {
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);

        smartBoy = new SmartBoy(parkingLots);
    }

    @Test
    public void testShouldParkToLotWhichHasMoreFreeRate() throws Exception {
        assertThat(smartBoy.park(new Car()), is(parkingLot1));
        assertThat(smartBoy.park(new Car()), is(parkingLot3));
        assertThat(smartBoy.park(new Car()), is(parkingLot2));
    }
}
