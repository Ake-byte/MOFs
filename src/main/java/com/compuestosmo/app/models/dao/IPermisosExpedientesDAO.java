package com.compuestosmo.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.compuestosmo.app.models.entity.PermisosExpediente;

public interface IPermisosExpedientesDAO extends CrudRepository<PermisosExpediente, Long>{
	@Query("SELECT p FROM PermisosExpediente p WHERE p.permiso = false")
	public List<PermisosExpediente> findAllEnabled();
	
}
