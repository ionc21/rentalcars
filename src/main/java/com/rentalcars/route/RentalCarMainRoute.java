package com.rentalcars.route;

import com.rentalcars.processor.VehicleProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import static java.text.MessageFormat.format;

public class RentalCarMainRoute extends RouteBuilder {

    @Autowired
    private VehicleProcessor vehicleProcessor;

    public static final String VEHICLE_ENDPOINT = format("cxfrs:bean:rentalCarRestService{0}",
            "?bindingStyle=SimpleConsumer");
    public static final String VEHICLE_ENDPOINT_ID = "vhicleRouteId";

    @Override public void configure() throws Exception {
        from(VEHICLE_ENDPOINT).id(VEHICLE_ENDPOINT_ID).process(vehicleProcessor);
    }
}
