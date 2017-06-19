package com.rentalcars.processor;

import static com.rentalcars.constants.RentalCarsConstants.HIGH_RATED_SUPPLIER;
import static com.rentalcars.constants.RentalCarsConstants.NAME_AND_PRICE;
import static com.rentalcars.constants.RentalCarsConstants.SCORE;
import static com.rentalcars.constants.RentalCarsConstants.SORT;
import static com.rentalcars.constants.RentalCarsConstants.SPEC;
import static com.rentalcars.utils.RentalCarsUtil.getCarsByScoreDESC;
import static com.rentalcars.utils.RentalCarsUtil.getHighestRatedSupplier;
import static com.rentalcars.utils.RentalCarsUtil.getValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import com.rentalcars.comparators.PriceComparatorASC;
import com.rentalcars.comparators.PriceComparatorDESC;
import com.rentalcars.constants.RentalCarsConstants;
import com.rentalcars.model.json.Vehicle;
import com.rentalcars.model.json.VehicleResp;
import com.rentalcars.transformer.RentalCarTrasnformer;

public class VehicleProcessor implements Processor {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final String FILE_PATH = "src/main/resources/vehicle/vehicle.json";

	@Override
	public void process(final Exchange exchange) throws Exception {
		final String queryString = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
		final MultivaluedMap<String, String> queryMap = JAXRSUtils.getStructuredParams(queryString, "&", false, false);

		for (Entry<String, List<String>> entry : queryMap.entrySet()) {
			exchange.setProperty(entry.getKey(), entry.getValue());
		}

		final VehicleResp vehicleRespFromFile = objectMapper.readValue(new File(FILE_PATH), VehicleResp.class);

		List<Vehicle> vehicles = vehicleRespFromFile.getVehicleList();
		List<Car> cars = new ArrayList<>(vehicles.size());
		CollectionUtils.collect(vehicles, RentalCarTrasnformer.VEHICLE_TO_CAR, cars);

		String httpPath = exchange.getIn().getHeader(Exchange.HTTP_PATH, String.class);
		String[] propArray = StringUtils.split(httpPath, "/");
		String property = propArray[propArray.length - 1];

		switch (property) {
		case NAME_AND_PRICE:
			mappByNameAndPrice(cars, exchange);
			break;
		case SPEC:
			mappBySpec(cars, exchange);
			break;
		case HIGH_RATED_SUPPLIER:
			mapByHighestRatedSupplier(cars, exchange);
			break;
		case SCORE:
			mapCarsByScore(cars, exchange);
			break;
		default:
			exchange.getIn().setBody(cars);
			break;
		}

	}

	private void mapCarsByScore(final List<Car> cars, final Exchange exchange) {
		List<Car> carsByScore = getCarsByScoreDESC(cars).stream().map(c -> {
			Car car = new Car(c.getName(), c.getScore(), c.getRating(), c.getSumScores());
			return car;
		}).collect(Collectors.toList());
		exchange.getIn().setBody(carsByScore);
	}

	private void mapByHighestRatedSupplier(final List<Car> cars, final Exchange exchange) {
		List<Car> highestRatedSupplierOfCar = getHighestRatedSupplier(cars).stream().map(c -> {
			Car car = new Car(c.getName(), c.getCarTypeBySIPP(), c.getSupplier(), c.getRating());
			return car;
		}).collect(Collectors.toList());
		exchange.getIn().setBody(highestRatedSupplierOfCar);
	}

	private void mappByNameAndPrice(final List<Car> cars, final Exchange exchange) {
		List<Car> carMappedByNameAndPrice = cars.stream().map(c -> {
			Car car = new Car(c.getName(), c.getPrice());
			return car;
		}).collect(Collectors.toList());

		String sortType = getValue(exchange, SORT);
		sortCars(sortType, carMappedByNameAndPrice);

		exchange.getIn().setBody(carMappedByNameAndPrice);
	}

	private void mappBySpec(final List<Car> cars, final Exchange exchange) {
		List<Car> carMappedBySpec = cars.stream().map(c -> {
			Car car = new Car(c.getSipp(), c.getName(), c.getCarType(), c.getCarSpec());
			return car;
		}).collect(Collectors.toList());
		exchange.getIn().setBody(carMappedBySpec);
	}

	private void sortCars(final String sortType, final List<Car> vehicleSortedColl) {
		if (sortType.equalsIgnoreCase(RentalCarsConstants.DESC_SORTING)) {
			Collections.sort(vehicleSortedColl, new PriceComparatorDESC());
		} else {
			Collections.sort(vehicleSortedColl, new PriceComparatorASC());
		}
	}

}
