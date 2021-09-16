package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.compuestosmo.app.models.entity.PruebasMOF;


public interface IPruebasMOFService {

	public List<PruebasMOF> findall();
	
	public Page<PruebasMOF> findPruebasById(Long id, Pageable pageable);

	public void save(PruebasMOF expedienteMOF);

	public PruebasMOF findOne(Long id);

	public void delete(Long id);
	
	public List<PruebasMOF> findPruebasBySeccionId(Long id);
}
