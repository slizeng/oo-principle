package cc.oobootcamp.park;

import cc.oobootcamp.exception.NoMatchedCarException;

import java.util.List;
import java.util.Objects;

public abstract class ParkingBoy {
    List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract Ticket park(Car car);

    public Car pick(Ticket ticket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.pickCar(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(NoMatchedCarException::new);
    }
}
