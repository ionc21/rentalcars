package com.rentalcars.route;

import static java.text.MessageFormat.format;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.rentalcars.processor.VehicleProcessor;

public class RentalCarMainRoute extends RouteBuilder {

    @Autowired
    private VehicleProcessor vehicleProcessor;

    public static final String VEHICLE_ENDPOINT = format("cxfrs:bean:rentalCarRestService{0}",
            "?bindingStyle=SimpleConsumer");
    public static final String VEHICLE_ENDPOINT_ID = "vehicleRouteId";

    @Override
    public void configure() throws Exception {
        from(VEHICLE_ENDPOINT).id(VEHICLE_ENDPOINT_ID).process(vehicleProcessor);
    }
}
