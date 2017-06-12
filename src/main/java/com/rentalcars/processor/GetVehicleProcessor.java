package com.rentalcars.processor;

import static com.rentalcars.constants.RentalCarsConstants.NAME_AND_PRICE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.rentalcars.bo.Car;
import com.rentalcars.model.json.Vehicle;
import com.rentalcars.model.json.VehicleList;
import com.rentalcars.transformer.RentalCarTrasnformer;

public class GetVehicleProcessor implements Processor {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final String FILE_PATH = "src/main/resources/vehicle/vehicle.json";

	@Override
	public void process(final Exchange exchange) throws Exception {
		final String queryString = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
		final MultivaluedMap<String, String> queryMap = JAXRSUtils.getStructuredParams(queryString, "&", false, false);

		for (Entry<String, List<String>> entry : queryMap.entrySet()) {
			exchange.setProperty(entry.getKey(), entry.getValue());
		}

		final Vehicle vehicleRespFromFile = objectMapper.readValue(new File(FILE_PATH), Vehicle.class);

		List<VehicleList> vehicles = vehicleRespFromFile.getVehicleList();
		List<Car> cars = new ArrayList<>(vehicles.size());
		CollectionUtils.collect(vehicles, RentalCarTrasnformer.VEHICLE_TO_CAR, cars);

		String httpPath = exchange.getIn().getHeader(Exchange.HTTP_PATH, String.class);
		String[] propArray = StringUtils.split(httpPath, "/");
		String property = propArray[propArray.length - 1];

		switch (property) {
		case NAME_AND_PRICE:
			mappByNameAndPrice(cars, exchange);
			break;
		default:
			exchange.getIn().setBody(cars);
			break;
		}

	}

	private void mappByNameAndPrice(final List<Car> cars, final Exchange exchange) {
		List<Car> carMappedByNameAndPrice = cars.stream().map(c -> {
			Car car = new Car(c.getName(), c.getPrice());
			return car;
		}).collect(Collectors.toList());

		exchange.getIn().setBody(carMappedByNameAndPrice);
	}
}
