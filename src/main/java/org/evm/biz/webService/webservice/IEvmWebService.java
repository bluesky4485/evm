package org.evm.biz.webService.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@WebService
@Path(value = "/evmws")
public interface IEvmWebService {
	/**
	 * 
	 * update by jerry.x 2016年10月14日
	 * 
	 * @param para
	 * @return
	 *
	 */
	@GET
	@Path("/CodeServices/{para}")
	@Produces({ MediaType.APPLICATION_JSON })  
	String CodeServices(@PathParam("para") String para);
}
