package com.rentalcars.route.test;

import static java.text.MessageFormat.format;

import java.util.List;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import com.rentalcars.bo.CarBO;
import com.rentalcars.constants.RentalCarsConstants;
import com.rentalcars.processor.VehicleProcessor;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring/camel-context.xml" }) @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RentalCarMainRouteTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resulEndpoint;

    @Produce(uri = "direct:start")
    private ProducerTemplate producerTemplate;
    @Autowired
    private CamelContext camelContext;
    @Autowired
    private VehicleProcessor vehicleProcessor;

    @Test
    public void testGetCarsByNameAndPrice() throws Exception {
        Exchange exchange = new DefaultExchange(camelContext);
        exchange.getIn().setHeader(Exchange.HTTP_QUERY,
                format("{0}={1}", RentalCarsConstants.SORT, RentalCarsConstants.ASC_SORTING));
        exchange.getIn().setHeader(Exchange.HTTP_PATH, format("/vehicle/{0}", RentalCarsConstants.NAME_AND_PRICE));
        exchange.getIn().setBody("");

        Exchange responseExchange = producerTemplate.send(exchange);


        List<CarBO> cars = responseExchange.getIn().getBody(List.class);
        assertNotNull(cars);
        assertEquals("ChevroletSpark", cars.get(0).getName());
        assertEquals(Float.valueOf(120.16F), cars.get(0).getPrice());
    }

    @Test
    public void testGetCarsSpec() throws Exception {
        Exchange exchange = new DefaultExchange(camelContext);
        exchange.getIn().setHeader(Exchange.HTTP_PATH, format("/vehicle/{0}", RentalCarsConstants.SPEC));
        exchange.getIn().setBody("");

        Exchange responseExchange = producerTemplate.send(exchange);

        List<CarBO> cars = responseExchange.getIn().getBody(List.class);
        assertNotNull(cars);
        assertEquals("Ford Focus", cars.get(0).getName());
        assertEquals("CDMR", cars.get(0).getSipp());
    }

    @Test
    public void testGetHighestRatedSupplier() throws Exception {
        Exchange exchange = new DefaultExchange(camelContext);
        exchange.getIn().setHeader(Exchange.HTTP_QUERY,
                format("{0}={1}", RentalCarsConstants.SORT, RentalCarsConstants.DESC_SORTING));
        exchange.getIn().setHeader(Exchange.HTTP_PATH, format("/vehicle/{0}", RentalCarsConstants.HIGH_RATED_SUPPLIER));
        exchange.getIn().setBody("");

        Exchange responseExchange = producerTemplate.send(exchange);

        List<CarBO> cars = responseExchange.getIn().getBody(List.class);
        assertNotNull(cars);
        assertEquals("Kia Picanto", cars.get(0).getName());
        assertEquals("Mini", cars.get(0).getCarType());
    }

    @Test
    public void testGetScore() throws Exception {
        Exchange exchange = new DefaultExchange(camelContext);
        exchange.getIn().setHeader(Exchange.HTTP_QUERY,
                format("{0}={1}", RentalCarsConstants.SORT, RentalCarsConstants.DESC_SORTING));
        exchange.getIn().setHeader(Exchange.HTTP_PATH, format("/vehicle/{0}", RentalCarsConstants.SCORE));
        exchange.getIn().setBody("");

        Exchange responseExchange = producerTemplate.send(exchange);

        List<CarBO> cars = responseExchange.getIn().getBody(List.class);
        assertNotNull(cars);
        assertEquals("Ford Galaxy", cars.get(0).getName());
        assertEquals(Float.valueOf(7f), Float.valueOf(cars.get(0).getScore()));
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override public void configure() {
                from("direct:start").process(vehicleProcessor).to("mock:result");
            }
        };
    }

}
