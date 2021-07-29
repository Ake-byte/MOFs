package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findall();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);
	
	public Usuario findByEmail(String email);

	public void delete(Long id);
	
	public List<MOF> findByNombre(String term);
	
	public List<Usuario> findByNombreU(String term);
	
	public List<Role> findByRolUsuario(String authority);
	
	public Page<Role> findUsuarioByRole(String authority, Pageable pageable);
	
}
