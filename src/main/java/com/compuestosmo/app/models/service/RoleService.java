package com.compuestosmo.app.models.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IInvestigadoresDAO;
import com.compuestosmo.app.models.dao.IRoleDAO;
import com.compuestosmo.app.models.dao.IUsuarioDAO;
import com.compuestosmo.app.models.entity.Investigador;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private IInvestigadoresDAO investigadorDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Role> findAll() {
		
		return (List<Role>) roleDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Role role) {
		roleDAO.save(role);
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Role findOne(Long id) {
		return roleDAO.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		roleDAO.deleteById(id);
	}

	@Override
	@Transactional
	public void saveInvestigador(Investigador investigador) {
		investigadorDAO.save(investigador);
	}

	@Override
	public Role findRoleByUserId(Long id) {
		return roleDAO.findRoleByUserId(id);
	}

}
