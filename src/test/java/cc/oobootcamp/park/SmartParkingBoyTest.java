package cc.oobootcamp.park;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SmartParkingBoyTest {

    @Test
    void should_park_a_car_into_lot_A_returning_a_ticket_when_park_a_car_given_lot_a_has_more_available_space_than_lot_b() {
        ParkingLot moreSpaceLot = new ParkingLot(2);
        ParkingLot lessSpaceLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(lessSpaceLot, moreSpaceLot));
        Car car = new Car("car number A");

        Ticket ticket = smartParkingBoy.smartPark(car);

        assertNotNull(ticket);
        assertThat(moreSpaceLot.getAvailableSpaceNumber(), is(1));
        assertThat(lessSpaceLot.getAvailableSpaceNumber(), is(1));
    }

    @Test
    void should_park_a_car_into_lot_A_returning_a_ticket_when_park_a_car_given_two_lots_having_same_available_space() {
        ParkingLot firstParkLot = new ParkingLot(1);
        ParkingLot secondParkLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(firstParkLot, secondParkLot));
        Car car = new Car("car number A");

        Ticket ticket = smartParkingBoy.smartPark(car);

        assertNotNull(ticket);
        assertThat(firstParkLot.getAvailableSpaceNumber(), is(0));
        assertThat(secondParkLot.getAvailableSpaceNumber(), is(1));
    }
}
