package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IRolesUsuarioDAO;
import com.compuestosmo.app.models.entity.RolesUsuarios;

@Service
public class RolesUsuarioService implements IRolesUsuarioService{

	@Autowired
	private IRolesUsuarioDAO roleUsuarioDAO;

	@Override
	public List<RolesUsuarios> findAll() {
		// TODO Auto-generated method stub
		return (List<RolesUsuarios>) roleUsuarioDAO.findAll();
	}

	@Override
	@Transactional
	public void save(RolesUsuarios roleUsuario) {
		// TODO Auto-generated method stub
		roleUsuarioDAO.save(roleUsuario);
	}

	@Override
	@Transactional(readOnly=true)
	public RolesUsuarios findOne(Long id) {
		// TODO Auto-generated method stub
		return roleUsuarioDAO.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		roleUsuarioDAO.deleteById(id);
	}
	
	

}
