package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IMOFDAO;
import com.compuestosmo.app.models.dao.IRoleDAO;
import com.compuestosmo.app.models.dao.IUsuarioDAO;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuariodao;
	
	@Autowired
	private IMOFDAO mofdao;
	
	@Autowired
	private IRoleDAO roledao;
	
	@Override
	@Transactional(readOnly=true)
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
	@Transactional
	public void delete(Long id) {
		usuariodao.deleteById(id);

	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByEmail(String email) {
		
		return usuariodao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findByNombreU(String term) {
		// TODO Auto-generated method stub
		return usuariodao.findByNombreU(term);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Role> findByRolUsuario(String authority) {
		
		return roledao.findByRolUsuario(authority);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Role> findUsuarioByRole(String authority, Pageable pageable) {
		// TODO Auto-generated method stub
		return roledao.findUsuarioByRole(authority, pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByResetPasswordToken(String token) {
		// TODO Auto-generated method stub
		return usuariodao.findByResetPasswordToken(token);
	}

}
