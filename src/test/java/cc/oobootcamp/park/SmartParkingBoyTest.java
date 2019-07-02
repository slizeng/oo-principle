package cc.oobootcamp.park;

import cc.oobootcamp.exception.NoMatchedCarException;
import cc.oobootcamp.exception.NonAvailableParkingSpaceException;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmartParkingBoyTest {

    private static final String CAR_NUMBER_A = "car number A";

    @Test
    void should_park_a_car_into_lot_A_returning_a_ticket_when_park_a_car_given_lot_a_has_more_available_space_than_lot_b() {
        ParkingLot moreSpaceLot = new ParkingLot(2);
        ParkingLot lessSpaceLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(lessSpaceLot, moreSpaceLot));
        Car car = new Car(CAR_NUMBER_A);

        Ticket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
        assertThat(moreSpaceLot.getAvailableSpaceNumber(), is(1));
        assertThat(lessSpaceLot.getAvailableSpaceNumber(), is(1));
    }

    @Test
    void should_park_a_car_into_lot_A_returning_a_ticket_when_park_a_car_given_two_lots_having_same_available_space() {
        ParkingLot firstParkLot = new ParkingLot(1);
        ParkingLot secondParkLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(firstParkLot, secondParkLot));
        Car car = new Car(CAR_NUMBER_A);

        Ticket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
        assertThat(firstParkLot.getAvailableSpaceNumber(), is(0));
        assertThat(secondParkLot.getAvailableSpaceNumber(), is(1));
    }

    @Test
    void should_not_park_and_return_ticket_when_smart_parking_boy_park_a_car_given_two_parking_lots_without_available_space() {
        ParkingLot firstFullParkingLot = new ParkingLot(1);
        firstFullParkingLot.parkCar(new Car("car number 1"));
        ParkingLot secondParkingLot = new ParkingLot(1);
        secondParkingLot.parkCar(new Car("car number 2"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(firstFullParkingLot, secondParkingLot));
        Car car = new Car(CAR_NUMBER_A);

        assertThrows(NonAvailableParkingSpaceException.class, () -> smartParkingBoy.park(car));
    }

    @Test
    void should_return_target_car_when_smart_parking_boy_pick_a_car_with_valid_ticket_given_two_parking_lots_and_a_car_parked_in_one_lot() {
        ParkingLot firstFullParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(firstFullParkingLot, secondParkingLot));
        Car car = new Car(CAR_NUMBER_A);
        Ticket ticket = smartParkingBoy.park(car);

        Car returnedCar = smartParkingBoy.pick(ticket);

        assertNotNull(ticket);
        assertEquals(car, returnedCar);
    }

    @Test
    void should_not_return_target_car_when_smart_parking_boy_pick_a_car_with_invalid_ticket_given_two_parking_lots_and_a_car_parked_in_one_lot() {
        ParkingLot firstFullParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(asList(firstFullParkingLot, secondParkingLot));
        Car car = new Car(CAR_NUMBER_A);
        Ticket correctTicket = smartParkingBoy.park(car);
        Ticket invalidTicket = new Ticket("invalid number");

        assertNotNull(correctTicket);
        assertThrows(NoMatchedCarException.class, () -> smartParkingBoy.pick(invalidTicket));
        assertThrows(NoMatchedCarException.class, () -> smartParkingBoy.pick(null));
    }
}
