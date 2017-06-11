package com.rentalcars.comparators;

import java.util.Comparator;

import com.rentalcars.model.json.VehicleList;

public class PriceComparator implements Comparator<VehicleList> {

	@Override
	public int compare(final VehicleList o1, final VehicleList o2) {
		if (o1.getPrice().compareTo(o2.getPrice()) > 0) {
			return 1;
		} else if (o1.getPrice().compareTo(o2.getPrice()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}