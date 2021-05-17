package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.MOF;

public interface IMOFService {
	public List<MOF> findAll();
	
	public void save(MOF mof);

	public MOF findOne(Long id);

	public void delete(Long id);
}
