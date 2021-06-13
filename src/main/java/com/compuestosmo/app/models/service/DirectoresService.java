package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.compuestosmo.app.models.dao.IDirectoresDAO;
import com.compuestosmo.app.models.entity.Directores;

public class DirectoresService implements IDirectoresService {

	@Autowired
	private IDirectoresDAO directoresdao;
	
	@Override
	public List<Directores> findall() {
		return (List<Directores>) directoresdao.findAll();
	}

	@Override
	@Transactional
	public void save(Directores usuario) {
		directoresdao.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Directores findOne(Long id) {
		return directoresdao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		directoresdao.deleteById(id);
	}

}
