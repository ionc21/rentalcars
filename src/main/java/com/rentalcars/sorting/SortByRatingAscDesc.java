package com.rentalcars.sorting;

import java.util.Collections;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.rentalcars.comparators.RatingComparatorASC;
import com.rentalcars.comparators.RatingComparatorDESC;
import com.rentalcars.constants.RentalCarsConstants;
import com.rentalcars.model.json.VehicleList;
import com.rentalcars.model.json.VehicleResponse;
import com.rentalcars.utils.RentalCarsUtil;

@Component(SortByRatingAscDesc.BEAN_NAME)
public class SortByRatingAscDesc {
	public static final String BEAN_NAME = "sortByRatingAscDesc";

	public void sortVehicles(final Exchange exchange) {
		String sortType = RentalCarsUtil.getValue(exchange,
				RentalCarsConstants.SORT);
		VehicleResponse vehicleResponse = exchange.getIn()
				.getBody(VehicleResponse.class);
		List<VehicleList> vehicleOutputColl = vehicleResponse.getVehicleList();
		if (sortType.equalsIgnoreCase(RentalCarsConstants.DESC_SORTING)) {
			Collections.sort(vehicleOutputColl, new RatingComparatorDESC());
		} else {
			Collections.sort(vehicleOutputColl, new RatingComparatorASC());
		}
		VehicleResponse sortedVehicles = new VehicleResponse();
		sortedVehicles.setVehicleList(vehicleOutputColl);
		exchange.getOut().setBody(sortedVehicles);
	}
}
