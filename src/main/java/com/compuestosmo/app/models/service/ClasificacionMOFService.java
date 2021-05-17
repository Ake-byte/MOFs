package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IClasificacionMOFDAO;
import com.compuestosmo.app.models.entity.ClasificacionMOF;

@Service
public class ClasificacionMOFService implements IClasificacionMOFService{
	@Autowired
	private IClasificacionMOFDAO clasificacionMOFDAO;

	@Override
	@Transactional(readOnly=true)
	public List<ClasificacionMOF> findall() {
		return (List<ClasificacionMOF>) clasificacionMOFDAO.findAll();
	}

	@Override
	@Transactional
	public void save(ClasificacionMOF clasificacionMOF) {
		clasificacionMOFDAO.save(clasificacionMOF);
		
	}

	@Override
	@Transactional(readOnly=true)
	public ClasificacionMOF findOne(Long id) {
		return clasificacionMOFDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clasificacionMOFDAO.deleteById(id);
		
	}
}
