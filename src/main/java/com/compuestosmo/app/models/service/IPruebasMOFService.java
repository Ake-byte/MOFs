package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.PruebasMOF;


public interface IPruebasMOFService {

	public List<PruebasMOF> findall();

	public void save(PruebasMOF expedienteMOF);

	public PruebasMOF findOne(Long id);

	public void delete(Long id);
}
