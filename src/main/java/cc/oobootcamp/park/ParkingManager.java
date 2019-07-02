package cc.oobootcamp.park;

import cc.oobootcamp.exception.NonAvailableParkingSpaceException;
import cc.oobootcamp.exception.ParkingSpaceIsFullException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ParkingManager extends ParkingBoy {
    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
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
        } else {
            throw new NonAvailableParkingSpaceException();
        }
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
