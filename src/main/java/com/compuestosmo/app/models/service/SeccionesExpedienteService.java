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
		return (List<SeccionesExpediente>) seccionesEDAO.findAll();
	}

	@Override
	@Transactional
	public void save(SeccionesExpediente seccionesExpediente) {
		seccionesEDAO.save(seccionesExpediente);
	}

	@Override
	@Transactional
	public void savePrueba(PruebasMOF pruebamof) {
		pruebasMOFDAO.save(pruebamof);
	}

	@Override
	@Transactional(readOnly=true)
	public SeccionesExpediente findOne(Long id) {
		return seccionesEDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		seccionesEDAO.deleteById(id);

	}

	@Override
	@Transactional(readOnly=true)
	public List<SeccionesExpediente> findSeccionesByExpedienteId(Long id) {
		return seccionesEDAO.findSeccionesByExpedienteId(id);
	}

}
