package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IExpedienteMOFDAO;
import com.compuestosmo.app.models.dao.IMOFDAO;
import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.MOF;

@Service
public class MOFService implements IMOFService{

	@Autowired
	private IMOFDAO mofdao;
	
	@Autowired
	private IExpedienteMOFDAO expedientemofdao;
	
	@Override
	@Transactional(readOnly=true)
	public List<MOF> findAll() {
		return (List<MOF>) mofdao.findAll();
	}
	
	@Override
	@Transactional
	public void save(MOF mof) {
		mofdao.save(mof);
		
	}

	@Override
	@Transactional(readOnly=true)
	public MOF findOne(Long id) {
		return mofdao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		mofdao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void saveExpediente(ExpedienteMOF expedientemof) {
		expedientemofdao.save(expedientemof);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Page<MOF> findMOFsByClasificacionId(Long id, Pageable pageable) {
		return mofdao.findMOFsByClasificacionId(id, pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<MOF> findByTerm(String term) {
		
			return mofdao.search(term);

	}

	@Override
	public Page<MOF> findAll(Pageable pageable) {
		return mofdao.findAll(pageable);
	}


}
