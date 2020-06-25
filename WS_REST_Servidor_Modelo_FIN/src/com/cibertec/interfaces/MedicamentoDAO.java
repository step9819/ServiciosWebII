package com.cibertec.interfaces;
import java.util.List;

import com.cibertec.entidad.Medicamento;
public interface MedicamentoDAO {
	public int saveMedicamento(Medicamento bean);
	public List<Medicamento> listAllMedicamentoXCantidad(int c1,int c2 );
}

