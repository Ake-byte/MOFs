package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IUsuarioDAO;
import com.compuestosmo.app.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuariodao;
	
	@Override
	public List<Usuario> findall() {
		return (List<Usuario>) usuariodao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuariodao.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Long id) {
		return usuariodao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		usuariodao.deleteById(id);

	}

	@Override
	public Usuario findByEmail(String email) {
		
		return usuariodao.findByEmail(email);
	}

}
