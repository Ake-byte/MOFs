package com.compuestosmo.app.models.dao;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.MOF;

public interface IMOFDAO extends CrudRepository<MOF, Long>{

}
