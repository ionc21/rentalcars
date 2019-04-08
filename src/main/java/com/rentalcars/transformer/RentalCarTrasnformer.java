package com.rentalcars.transformer;

import static com.rentalcars.utils.CarUtil.*;

import org.apache.commons.collections4.Transformer;

import com.rentalcars.model.Car;
import com.rentalcars.bo.CarBO;
import com.rentalcars.model.Vehicle;

public class RentalCarTrasnformer {

    public static final Transformer<Vehicle, Car> VEHICLE_TO_CAR = (Vehicle v) -> {
        Car.Builder builder = new Car.Builder();
        builder.withSipp(v.getSipp())
                .withName(v.getName())
                .withPrice(v.getPrice())
                .withSupplier(v.getSupplier())
                .withRating(v.getRating());
        return builder.build();
    };

    public static final Transformer<Car, CarBO> CAR_TO_CAR_BO = (Car car) -> {
        final Integer score = computeScore(car.getSipp());
        CarBO carBO = new CarBO();
        carBO.setCarType(getCarTypeBySIPP(car.getSipp()));
        carBO.setName(car.getName());
        carBO.setPrice(car.getPrice());
        carBO.setRating(car.getRating());
        carBO.setScore(score);
        carBO.setSipp(car.getSipp());
        carBO.setSpec(getCarSpec(car.getSipp(), car.getName()));
        carBO.setSupplier(car.getSupplier());
        carBO.setSumScores(computeSumScore(score, car.getRating()));

        return carBO;
    };
}
