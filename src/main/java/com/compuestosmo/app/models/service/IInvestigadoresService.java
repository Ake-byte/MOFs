package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.Investigador;
import com.compuestosmo.app.models.entity.Role;


public interface IInvestigadoresService {

	public List<Investigador> findall();

	public void save(Investigador investigador);
	
	public void saveUsuarioRole(Role role);

	public Investigador findOne(Long id);
	
	public Investigador findByRoleID(Long id);

	public void delete(Long id);
}
