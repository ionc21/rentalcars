package com.rentalcars.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component(ReaFromFileRoute.BEAN_NAME)
public class ReaFromFileRoute extends RouteBuilder {
	public static final String BEAN_NAME = "reaFromFileRoute";
	public static final String FILE_ENDPOINT = "file:vehicle?noop=false";

	@Override
	public void configure() throws Exception {
		from(FILE_ENDPOINT).process(new Processor() {

			@Override
			public void process(final Exchange exchange) throws Exception {
				exchange.getIn().getBody(String.class);
			}
		}).log("body: ${body}");
	}

}
