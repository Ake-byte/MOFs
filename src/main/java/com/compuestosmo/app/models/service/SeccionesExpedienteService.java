package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IPruebasMOFDAO;
import com.compuestosmo.app.models.dao.ISeccionesExpedienteDAO;
import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.SeccionesExpediente;

@Service
public class SeccionesExpedienteService implements ISeccionesExpedienteService {

	@Autowired
	private ISeccionesExpedienteDAO seccionesEDAO;
	
	@Autowired
	private IPruebasMOFDAO pruebasMOFDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<SeccionesExpediente> findall() {
		// TODO Auto-generated method stub
		return (List<SeccionesExpediente>) seccionesEDAO.findAll();
	}

	@Override
	@Transactional
	public void save(SeccionesExpediente seccionesExpediente) {
		// TODO Auto-generated method stub
		seccionesEDAO.save(seccionesExpediente);
	}

	@Override
	public void savePrueba(PruebasMOF pruebamof) {
		// TODO Auto-generated method stub
		pruebasMOFDAO.save(pruebamof);
	}

	@Override
	@Transactional(readOnly=true)
	public SeccionesExpediente findOne(Long id) {
		// TODO Auto-generated method stub
		return seccionesEDAO.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		seccionesEDAO.deleteById(id);

	}

}
