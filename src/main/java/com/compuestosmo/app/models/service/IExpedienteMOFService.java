package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.PruebasMOF;

public interface IExpedienteMOFService {
	public List<ExpedienteMOF> findall();

	public void save(ExpedienteMOF expedienteMOF);
	
	public void savePrueba(PruebasMOF pruebamof);

	public ExpedienteMOF findOne(Long id);

	public void delete(Long id);
}
