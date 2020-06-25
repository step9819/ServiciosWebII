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

import com.cibertec.entidad.Televisor;
import com.google.gson.Gson;

@ParentPackage("dawi")
public class TelevisorAction {
	
	private Televisor[] listaTelevisores; 
	private Televisor televisor;
	
	//
	private int stock1,stock2;
	
	private String REST_TELEVISOR="http://localhost:8080/WS_REST_Servidor_Modelo_FIN/rest/televisor/";
	
	
	private HttpClient httpClient;
	
	public TelevisorAction() {
		httpClient=HttpClientBuilder.create().build();
	}
	
	@Action(value="/listaTelevisoresXStock", results= {@Result(name="ok",type="json")})
	public String listaTelevisoresXRangoDeStock() throws ClientProtocolException, IOException {
		HttpGet http=new HttpGet(REST_TELEVISOR+"consulta/"+stock1+"/"+stock2);
		HttpResponse response=httpClient.execute(http);
		//
		String json=EntityUtils.toString(response.getEntity());
		//
		Gson gson=new Gson();
		listaTelevisores=gson.fromJson(json, Televisor[].class);
		return "ok";
	}
	@Action(value="/saveTelevisor", results= {@Result(name="ok",type="redirect",location="/televisor.jsp")})
	public String saveTelevisor() throws ClientProtocolException, IOException {
			HttpPost http=new HttpPost(REST_TELEVISOR+"registrar");
			Gson gson=new Gson();
			String JSONDocente=gson.toJson(televisor);
			StringEntity string=new StringEntity(JSONDocente.toString(),"UTF-8");
			http.setHeader("Content-Type","application/json;charset=UTF-8");
			http.setEntity(string);
			HttpResponse response=httpClient.execute(http);
			//dataMensaje=EntityUtils.toString(response.getEntity());
		return "ok";	
	}

	public Televisor[] getListaTelevisores() {
		return listaTelevisores;
	}

	public void setListaTelevisores(Televisor[] listaTelevisores) {
		this.listaTelevisores = listaTelevisores;
	}

	public Televisor getTelevisor() {
		return televisor;
	}

	public void setTelevisor(Televisor televisor) {
		this.televisor = televisor;
	}

	public int getStock1() {
		return stock1;
	}

	public void setStock1(int stock1) {
		this.stock1 = stock1;
	}

	public int getStock2() {
		return stock2;
	}

	public void setStock2(int stock2) {
		this.stock2 = stock2;
	}
	
	

}
