package com.rentalcars.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import lombok.Data;

@JsonPropertyOrder({ "sipp", "name", "price", "supplier", "rating" })
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class Vehicle {

	private String sipp;
	private String name;
	private Float price;
	private String supplier;
	private Float rating;

}
