package com.rentalcars.model.json;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"sipp", "name", "price", "supplier", "rating"})
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class VehicleList {

	private String sipp;
	private String name;
	private BigDecimal price;
	private String supplier;
	private BigDecimal rating;

}
