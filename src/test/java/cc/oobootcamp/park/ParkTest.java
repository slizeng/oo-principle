package cc.oobootcamp.park;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static cc.oobootcamp.park.Park.FAILED;
import static cc.oobootcamp.park.Park.SUCCEED;
import static org.hamcrest.core.Is.is;
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
        String result = park.applyParking(carOne);

        assertThat(result, is(NUMBER_ONE));
    }

    @Test
    void should_return_failed_when_a_car_arrives_and_park_is_full() {
        park.applyParking(carTwo);

        String result = park.applyParking(carOne);

        assertThat(result, is(FAILED));
    }

    @Test
    void should_return_succeed_when_a_car_leave_the_park_with_the_correct_car_number() {
        park.applyParking(carOne);

        String resultOfLeaving = park.applyLeaving(carOne);

        assertThat(resultOfLeaving, is(SUCCEED));
    }

    @Test
    void should_return_failed_when_a_car_leaves_with_incorrect_car_number() {
        park.applyParking(carOne);

        String resultOfLeaving = park.applyLeaving(carTwo);
        assertThat(resultOfLeaving, is(FAILED));
    }

    @Test
    void should_parking_spaces_plus_one_when_a_car_leaves() {
        park.applyParking(carOne);
        assertThat(park.applyParking(carTwo), is(FAILED));

        String resultOfLeaving = park.applyLeaving(carOne);
        assertThat(resultOfLeaving, is(SUCCEED));

        assertThat(park.applyParking(carTwo), is(carTwo.getCarNumber()));
    }

    @Test
    void should_parking_spaces_minus_one_when_a_car_parks() {
        String resultOfLastSpaceParking = park.applyParking(carOne);
        String resultOfNotEnoughSpace = park.applyParking(carTwo);

        assertThat(resultOfLastSpaceParking, is(carOne.getCarNumber()));
        assertThat(resultOfNotEnoughSpace, is(FAILED));
    }

    @Test
    void should_return_failed_when_a_car_parks_without_valid_plate_number() {
        String resultOfEmptyString = park.applyParking(new Car(""));
        String resultOfNull = park.applyParking(null);

        assertThat(resultOfEmptyString, is(FAILED));
        assertThat(resultOfNull, is(FAILED));
    }
}