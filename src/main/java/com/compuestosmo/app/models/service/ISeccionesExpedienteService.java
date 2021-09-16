package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.SeccionesExpediente;

public interface ISeccionesExpedienteService {

	public List<SeccionesExpediente> findall();

	public void save(SeccionesExpediente seccionesExpediente);
	
	public void savePrueba(PruebasMOF pruebamof);

	public SeccionesExpediente findOne(Long id);

	public void delete(Long id);
	
	public List<SeccionesExpediente> findSeccionesByExpedienteId(Long id);
}
