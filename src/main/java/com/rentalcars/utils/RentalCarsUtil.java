package com.rentalcars.utils;

import static com.rentalcars.utils.CarUtil.getCarTypeBySIPP;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;

import com.rentalcars.model.Car;
import com.rentalcars.bo.CarBO;

public class RentalCarsUtil {

	public static String getValue(final Exchange exchange, final String property) {
		List<String> values = exchange.getProperty(property, List.class);
		if (isNotEmpty(values)) {
			return values.get(0);
		} else {
			return "";
		}
	}

	public static List<Car> getHighestRatedSupplier(final List<Car> cars) {
		Map<String, Optional<Car>> vehiclesPerCarType = cars.stream()
				.collect(Collectors.groupingBy(c -> getCarTypeBySIPP(c.getSipp()), Collectors.maxBy(Comparator.comparing(Car::getRating))));

		return vehiclesPerCarType.values().stream().filter(v -> v.isPresent()).map(v -> v.get()).sorted(Comparator.comparing(
                Car::getRating).reversed())
				.collect(Collectors.toList());
	}

	public static List<CarBO> getCarsByScoreDESC(final List<CarBO> cars) {
		cars.sort(Comparator.comparing(CarBO::getSumScores).reversed());
		return cars;
	}

}
