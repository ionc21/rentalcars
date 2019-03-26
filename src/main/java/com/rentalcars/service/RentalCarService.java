package com.rentalcars.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Path("/vehicle")
@Service(RentalCarService.BEAN_NAME)
public class RentalCarService {

	public static final String BEAN_NAME = "rentalCarService";

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getVehicle() {
		return "Success";
	}

	@Path("/nameAndPrice")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getByNameAndPrice() {
		return "Success";
	}

	@Path("/spec")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getSpec() {
		return "Success";
	}

	@Path("/highestRatedSupplier")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getHighestRatedSupplier() {
		return "Success";
	}

	@Path("/score")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getVehiclesByScoreDESC() {
		return "Success";
	}
}
