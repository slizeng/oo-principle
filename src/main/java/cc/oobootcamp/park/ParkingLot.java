package cc.oobootcamp.park;


import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ParkingLot {
    private static final int EMPTY_SIZE = 0;

    private int parkMaxSize;
    private Map<String, Car> parkingSpace = new HashMap<>();

    public ParkingLot(int parkMaxSize) {
        this.parkMaxSize = parkMaxSize > 0 ? parkMaxSize : EMPTY_SIZE;
    }

    public Ticket parkCar(Car car) {
        if (hasParkingSpace() && isValidCar(car)) {
            parkingSpace.put(car.getPlateNumber(), car);
            return new Ticket(car.getPlateNumber());
        }

        return null;
    }

    public Car pickCar(Ticket ticket) {
        return parkingSpace.remove(ticket.getPlateNumber());
    }

    boolean hasParkingSpace() {
        return parkingSpace.size() < parkMaxSize;
    }

    int getAvailableSpaceNumber() {
        int availableSpaceSize = parkMaxSize - parkingSpace.size();

        return availableSpaceSize < 0 ? 0 : availableSpaceSize;
    }

    private boolean isValidCar(Car car) {
        return nonNull(car) && isNotEmpty(car.getPlateNumber());
    }
}
