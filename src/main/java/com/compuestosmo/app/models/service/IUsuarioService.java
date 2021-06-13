package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findall();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);

	public void delete(Long id);
}
