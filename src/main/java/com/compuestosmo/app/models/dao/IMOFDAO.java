package com.compuestosmo.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.MOF;

public interface IMOFDAO extends PagingAndSortingRepository<MOF, Long>{

	//@Query("select m from MOF m where concat(m.nombreCompuesto, ' ', m.director1, ' ', m.director2, ' ', m.investigador, ' ') LIKE %?1%")
	@Query("select m from MOF m where m.nombreCompuesto LIKE %?1%"
			+ " OR m.director1 LIKE %?1%"
            + " OR m.director2 LIKE %?1%"
			+ " OR m.investigador LIKE %?1%"
			+ " OR m.clasificacionmof.nombreClasificacion LIKE %?1%")
	public List<MOF> search(String term);
	
	@Query("select m from MOF m where m.clasificacionmof.id = ?1")
	public Page<MOF> findMOFsByClasificacionId(Long id, Pageable pageable);
}
