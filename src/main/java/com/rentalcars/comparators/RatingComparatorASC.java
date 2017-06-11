package com.rentalcars.comparators;

import java.util.Comparator;

import com.rentalcars.model.json.VehicleList;

public class RatingComparatorASC implements Comparator<VehicleList> {
	@Override
	public int compare(final VehicleList o1, final VehicleList o2) {
		return o1.getRating().compareTo(o2.getRating());
	}

}
