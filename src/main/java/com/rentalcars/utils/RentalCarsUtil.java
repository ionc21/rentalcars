package com.rentalcars.utils;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.commons.collections.CollectionUtils;

public class RentalCarsUtil {

	public static String getValue(final Exchange exchange,
			final String property) {
		List<String> values = exchange.getProperty(property, List.class);
		if (CollectionUtils.isNotEmpty(values)) {
			return values.get(0);
		} else {
			return "";
		}
	}

}
