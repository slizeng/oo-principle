package cc.oobootcamp.car;

import java.util.Objects;

public class Car {
    private String carNumber;

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    @Override
    public boolean equals(Object targetCar) {
        if (this == targetCar) return true;
        if (targetCar == null || getClass() != targetCar.getClass()) return false;
        Car car = (Car) targetCar;

        return Objects.equals(carNumber, car.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber);
    }
}
