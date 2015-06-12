package com.helaoshi.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by liangliu on 6/9/15.
 */
public class NormalParkingStrategyTest {

    private ParkingMan boy;

    @Before
    public void setUp() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);

        boy = new ParkingMan(parkingLots, new NormalParkingStrategy());
    }

    @Test
    public void testParkAndFetchCar() throws Exception {
        assertThat(boy.canPark(), is(true));
        Car car1 = new Car();
        boy.park(car1);
        assertThat(boy.get(car1), is(car1));

        boy.park(new Car());
        boy.park(new Car());
        boy.park(new Car());
        Car car2 = new Car();
        boy.park(car2);
        assertThat(car2, is(boy.get(car2)));

        boy.park(new Car());
        boy.park(new Car());
        Car car3 = new Car();
        assertThat(boy.canPark(), is(false));
        assertThat(boy.get(car3), nullValue());

        assertThat(boy.park(new Car()), is(nullValue()));
    }
}
