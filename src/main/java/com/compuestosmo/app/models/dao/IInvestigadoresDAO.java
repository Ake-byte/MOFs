package com.compuestosmo.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.compuestosmo.app.models.entity.Investigador;

public interface IInvestigadoresDAO extends CrudRepository<Investigador, Long>{

	@Query("select i from Investigador i where i.roles.id = ?1")
	public Investigador findByRoleID(Long id);
}
