package com.compuestosmo.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.compuestosmo.app.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
	
	public Usuario findByEmail(String email);
}
