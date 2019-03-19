package com.rentalcars.transformer;

import com.rentalcars.bo.Car;
import com.rentalcars.model.json.Vehicle;
import org.apache.commons.collections4.Transformer;

public class RentalCarTrasnformer {

    public static final Transformer<Vehicle, Car> VEHICLE_TO_CAR = (Vehicle v) -> {
        String sipp = v.getSipp();
        String name = v.getName();
        Float price = v.getPrice();
        String supplier = v.getSupplier();
        Float rating = v.getRating();
        Car car = new Car(sipp, name, price, supplier, rating);
        return car;
    };

}
