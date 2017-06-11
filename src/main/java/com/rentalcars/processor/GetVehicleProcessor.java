package com.rentalcars.processor;

import java.io.File;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.rentalcars.model.json.VehicleResponse;

public class GetVehicleProcessor implements Processor {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final String FILE_PATH = "src/main/resources/vehicle/vehicle.json";

	@Override
	public void process(final Exchange exchange) throws Exception {
		final String queryString = exchange.getIn()
				.getHeader(Exchange.HTTP_QUERY, String.class);
		final MultivaluedMap<String, String> queryMap = JAXRSUtils
				.getStructuredParams(queryString, "&", false, false);

		for (Entry<String, List<String>> entry : queryMap.entrySet()) {
			exchange.setProperty(entry.getKey(), entry.getValue());
		}

		VehicleResponse vehicleResponse = objectMapper
				.readValue(new File(FILE_PATH), VehicleResponse.class);
		exchange.getIn().setBody(vehicleResponse);
	}
}
