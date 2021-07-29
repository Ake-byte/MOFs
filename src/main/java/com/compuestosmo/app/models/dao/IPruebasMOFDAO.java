package com.compuestosmo.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.PruebasMOF;

public interface IPruebasMOFDAO extends PagingAndSortingRepository<PruebasMOF, Long>{

	@Query("select p from PruebasMOF p where p.secciones_expedientes.id = ?1")
	public Page<PruebasMOF> findPruebasById(Long id, Pageable pageable);
}
