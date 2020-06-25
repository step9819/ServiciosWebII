package com.cibertec.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.cibertec.entidad.Medicamento;
import com.cibertec.interfaces.MedicamentoDAO;
import utils.MysqlDBConexion;

public class MedicamentoDAOImpl implements MedicamentoDAO{
	@Override
	public int saveMedicamento(Medicamento bean){
		int estado=-1;
		Connection cn=null;
		CallableStatement cstm=null;
		try {
			cn=MysqlDBConexion.getConexion();
			String sql="call sp_saveMedicamento(?,?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setString(1,bean.getNombre());
			cstm.setDouble(2,bean.getPrecio());
			cstm.setInt(3,bean.getCantidad());
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
	public List<Medicamento> listAllMedicamentoXCantidad(int c1, int c2) {
		List<Medicamento> lista=new ArrayList<Medicamento>();
		Medicamento bean=null;
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn=MysqlDBConexion.getConexion();
			String sql="call sp_listarMedicamentoPorCantidad(?,?)";
			cstm=cn.prepareCall(sql);
			cstm.setInt(1,c1);
			cstm.setInt(2,c2);
			rs=cstm.executeQuery();
			while(rs.next()) {
				bean=new Medicamento();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setPrecio(rs.getDouble(3));
				bean.setCantidad(rs.getInt(4));
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


