package com.helaoshi.parking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by liangliu on 6/12/15.
 */
public class BossParkingTest {

    private Manager manager;

    ParkingLot parkingLot1 = new ParkingLot("name1",2);
    ParkingLot parkingLot2 = new ParkingLot("name2",1);
    ParkingLot parkingLot3 = new ParkingLot("name3",5);

    ParkingLot parkingLot4 = new ParkingLot("name4",3);
    ParkingLot parkingLot5 = new ParkingLot("name5",6);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Before
    public void setUp() throws Exception {

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);

        manager = new Manager("manager", parkingLots, new NormalParkingStrategy());

        ParkingMan parkingMan = new ParkingMan("boy", new NormalParkingStrategy());
        parkingMan.addParkingLot(parkingLot4);
        manager.addParkingMan(parkingMan);

        parkingMan = new ParkingMan("girl", new MostAvailableParkingStrategy());
        parkingMan.addParkingLot(parkingLot5);
        manager.addParkingMan(parkingMan);
    }

    @Test
    public void testBossParking() {
        Car car = new Car();
        assertThat(manager.canPark(), is(true));
        assertThat(manager.park(car), is(parkingLot1));
        assertThat(manager.get(car), is(car));
    }

    @Test
    public void testManagerCanAssignToParkingMan() throws Exception {
        Car car = new Car();
        assertThat(manager.assign(car), is(parkingLot4));
        assertThat(manager.get(car), is(car));
    }

    @Test
    public void printAllParkingLots() {
        manager.print();
        assertEquals(
                "Manager:manager\n" +
                "    ParkingMan:boy\n" +
                "    ParkingLot name4\n" +
                "\n" +
                "    ParkingMan:girl\n" +
                "    ParkingLot name5\n" +
                "\n" +
                "    ParkingLot name1\n" +
                "    ParkingLot name2\n" +
                "    ParkingLot name3", outContent.toString().trim());
    }
}
