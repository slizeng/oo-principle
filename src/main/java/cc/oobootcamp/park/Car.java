package cc.oobootcamp.park;

import java.util.Objects;

public class Car {
    private String plateNumber;

    public Car(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    @Override
    public boolean equals(Object targetCar) {
        if (this == targetCar) return true;
        if (targetCar == null || getClass() != targetCar.getClass()) return false;
        Car car = (Car) targetCar;

        return Objects.equals(plateNumber, car.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }
}
