package com.rentalcars.comparators;

import java.util.Comparator;

import com.rentalcars.model.json.VehicleList;

public class RatingComparatorDESC implements Comparator<VehicleList> {
	@Override
	public int compare(final VehicleList o1, final VehicleList o2) {
		return o2.getRating().compareTo(o1.getRating());
	}

}
