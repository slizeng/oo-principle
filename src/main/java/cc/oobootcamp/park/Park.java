package cc.oobootcamp.park;


import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Park {
    public static final String FAILED = "failed";
    public static final String SUCCEED = "success";

    private static final int EMPTY_SIZE = 0;

    private int parkMaxSize;
    private Map<String, Car> parkingSpaces = new HashMap<>();

    public Park(int parkMaxSize) {
        this.parkMaxSize = parkMaxSize > 0 ? parkMaxSize : EMPTY_SIZE;
    }

    public Ticket applyParking(Car car) {
        if (hasParkingSpaces() && isValidCar(car)) {
            parkingSpaces.put(car.getPlateNumber(), car);
            return new Ticket(car.getPlateNumber());
        }

        return null;
    }

    public String applyLeaving(Ticket ticket) {
        return nonNull(parkingSpaces.remove(ticket.getPlateNumber())) ? SUCCEED : FAILED;
    }

    private boolean hasParkingSpaces() {
        return parkingSpaces.size() < parkMaxSize;
    }

    private boolean isValidCar(Car car) {
        return nonNull(car) && isNotEmpty(car.getPlateNumber());
    }
}
