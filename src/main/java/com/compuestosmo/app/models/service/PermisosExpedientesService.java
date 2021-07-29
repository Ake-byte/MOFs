package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IExpedienteMOFDAO;
import com.compuestosmo.app.models.dao.IPermisosExpedientesDAO;
import com.compuestosmo.app.models.dao.IUsuarioDAO;
import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.PermisosExpediente;
import com.compuestosmo.app.models.entity.Usuario;

@Service
public class PermisosExpedientesService implements IPermisosExpedientesService {

	@Autowired
	private IPermisosExpedientesDAO permisoDAO;
	
	@Autowired
	private IExpedienteMOFDAO expedienteDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<PermisosExpediente> findAll() {

		return (List<PermisosExpediente>) permisoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(PermisosExpediente permisosExpediente) {

		permisoDAO.save(permisosExpediente);
	}

	@Override
	@Transactional
	public void saveExpediente(ExpedienteMOF expedienteMOF) {

		expedienteDAO.save(expedienteMOF);
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario usuario) {

		usuarioDAO.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public PermisosExpediente findOne(Long id) {

		return permisoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		permisoDAO.deleteById(id);
	}

	@Override
	public List<PermisosExpediente> findAllEnabled() {
		// TODO Auto-generated method stub
		return permisoDAO.findAllEnabled();
	}

}
