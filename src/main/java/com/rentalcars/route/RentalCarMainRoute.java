package com.rentalcars.route;

import com.rentalcars.processor.VehicleProcessor;
import org.apache.camel.builder.RouteBuilder;

import static java.text.MessageFormat.format;

public class RentalCarMainRoute extends RouteBuilder {
	public static final String VEHICLE_ENDPOINT = format(
			"cxfrs:bean:rentalCarRestService{0}",
			"?bindingStyle=SimpleConsumer");
	public static final String VEHICLE_ENDPOINT_ID = "vhicleRouteId";

	@Override
	public void configure() throws Exception {
		from(VEHICLE_ENDPOINT).id(VEHICLE_ENDPOINT_ID)
				.process(new VehicleProcessor());
	}
}
