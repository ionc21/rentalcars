package com.rentalcars.comparators;

import java.util.Comparator;

import com.rentalcars.bo.Car;

public class PriceComparatorASC implements Comparator<Car> {

	@Override
	public int compare(final Car o1, final Car o2) {
		return o1.getPrice().compareTo(o2.getPrice());
	}
}