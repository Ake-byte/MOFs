package com.compuestosmo.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compuestosmo.app.models.dao.IInvestigadoresDAO;
import com.compuestosmo.app.models.dao.IRoleDAO;
import com.compuestosmo.app.models.entity.Investigador;
import com.compuestosmo.app.models.entity.Role;

@Service
public class InvestigadoresService implements IInvestigadoresService {

	@Autowired
	private IInvestigadoresDAO investigadoresDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Investigador> findall() {
		return (List<Investigador>) investigadoresDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Investigador investigador) {
		investigadoresDAO.save(investigador);
	}

	@Override
	@Transactional
	public void saveUsuarioRole(Role role) {
		roleDAO.save(role);
	}

	@Override
	@Transactional(readOnly=true)
	public Investigador findOne(Long id) {
		return investigadoresDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		investigadoresDAO.deleteById(id);
	}

	@Override
	public Investigador findByRoleID(Long id) {
		return investigadoresDAO.findByRoleID(id);
	}


}
