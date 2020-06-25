package com.cibertec.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.entidad.Medicamento;
import com.cibertec.entidad.Televisor;
import com.cibertec.interfaces.TelevisorDAO;

import utils.MysqlDBConexion;

public class TelevisorDAOlmpl implements TelevisorDAO{

	@Override
	public int saveTelevisor(Televisor bean) {
		int estado=-1;
		Connection cn=null;
		CallableStatement cstm=null;
		try {
			cn=MysqlDBConexion.getConexion();
			String sql="call sp_saveTelevisor(?,?,?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1,bean.getNombre());
			cstm.setDouble(2,bean.getPrecio());
			cstm.setInt(3,bean.getStock());
			cstm.setString(4, bean.getFecha());
			estado=cstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();	
		}
		finally{
			try {
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return estado;
	}

	@Override
	public List<Televisor> listAllTelevisoresXStock(int stock1, int stock2) {
		List<Televisor> lista=new ArrayList<Televisor>();
		Televisor bean=null;
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MysqlDBConexion.getConexion();
			String sql="call sp_listarTelevisoresPorStock(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1,stock1);
			cstm.setInt(2,stock2);
			rs=cstm.executeQuery();
			while(rs.next()) {
				bean=new Televisor();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setPrecio(rs.getDouble(3));
				bean.setStock(rs.getInt(4));
				bean.setFecha(rs.getString(5));
				lista.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(cstm!=null) cstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

}
