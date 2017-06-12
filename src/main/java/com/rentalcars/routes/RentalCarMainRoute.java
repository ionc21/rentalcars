package com.rentalcars.routes;

import java.text.MessageFormat;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.rentalcars.processor.GetVehicleProcessor;

@Component(RentalCarMainRoute.BEAN_NAME)
public class RentalCarMainRoute extends RouteBuilder {

	public static final String BEAN_NAME = "rentalCarMainRoute";
	public static final String VEHICLE_ENDPOINT = MessageFormat.format("cxfrs:bean:rentalCarRestService{0}", "?bindingStyle=SimpleConsumer");

	@Override
	public void configure() throws Exception {
		from(VEHICLE_ENDPOINT).process(new GetVehicleProcessor());
	}
}
