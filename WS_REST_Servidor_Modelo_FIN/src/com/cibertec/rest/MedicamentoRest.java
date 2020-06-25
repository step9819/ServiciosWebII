package com.cibertec.rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cibertec.dao.MedicamentoDAOImpl;
import com.cibertec.entidad.Medicamento;

@Path("/medicamento")
public class MedicamentoRest {
	private MedicamentoDAOImpl dao;
	
	public MedicamentoRest() {
		dao=new MedicamentoDAOImpl();
	}
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int registrarMedicamento(Medicamento bean) {
		return dao.saveMedicamento(bean);
	}
	
	@GET
	@Path("/consulta/{can1}/{can2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaMedicamentos(@PathParam("can1") int c1,@PathParam("can2") int c2){
		return  Response.ok(dao.listAllMedicamentoXCantidad(c1, c2)).build();
	}
}



