package org.evm.biz.webService.webservice.impl;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
	@GET
	@Path("/echo/{input}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String ping(@PathParam("input") String input) {
		return input + ":in server!";
	}

	@POST
	@Path("/echo/")
	@Produces({ MediaType.APPLICATION_JSON })
	public String pingPost(String input) {
		return input + ":in server!";
	}
}
