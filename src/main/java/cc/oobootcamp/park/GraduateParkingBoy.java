package cc.oobootcamp.park;

import cc.oobootcamp.exception.ParkingSpaceIsFullException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasParkingSpace)
                .findFirst()
                .map(parkingLot -> parkingLot.parkCar(car))
                .orElseThrow(ParkingSpaceIsFullException::new);
    }
}
