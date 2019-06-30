package cc.oobootcamp.park;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static cc.oobootcamp.park.Park.FAILED;
import static cc.oobootcamp.park.Park.SUCCEED;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


class ParkTest {
    private static final int PARK_MAX_SIZE = 1;
    private static final String NUMBER_TWO = "NUMBER_TWO";
    private static String NUMBER_ONE = "NUMBER_ONE";
    private Park park;
    private Car carOne;
    private Car carTwo;

    @BeforeEach
    void setUp() {
        park = new Park(PARK_MAX_SIZE);
        carOne = new Car(NUMBER_ONE);
        carTwo = new Car(NUMBER_TWO);
    }

    @Test
    void should_return_succeed_and_plate_number_when_a_car_arrives_and_park_is_not_full() {
        Ticket result = park.applyParking(carOne);

        assertThat(result.getPlateNumber(), is(NUMBER_ONE));
    }

    @Test
    void should_return_failed_when_a_car_arrives_and_park_is_full() {
        park.applyParking(carTwo);

        Ticket result = park.applyParking(carOne);

        assertNull(result);
    }

    @Test
    void should_return_succeed_when_a_car_leave_the_park_with_the_correct_car_number() {
        Ticket ticket = park.applyParking(carOne);

        String resultOfLeaving = park.applyLeaving(ticket);

        assertThat(resultOfLeaving, is(SUCCEED));
    }

    @Test
    void should_return_failed_when_a_car_leaves_with_incorrect_car_number() {
        park.applyParking(carOne);
        Ticket ticket = new Ticket(carTwo.getPlateNumber());

        String resultOfLeaving = park.applyLeaving(ticket);
        assertThat(resultOfLeaving, is(FAILED));
    }

    @Test
    void should_parking_spaces_plus_one_when_a_car_leaves() {
        Ticket ticket = park.applyParking(carOne);
        assertNull(park.applyParking(carTwo));

        String resultOfLeaving = park.applyLeaving(ticket);
        assertThat(resultOfLeaving, is(SUCCEED));

        assertThat(park.applyParking(carTwo).getPlateNumber(), is(carTwo.getPlateNumber()));
    }

    @Test
    void should_parking_spaces_minus_one_when_a_car_parks() {
        Ticket resultOfLastSpaceParking = park.applyParking(carOne);
        Ticket resultOfNotEnoughSpace = park.applyParking(carTwo);

        assertThat(resultOfLastSpaceParking.getPlateNumber(), is(carOne.getPlateNumber()));
        assertNull(resultOfNotEnoughSpace);
    }

    @Test
    void should_return_failed_when_a_car_parks_without_valid_plate_number() {
        Ticket resultOfEmptyString = park.applyParking(new Car(""));
        Ticket resultOfNull = park.applyParking(null);

        assertNull(resultOfEmptyString);
        assertNull(resultOfNull);
    }
}