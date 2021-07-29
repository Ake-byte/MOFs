package com.compuestosmo.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.MOF;

public interface IMOFDAO extends CrudRepository<MOF, Long>{

	@Query("select m from MOF m where m.nombreCompuesto like %?1%")
	public List<MOF> findByNombre(String term);
}
