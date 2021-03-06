package com.compuestosmo.app.models.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IPruebasMOFDAO;
import com.compuestosmo.app.models.entity.PruebasMOF;

@Service
public class PruebasMOFService implements IPruebasMOFService {

	@Autowired
	private IPruebasMOFDAO pruebasMOFDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<PruebasMOF> findall() {
		return (List<PruebasMOF>) pruebasMOFDAO.findAll();
	}

	@Override
	@Transactional
	public void save(PruebasMOF expedienteMOF) {
		pruebasMOFDAO.save(expedienteMOF);

	}

	@Override
	@Transactional(readOnly=true)
	public PruebasMOF findOne(Long id) {
		return pruebasMOFDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pruebasMOFDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<PruebasMOF> findPruebasById(Long id, Pageable pageable) {
		return pruebasMOFDAO.findPruebasById(id, pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PruebasMOF> findPruebasBySeccionId(Long id) {
		return pruebasMOFDAO.findPruebasBySeccionId(id);
	}


}
