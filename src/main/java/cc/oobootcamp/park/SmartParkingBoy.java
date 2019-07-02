package cc.oobootcamp.park;

import java.util.List;

import static java.util.Comparator.comparingInt;

public class SmartParkingBoy extends GraduateParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket smartPark(Car car) throws RuntimeException {
        ParkingLot parkingLot = parkingLots.stream()
                .filter(ParkingLot::hasParkingSpace)
                .max((comparingInt(ParkingLot::getAvailableSpaceNumber)))
                .orElseThrow(NonAvailableParkingSpaceException::new);
        return parkingLot.parkCar(car);
    }
}
