package cc.oobootcamp.park;

import cc.oobootcamp.exception.NoMatchedCarException;
import cc.oobootcamp.exception.NonAvailableParkingSpaceException;
import cc.oobootcamp.exception.ParkingSpaceIsFullException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ParkingManager {
    private List<ParkingLot> parkingLots;
    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        this.parkingLots = parkingLots;
        this.parkingBoys = parkingBoys;
    }

    public Ticket park(Car car) {
        Optional<Ticket> optionalTicketFromBoys = parkByBoys(car);

        if (optionalTicketFromBoys.isPresent()) {
            return optionalTicketFromBoys.get();
        }

        Optional<Ticket> optionalTicketFromSelf = parkBySelf(car);

        if (optionalTicketFromSelf.isPresent()) {
            return optionalTicketFromSelf.get();
        }

        throw new NonAvailableParkingSpaceException();
    }

    public Car pick(Ticket ticket) {
        Optional<Car> pickedCarBySelf = pickBySelf(ticket);

        if (pickedCarBySelf.isPresent()) {
            return pickedCarBySelf.get();
        }

        Optional<Car> optionalCar = pickByParkingBoys(ticket);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }

        throw new NoMatchedCarException();
    }

    private Optional<Car> pickBySelf(Ticket ticket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.pickCar(ticket))
                .filter(Objects::nonNull)
                .findFirst();
    }

    private Optional<Car> pickByParkingBoys(Ticket ticket) {
        return parkingBoys.stream()
                .map(parkingBoy -> {
                    try {
                        return parkingBoy.pick(ticket);
                    } catch (NoMatchedCarException ignored) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst();
    }

    private Optional<Ticket> parkBySelf(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasParkingSpace)
                .map(parkingLot -> parkingLot.parkCar(car))
                .findFirst();
    }

    private Optional<Ticket> parkByBoys(Car car) {
        return parkingBoys.stream()
                .map(parkingBoy -> {
                    try {
                        return parkingBoy.park(car);
                    } catch (ParkingSpaceIsFullException ignored) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst();
    }
}
