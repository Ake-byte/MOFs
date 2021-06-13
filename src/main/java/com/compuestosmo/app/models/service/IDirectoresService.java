package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.Directores;


public interface IDirectoresService {

	public List<Directores> findall();

	public void save(Directores usuario);

	public Directores findOne(Long id);

	public void delete(Long id);
}
