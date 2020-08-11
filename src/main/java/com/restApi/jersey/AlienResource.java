package com.restApi.jersey;

import java.util.List;
import java.sql.*;	


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "aliens")
public class AlienResource {
	AlienRepository repo = new AlienRepository();

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Alien> getAlien() {

		return repo.getAliens();

	}

	@GET
	@Path(value = "alien/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id) {

		return repo.getAlien(id);

	}

	@POST
	@Path(value = "alien")
	public List<Alien> createAlien(Alien alien) {
		repo.create(alien);
		return repo.getAliens();
	}

}
