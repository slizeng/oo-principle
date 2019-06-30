package cc.oobootcamp.park;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Park {
    public static final String FAILED = "failed";
    public static final String SUCCEED = "success";

    private static final int EMPTY_SIZE = 0;

    private int parkMaxSize;
    private Set<Car> parkingSpaces = new HashSet<>();

    public Park(int parkMaxSize) {
        this.parkMaxSize = parkMaxSize > 0 ? parkMaxSize : EMPTY_SIZE;
    }

    public String applyParking(Car car) {
        if (hasParkingSpaces() && isValidCar(car)) {
            parkingSpaces.add(car);
            return car.getCarNumber();
        }
        return FAILED;
    }

    public String applyLeaving(Car car) {
        return parkingSpaces.remove(car) ? SUCCEED : FAILED;
    }

    private boolean hasParkingSpaces() {
        return parkingSpaces.size() < parkMaxSize;
    }

    private boolean isValidCar(Car car) {
        return nonNull(car) && isNotEmpty(car.getCarNumber());
    }
}
