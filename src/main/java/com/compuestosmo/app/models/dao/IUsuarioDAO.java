package com.compuestosmo.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.Usuario;

public interface IUsuarioDAO extends PagingAndSortingRepository<Usuario, Long>{
	
}
