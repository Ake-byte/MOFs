package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.SeccionesExpediente;

public interface IExpedienteMOFService {
	public List<ExpedienteMOF> findall();

	public void save(ExpedienteMOF expedienteMOF);
	
	public void saveSeccion(SeccionesExpediente seccionExpediente);

	public ExpedienteMOF findOne(Long id);

	public void delete(Long id);
}
