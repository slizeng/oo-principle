package cc.oobootcamp.park;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


class ParkingLotTest {
    private static final int PARK_MAX_SIZE = 1;
    private static final String NUMBER_TWO = "NUMBER_TWO";
    private static String NUMBER_ONE = "NUMBER_ONE";
    private ParkingLot parkingLot;
    private Car carOne;
    private Car carTwo;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(PARK_MAX_SIZE);
        carOne = new Car(NUMBER_ONE);
        carTwo = new Car(NUMBER_TWO);
    }

    @Test
    void should_return_succeed_and_plate_number_when_a_car_arrives_and_park_is_not_full() {
        Ticket result = parkingLot.parkCar(carOne);

        assertThat(result.getPlateNumber(), is(NUMBER_ONE));
    }

    @Test
    void should_return_failed_when_a_car_arrives_and_park_is_full() {
        parkingLot.parkCar(carTwo);

        Ticket result = parkingLot.parkCar(carOne);

        assertNull(result);
    }

    @Test
    void should_return_succeed_when_a_car_leave_the_park_with_the_correct_car_number() {
        Ticket ticket = parkingLot.parkCar(carOne);

        Car car = parkingLot.pickCar(ticket);

        assertThat(car, is(carOne));
    }

    @Test
    void should_return_failed_when_a_car_leaves_with_incorrect_car_number() {
        parkingLot.parkCar(carOne);
        Ticket ticket = new Ticket(carTwo.getPlateNumber());

        Car car = parkingLot.pickCar(ticket);
        assertNull(car);
    }

    @Test
    void should_parking_spaces_plus_one_when_a_car_leaves() {
        Ticket ticket = parkingLot.parkCar(carOne);
        assertNull(parkingLot.parkCar(carTwo));

        Car car = parkingLot.pickCar(ticket);
        assertThat(car, is(carOne));

        assertThat(parkingLot.parkCar(carTwo).getPlateNumber(), is(carTwo.getPlateNumber()));
    }

    @Test
    void should_parking_spaces_minus_one_when_a_car_parks() {
        Ticket resultOfLastSpaceParking = parkingLot.parkCar(carOne);
        Ticket resultOfNotEnoughSpace = parkingLot.parkCar(carTwo);

        assertThat(resultOfLastSpaceParking.getPlateNumber(), is(carOne.getPlateNumber()));
        assertNull(resultOfNotEnoughSpace);
    }

    @Test
    void should_return_failed_when_a_car_parks_without_valid_plate_number() {
        Ticket resultOfEmptyString = parkingLot.parkCar(new Car(""));
        Ticket resultOfNull = parkingLot.parkCar(null);

        assertNull(resultOfEmptyString);
        assertNull(resultOfNull);
    }
}