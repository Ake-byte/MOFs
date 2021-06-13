package com.compuestosmo.app.models.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IRoleDAO;
import com.compuestosmo.app.models.dao.IUsuarioDAO;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public List<Role> findAll() {
		
		return (List<Role>) roleDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDAO.save(role);
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDAO.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Role findOne(Long id) {
		// TODO Auto-generated method stub
		return roleDAO.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		roleDAO.deleteById(id);
	}

}
