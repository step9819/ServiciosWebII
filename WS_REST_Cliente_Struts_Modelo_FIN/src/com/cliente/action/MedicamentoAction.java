package com.cliente.action;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.cibertec.entidad.Medicamento;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("dawi")
public class MedicamentoAction extends ActionSupport{
	
	private Medicamento[] listaMedicamentos; 
	private Medicamento medicamento;
	
	//
	private int cantidad1,cantidad2;
	
	private String REST_MEDICAMENTO="http://localhost:8085/WS_REST_Servidor_Modelo/rest/medicamento/";
	
	
	private HttpClient httpClient;
	
	public MedicamentoAction() {
		httpClient=HttpClientBuilder.create().build();
	}
	
	@Action(value="/listaMedicamentosXRangoDeCantidad", results= {@Result(name="ok",type="json")})
	public String listaMedicamentosXRangoDeCantidad() throws ClientProtocolException, IOException {
		HttpGet http=new HttpGet(REST_MEDICAMENTO+"consulta/"+cantidad1+"/"+cantidad2);
		HttpResponse response=httpClient.execute(http);
		//
		String json=EntityUtils.toString(response.getEntity());
		//
		Gson gson=new Gson();
		listaMedicamentos=gson.fromJson(json, Medicamento[].class);
		return "ok";
	}
	@Action(value="/saveMedicamento", results= {@Result(name="ok",type="redirect",location="/medicamento.jsp")})
	public String saveMedicamento() throws ClientProtocolException, IOException {
			HttpPost http=new HttpPost(REST_MEDICAMENTO+"registrar");
			Gson gson=new Gson();
			String JSONDocente=gson.toJson(medicamento);
			StringEntity string=new StringEntity(JSONDocente.toString(),"UTF-8");
			http.setHeader("Content-Type","application/json;charset=UTF-8");
			http.setEntity(string);
			HttpResponse response=httpClient.execute(http);
			//dataMensaje=EntityUtils.toString(response.getEntity());
		return "ok";	
	}
	
	
	
	
	public Medicamento[] getListaMedicamentos() {
		return listaMedicamentos;
	}

	public void setListaMedicamentos(Medicamento[] listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public int getCantidad1() {
		return cantidad1;
	}

	public void setCantidad1(int cantidad1) {
		this.cantidad1 = cantidad1;
	}

	public int getCantidad2() {
		return cantidad2;
	}

	public void setCantidad2(int cantidad2) {
		this.cantidad2 = cantidad2;
	}
	
	
}








