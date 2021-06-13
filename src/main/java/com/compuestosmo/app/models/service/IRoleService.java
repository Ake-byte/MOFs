package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;

public interface IRoleService {

public List<Role> findAll();
	
	public void save(Role role);
	
	public void saveUsuario(Usuario usuario);

	public Role findOne(Long id);

	public void delete(Long id);
}
