package com.rentalcars.route;

import java.text.MessageFormat;

import com.rentalcars.bo.Car;
import org.apache.camel.builder.RouteBuilder;

import com.rentalcars.processor.VehicleProcessor;

public class RentalCarMainRoute extends RouteBuilder {
	public static final String VEHICLE_ENDPOINT = MessageFormat.format(
			"cxfrs:bean:rentalCarRestService{0}",
			"?bindingStyle=SimpleConsumer");
	public static final String VEHICLE_ENDPOINT_ID = "vhicleRouteId";

	@Override
	public void configure() throws Exception {
		from(VEHICLE_ENDPOINT).id(VEHICLE_ENDPOINT_ID)
				.process(new VehicleProcessor());
	}
}
