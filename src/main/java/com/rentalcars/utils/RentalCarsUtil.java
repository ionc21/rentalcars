package com.rentalcars.utils;

import com.rentalcars.bo.Car;
import org.apache.camel.Exchange;
import org.apache.commons.collections.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RentalCarsUtil {

	public static String getValue(final Exchange exchange, final String property) {
		List<String> values = exchange.getProperty(property, List.class);
		if (CollectionUtils.isNotEmpty(values)) {
			return values.get(0);
		} else {
			return "";
		}
	}

	public static List<Car> getHighestRatedSupplier(final List<Car> cars) {
		Map<String, Optional<Car>> vehiclesPerCarType = cars.stream()
				.collect(Collectors.groupingBy(Car::getCarTypeBySIPP, Collectors.maxBy(Comparator.comparing(Car::getRating))));

		return vehiclesPerCarType.values().stream().filter(v -> v.isPresent()).map(v -> v.get()).sorted(Comparator.comparing(Car::getRating).reversed())
				.collect(Collectors.toList());
	}

	public static List<Car> getCarsByScoreDESC(final List<Car> cars) {
		cars.sort(Comparator.comparing(Car::getSumScores).reversed());
		return cars;
	}

}
