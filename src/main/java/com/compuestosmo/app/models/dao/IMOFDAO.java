package com.compuestosmo.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.MOF;

public interface IMOFDAO extends PagingAndSortingRepository<MOF, Long>{

	@Query("select m from MOF m where m.nombreCompuesto like %?1%")
	public List<MOF> findByNombre(String term);
	
	@Query("select m from MOF m where m.clasificacionmof.id = ?1")
	public Page<MOF> findMOFsByClasificacionId(Long id, Pageable pageable);
}
