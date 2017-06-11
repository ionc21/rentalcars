package com.rentalcars.routes;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.rentalcars.model.json.Vehicle;

@Component(RentalCarMainRoute.BEAN_NAME)
public class RentalCarMainRoute extends RouteBuilder {

	public static final String BEAN_NAME = "rentalCarRoute";
	public static final String VEHICLE_ENDPOINT = "cxfrs://http://localhost:9090?resourceClasses=com.rentalcars.service.RentalCarService&bindingStyle=Default";
	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final String FILE_PATH = "src/main/resources/vehicle/vehicle.json";
	// private static final String FILE_PATH = "/vehicle/vehicle.json";

	@Override
	public void configure() throws Exception {

		// InputStream is = this.getClass().getResourceAsStream(FILE_PATH);

		from(VEHICLE_ENDPOINT).process(new Processor() {

			@Override
			public void process(final Exchange exchange) throws Exception {
				Vehicle vehicle = objectMapper.readValue(new File(FILE_PATH),
						Vehicle.class);
				vehicle.getVehicleList();
			}
		}).log("Print out body: ${body}");
	}

}
