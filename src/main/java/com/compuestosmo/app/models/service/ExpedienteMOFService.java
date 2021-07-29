package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IExpedienteMOFDAO;
import com.compuestosmo.app.models.dao.ISeccionesExpedienteDAO;
import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.SeccionesExpediente;

@Service
public class ExpedienteMOFService implements IExpedienteMOFService{

	@Autowired
	private IExpedienteMOFDAO expedienteMOFDAO;
	
	@Autowired
	private ISeccionesExpedienteDAO seccionesEDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<ExpedienteMOF> findall() {
		return (List<ExpedienteMOF>) expedienteMOFDAO.findAll();
	}

	@Override
	@Transactional
	public void save(ExpedienteMOF expedienteMOF) {
		expedienteMOFDAO.save(expedienteMOF);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ExpedienteMOF findOne(Long id) {
		return expedienteMOFDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		expedienteMOFDAO.deleteById(id);
		
	}

	@Override
	@Transactional
	public void saveSeccion(SeccionesExpediente seccionExpediente) {
		// TODO Auto-generated method stub
		seccionesEDAO.save(seccionExpediente);
	}

}
