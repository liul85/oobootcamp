package com.helaoshi.parking;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by liangliu on 6/9/15.
 */
public class ParkingLotTest {

    private ParkingLot parkingLot;
    private Car car;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(1);
        car = new Car();

    }

    @Test
    public void testCanParkingCar() throws Exception {
        assertThat(parkingLot.canPark(), is(true));
    }

    @Test
    public void testCanNotParkCarWhenParkingLotIsFull() throws Exception {
        parkingLot.park(car);
        assertThat(parkingLot.canPark(), is(false));
    }

    @Test
    public void testCanFetchCar() throws Exception {
        car = parkingLot.park(car);
        assertThat(parkingLot.fetch(car), is(car));
        assertThat(parkingLot.fetch(new Car()), is(nullValue()));
        assertThat(parkingLot.canPark(), is(true));
    }


}
