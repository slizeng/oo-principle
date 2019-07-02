package cc.oobootcamp.park;

import cc.oobootcamp.exception.NonAvailableParkingSpaceException;

import java.util.List;

import static java.util.Comparator.comparingInt;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream()
                .filter(ParkingLot::hasParkingSpace)
                .max((comparingInt(ParkingLot::getAvailableSpaceNumber)))
                .orElseThrow(NonAvailableParkingSpaceException::new);
        return parkingLot.parkCar(car);
    }
}
