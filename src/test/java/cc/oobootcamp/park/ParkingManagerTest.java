package cc.oobootcamp.park;

import cc.oobootcamp.exception.NoMatchedCarException;
import cc.oobootcamp.exception.NonAvailableParkingSpaceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cc.oobootcamp.park.GraduateParkingBoyTest.buildFullParkingLots;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingManagerTest {

    private static final String CAR_NUMBER_A = "car number A";
    private static final String CAR_NUMBER_B = "car number B";

    @Test
    void should_smart_parking_boy_and_graduate_parking_boy_park_cars_and_return_tickets_when_parking_manager_manages_them_to_park_cars_give_two_boys_with_available_space() {
        Car firstCar = new Car(CAR_NUMBER_A);
        Car secondCar = new Car(CAR_NUMBER_B);
        ParkingBoy graduateParkingBoy =
                new GraduateParkingBoy(buildEmptyParkingLots());
        ParkingBoy smartParkingBoy =
                new SmartParkingBoy(buildEmptyParkingLots());
        List<ParkingBoy> parkingBoys = asList(graduateParkingBoy, smartParkingBoy);
        ParkingManager parkingManager = new ParkingManager(
                singletonList(new ParkingLot(0)),
                parkingBoys
        );

        Ticket firstTicket = parkingManager.park(firstCar);
        Ticket secondTicket = parkingManager.park(secondCar);

        assertNotNull(firstTicket);
        assertNotNull(secondTicket);
        assertEquals(firstCar, graduateParkingBoy.pick(firstTicket));
        assertEquals(secondCar, smartParkingBoy.pick(secondTicket));
    }

    @Test
    void should_parking_boy_and_parking_manager_park_cars_and_return_tickets_when_parking_manager_manages_them_to_park_cars() {
        Car firstCar = new Car(CAR_NUMBER_A);
        Car secondCar = new Car(CAR_NUMBER_B);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(buildEmptyParkingLots());
        ParkingManager parkingManager = new ParkingManager(
                buildEmptyParkingLots(),
                singletonList(graduateParkingBoy)
        );

        Ticket firstTicket = parkingManager.park(firstCar);
        Ticket secondTicket = parkingManager.park(secondCar);

        assertNotNull(firstTicket);
        assertNotNull(secondTicket);
        assertEquals(firstCar, graduateParkingBoy.pick(firstTicket));
        assertEquals(secondCar, parkingManager.pick(secondTicket));
    }

    @Test
    void should_parking_manager_park_car_and_return_ticket_when_parking_manager_park_car_given_parking_boy_without_parking_space_and_manager_has_space() {
        Car car = new Car(CAR_NUMBER_A);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(buildFullParkingLots());
        ParkingManager parkingManager = new ParkingManager(
                buildEmptyParkingLots(),
                singletonList(graduateParkingBoy)
        );

        Ticket ticket = parkingManager.park(car);

        assertNotNull(ticket);
        assertEquals(car, parkingManager.pick(ticket));
    }

    @Test
    void should_not_park_this_car_and_throw_NonAvailableParkingSpaceException_when_parking_manager_parks_car_given_parking_boy_without_available_space_and_manager_without_available_space() {
        Car car = new Car(CAR_NUMBER_A);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(buildFullParkingLots());
        ParkingManager parkingManager = new ParkingManager(
                buildFullParkingLots(),
                singletonList(graduateParkingBoy)
        );

        assertThrows(NonAvailableParkingSpaceException.class, () -> parkingManager.park(car));
    }


    @Test
    void should_target_car_be_returned_when_graduate_parking_boy_to_pick_car_with_valid_ticket_given_a_car_parked_in_graduate_parking_boys_lot() {
        Car car = new Car(CAR_NUMBER_A);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(buildEmptyParkingLots());
        ParkingManager parkingManager = new ParkingManager(
                buildFullParkingLots(),
                singletonList(graduateParkingBoy)
        );
        Ticket ticket = parkingManager.park(car);

        Car returnedCar = graduateParkingBoy.pick(ticket);

        assertNotNull(ticket);
        assertEquals(car, returnedCar);
    }

    @Test
    void should_target_car_be_returned_when_parking_manager_picks_car_with_valid_ticket_given_a_car_parked_in_graduate_parking_boys_lot() {
        Car car = new Car(CAR_NUMBER_A);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(buildEmptyParkingLots());
        ParkingManager parkingManager = new ParkingManager(
                buildFullParkingLots(),
                singletonList(graduateParkingBoy)
        );
        Ticket ticket = parkingManager.park(car);

        Car returnedCar = parkingManager.pick(ticket);

        assertNotNull(ticket);
        assertEquals(car, returnedCar);
    }

    @Test
    void should_target_car_not_be_returned_and_throws_NoMatchedCarException_when_smart_parking_boy_picks_car_with_valid_ticket_given_a_car_parked_in_graduate_parking_boys_lot() {
        Car car = new Car(CAR_NUMBER_A);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(buildEmptyParkingLots());
        ParkingBoy smartParkingBoy = new SmartParkingBoy(buildFullParkingLots());
        ParkingManager parkingManager = new ParkingManager(
                buildFullParkingLots(),
                asList(graduateParkingBoy, smartParkingBoy)
        );
        Ticket ticket = parkingManager.park(car);

        assertThrows(NoMatchedCarException.class, () -> smartParkingBoy.pick(ticket));
    }


    private List<ParkingLot> buildEmptyParkingLots() {
        return singletonList(new ParkingLot(1));
    }
}
