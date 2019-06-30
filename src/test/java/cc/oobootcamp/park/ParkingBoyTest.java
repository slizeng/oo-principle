package cc.oobootcamp.park;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingBoyTest {
    private static final String FALSE_PLATE_NUMBER = "PLATE NUMBER FALSE";
    private static final String PLATE_NUMBER = "PLATE NUMBER";

    @Test
    void should_return_a_ticket_when_parking_boy_park_a_car_given_a_parking_lot_A_has_higher_order_than_parking_lot_B_and_both_of_them_have_available_space() {
        Car car = new Car(PLATE_NUMBER);
        ParkingLot higherPriorityParkingLot = new ParkingLot(1);
        ParkingLot lowerPriorityParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(asList(higherPriorityParkingLot, lowerPriorityParkingLot));

        Ticket ticket = parkingBoy.park(car);

        assertNotNull(ticket);
        assertFalse(higherPriorityParkingLot.hasParkingSpace());
        assertTrue(lowerPriorityParkingLot.hasParkingSpace());
    }

    @Test
    void should_return_a_ticket_when_parking_boy_park_a_car_given_a_parking_lot_A_has_higher_order_than_parking_lot_B_and_A_have_no_available_space_and_B_have_available_space() {
        Car car = new Car(PLATE_NUMBER);
        ParkingLot higherPriorityParkingLot = new ParkingLot(1);
        higherPriorityParkingLot.parkCar(new Car(FALSE_PLATE_NUMBER));
        ParkingLot lowerPriorityParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(asList(higherPriorityParkingLot, lowerPriorityParkingLot));

        Ticket ticket = parkingBoy.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_not_park_this_car_when_parking_boy_park_a_car_given_two_parking_lots_and_both_have_no_available_space() {
        Car car = new Car(PLATE_NUMBER);
        ParkingBoy parkingBoy = new ParkingBoy(buildFullParkingLots(2, 2));

        assertThrows(ParkingSpaceIsFullException.class, () -> parkingBoy.park(car));
    }

    @Test
    void should_return_my_car_when_parking_boy_picks_a_car_with_matched_ticket() {
        Car car = new Car(PLATE_NUMBER);
        ParkingLot higherPriorityParkingLot = new ParkingLot(1);
        ParkingLot lowerPriorityParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(asList(higherPriorityParkingLot, lowerPriorityParkingLot));
        Ticket ticket = parkingBoy.park(car);

        Car returnedCar = parkingBoy.pick(ticket);

        assertNotNull(ticket);
        assertEquals(returnedCar, car);
    }

    @Test
    void should_not_return_any_car_when_parking_boy_picks_a_car_without_valid_ticket() {
        Car car = new Car(PLATE_NUMBER);
        ParkingLot higherPriorityParkingLot = new ParkingLot(1);
        ParkingLot lowerPriorityParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(asList(higherPriorityParkingLot, lowerPriorityParkingLot));
        Ticket ticket = parkingBoy.park(car);

        assertNotNull(ticket);
        assertThrows(NoMatchedCarException.class, () ->
                parkingBoy.pick(new Ticket(FALSE_PLATE_NUMBER))
        );
    }

    private List<ParkingLot> buildFullParkingLots(int numberOfLots, int capacity) {
        return IntStream.range(0, numberOfLots)
                .mapToObj((index) -> {
                    ParkingLot parkingLot = new ParkingLot(capacity);
                    IntStream.range(0, capacity).forEach((i) -> parkingLot.parkCar(new Car(String.valueOf(i))));
                    return parkingLot;
                }).collect(Collectors.toList());
    }
}
