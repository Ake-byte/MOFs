package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IMOFDAO;
import com.compuestosmo.app.models.entity.MOF;

@Service
public class MOFService implements IMOFService{

	@Autowired
	private IMOFDAO mofdao;
	
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

}
