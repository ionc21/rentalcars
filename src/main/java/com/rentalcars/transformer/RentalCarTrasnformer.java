package com.rentalcars.transformer;

import org.apache.commons.collections4.Transformer;

import com.rentalcars.bo.Car;
import com.rentalcars.model.json.VehicleList;

public class RentalCarTrasnformer {

	public static final Transformer<VehicleList, Car> VEHICLE_TO_CAR = new Transformer<VehicleList, Car>() {

		@Override
		public Car transform(final VehicleList input) {

			String sipp = input.getSipp();
			String name = input.getName();
			Float price = input.getPrice();
			String supplier = input.getSupplier();
			Float rating = input.getRating();
			Car car = new Car(sipp, name, price, supplier, rating);
			return car;
		}

	};

}
