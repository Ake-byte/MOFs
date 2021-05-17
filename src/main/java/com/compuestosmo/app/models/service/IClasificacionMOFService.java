package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.ClasificacionMOF;

public interface IClasificacionMOFService {

	public List<ClasificacionMOF> findall();
	
	public void save(ClasificacionMOF clasificacionMOF);
	
	public ClasificacionMOF findOne(Long id);
	
	public void delete(Long id);
}
