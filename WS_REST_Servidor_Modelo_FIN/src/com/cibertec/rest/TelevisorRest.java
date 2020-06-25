package com.cibertec.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cibertec.dao.TelevisorDAOlmpl;
import com.cibertec.entidad.Televisor;

@Path("/televisor")
public class TelevisorRest {
private TelevisorDAOlmpl dao;
	
	public TelevisorRest() {
		dao=new TelevisorDAOlmpl();
	}
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int registrarTelevisor(Televisor bean) {
		return dao.saveTelevisor(bean);
	}
	
	@GET
	@Path("/consulta/{stock1}/{stock2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaMedicamentos(@PathParam("stock1") int stock1,@PathParam("stock2") int stock2){
		return  Response.ok(dao.listAllTelevisoresXStock(stock1, stock2)).build();
	}

}
