package com.rentalcars.sorting;

import java.util.Collections;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.rentalcars.bo.Car;
import com.rentalcars.comparators.PriceComparatorASC;
import com.rentalcars.comparators.PriceComparatorDESC;
import com.rentalcars.constants.RentalCarsConstants;
import com.rentalcars.utils.RentalCarsUtil;

@Component(SortByPriceAscDesc.BEAN_NAME)
public class SortByPriceAscDesc {
	public static final String BEAN_NAME = "sortByRatingAscDesc";

	public void sortVehicles(final Exchange exchange) {
		String sortType = RentalCarsUtil.getValue(exchange, RentalCarsConstants.SORT);

		List<Car> cars = exchange.getIn().getBody(List.class);
		sortCars(sortType, cars);
		exchange.getOut().setBody(cars);
	}

	private void sortCars(final String sortType, final List<Car> vehicleSortedColl) {
		if (sortType.equalsIgnoreCase(RentalCarsConstants.DESC_SORTING)) {
			Collections.sort(vehicleSortedColl, new PriceComparatorDESC());

		} else {
			Collections.sort(vehicleSortedColl, new PriceComparatorASC());
		}
	}

}
