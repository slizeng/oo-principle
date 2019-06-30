package cc.oobootcamp.park;

import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasParkingSpace)
                .findFirst()
                .map(parkingLot -> parkingLot.parkCar(car))
                .orElseThrow(ParkingSpaceIsFullException::new);
    }

    public Car pick(Ticket ticket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.pickCar(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(NoMatchedCarException::new);
    }
}
