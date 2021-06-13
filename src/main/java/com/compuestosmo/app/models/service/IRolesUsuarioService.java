package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.RolesUsuarios;

public interface IRolesUsuarioService {

public List<RolesUsuarios> findAll();
	
	public void save(RolesUsuarios roleUsuario);	

	public RolesUsuarios findOne(Long id);

	public void delete(Long id);
}
