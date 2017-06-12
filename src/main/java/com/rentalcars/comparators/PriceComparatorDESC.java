package com.rentalcars.comparators;

import java.util.Comparator;

import com.rentalcars.bo.Car;

public class PriceComparatorDESC implements Comparator<Car> {

	@Override
	public int compare(final Car o1, final Car o2) {
		return o2.getPrice().compareTo(o1.getPrice());
	}
}