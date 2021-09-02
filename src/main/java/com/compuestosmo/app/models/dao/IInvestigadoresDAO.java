package com.compuestosmo.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.Investigador;

public interface IInvestigadoresDAO extends PagingAndSortingRepository<Investigador, Long>{

	@Query("select i from Investigador i where i.roles.id = ?1")
	public Investigador findByRoleID(Long id);
	
	@Query("select r from Role r where r.authority like ?1%")
	public Page<Investigador> findInvestigador(Pageable pageable);
}
