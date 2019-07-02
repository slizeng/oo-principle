package cc.oobootcamp.park;

import org.junit.jupiter.api.Test;

import java.util.List;

import static cc.oobootcamp.park.GraduateParkingBoyTest.buildFullParkingLots;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ParkingManagerTest {

    private static final String CAR_NUMBER_A = "car number A";
    private static final String CAR_NUMBER_B = "car number B";

    @Test
    void should_smart_parking_boy_and_graduate_parking_boy_park_cars_and_return_tickets_when_parking_manager_manages_them_to_park_cars() {
        Car firstCar = new Car(CAR_NUMBER_A);
        Car secondCar = new Car(CAR_NUMBER_B);
        ParkingBoy graduateParkingBoy =
                new GraduateParkingBoy(singletonList(new ParkingLot(1)));
        ParkingBoy smartParkingBoy =
                new SmartParkingBoy(singletonList(new ParkingLot(1)));
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
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(singletonList(new ParkingLot(1)));
        ParkingManager parkingManager = new ParkingManager(
                singletonList(new ParkingLot(1)),
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
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(buildFullParkingLots(1, 1));
        ParkingManager parkingManager = new ParkingManager(
                singletonList(new ParkingLot(1)),
                singletonList(graduateParkingBoy)
        );

        Ticket firstTicket = parkingManager.park(car);

        assertNotNull(firstTicket);
        assertEquals(car, parkingManager.pick(firstTicket));
    }
}
