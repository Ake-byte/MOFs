package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.PermisosExpediente;
import com.compuestosmo.app.models.entity.Usuario;

public interface IPermisosExpedientesService {

	public List<PermisosExpediente> findAll();

	public void save(PermisosExpediente permisosExpediente);

	public void saveExpediente(ExpedienteMOF expedienteMOF);
	
	public void saveUsuario(Usuario usuario);

	public PermisosExpediente findOne(Long id);

	public void delete(Long id);
	
	public List<PermisosExpediente> findAllEnabled();
	
}
