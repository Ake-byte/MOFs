package com.compuestosmo.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.compuestosmo.app.models.entity.SeccionesExpediente;

public interface ISeccionesExpedienteDAO extends CrudRepository<SeccionesExpediente, Long>{

	@Query("select s from SeccionesExpediente s where s.expedientes.id = ?1")
	public List<SeccionesExpediente> findSeccionesByExpedienteId(Long id);
}
