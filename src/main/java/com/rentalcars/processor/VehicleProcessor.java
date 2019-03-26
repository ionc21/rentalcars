package com.rentalcars.processor;

import static com.rentalcars.constants.RentalCarsConstants.*;
import static com.rentalcars.utils.RentalCarsUtil.*;
import static java.util.Comparator.*;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentalcars.bo.Car;
import com.rentalcars.bo.CarBO;
import com.rentalcars.model.json.Vehicle;
import com.rentalcars.model.json.VehicleResp;
import com.rentalcars.transformer.RentalCarTrasnformer;

@Component
public class VehicleProcessor implements Processor {

    private static final String FILE_PATH = "src/main/resources/vehicle/vehicle.json";
    private final ObjectMapper objectMapper;

    @Autowired
    public VehicleProcessor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void process(final Exchange exchange) throws Exception {
        final String queryString = exchange.getIn().getHeader(Exchange.HTTP_QUERY, String.class);
        final MultivaluedMap<String, String> queryMap = JAXRSUtils.getStructuredParams(queryString, "&", false, false);

        for (Entry<String, List<String>> entry : queryMap.entrySet()) {
            exchange.setProperty(entry.getKey(), entry.getValue());
        }

        final VehicleResp vehicleRespFromFile = objectMapper.readValue(new File(FILE_PATH), VehicleResp.class);

        List<Car> cars = getListOfCars(vehicleRespFromFile);

        String property = getPathProperty(exchange);

        switch (property) {
        case NAME_AND_PRICE:
            mapByNameAndPrice(cars, exchange);
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

    private List<Car> getListOfCars(final VehicleResp vehicleRespFromFile) {
        List<Vehicle> vehicles = vehicleRespFromFile.getVehicleList();
        List<CarBO> carBOList = new ArrayList<>(vehicles.size());
        final List<Car> carList = new ArrayList<>(vehicles.size());
        CollectionUtils.collect(vehicles, RentalCarTrasnformer.VEHICLE_TO_CAR, carList);
        CollectionUtils.collect(carList, RentalCarTrasnformer.CAR_TO_CAR_BO, carBOList);

        return carList;
    }

    private String getPathProperty(final Exchange exchange) {
        String httpPath = exchange.getIn().getHeader(Exchange.HTTP_PATH, String.class);
        String[] propArray = StringUtils.split(httpPath, "/");
        String property = propArray[propArray.length - 1];
        return property;
    }

    private void mapCarsByScore(final List<Car> cars, final Exchange exchange) {
        List<CarBO> carsByScore = cars.stream()
                .map(c -> RentalCarTrasnformer.CAR_TO_CAR_BO.transform(c))
                .collect(Collectors.toList());
        exchange.getIn().setBody(getCarsByScoreDESC(carsByScore));
    }

    private void mapByHighestRatedSupplier(final List<Car> cars, final Exchange exchange) {
        List<CarBO> highestRatedSupplierOfCar = getHighestRatedSupplier(cars)
                .stream()
                .map(c ->RentalCarTrasnformer.CAR_TO_CAR_BO.transform(c))
                .collect(Collectors.toList());
        exchange.getIn().setBody(highestRatedSupplierOfCar);
    }

    private void mapByNameAndPrice(final List<Car> cars, final Exchange exchange) {
        List<CarBO> carMappedByNameAndPrice = cars.stream()
                .map(c ->RentalCarTrasnformer.CAR_TO_CAR_BO.transform(c))
                .collect(Collectors.toList());
        String sortType = getValue(exchange, SORT);
        sortCars(sortType, carMappedByNameAndPrice);

        exchange.getIn().setBody(carMappedByNameAndPrice);
    }

    private void mappBySpec(final List<Car> cars, final Exchange exchange) {
        List<CarBO> carMappedBySpec = cars.stream()
                .map(c -> RentalCarTrasnformer.CAR_TO_CAR_BO.transform(c))
                .collect(Collectors.toList());
        exchange.getIn().setBody(carMappedBySpec);
    }

    private void sortCars(final String sortType, final List<CarBO> vehicleSortedColl) {
        if (sortType.equalsIgnoreCase(DESC_SORTING)) {
            Collections.sort(vehicleSortedColl, comparing(CarBO::getPrice, reverseOrder()));
        } else {
            Collections.sort(vehicleSortedColl, comparing(CarBO::getPrice, naturalOrder()));
        }
    }

}
