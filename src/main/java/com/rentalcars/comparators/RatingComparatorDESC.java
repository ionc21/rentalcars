package com.rentalcars.comparators;

import java.util.Comparator;

import com.rentalcars.bo.Car;

public class RatingComparatorDESC implements Comparator<Car> {
	@Override
	public int compare(final Car o1, final Car o2) {
		return o2.getRating().compareTo(o1.getRating());
	}

}
