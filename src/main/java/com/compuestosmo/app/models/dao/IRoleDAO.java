package com.compuestosmo.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.compuestosmo.app.models.entity.Role;

public interface IRoleDAO extends CrudRepository<Role, Long>{

	@Query("select r from Role r where r.authority like ?1%")
	public List<Role> findByRolUsuario(String authority);
	
	@Query("select r from Role r where r.authority like ?1%")
	public Page<Role> findUsuarioByRole(String authority, Pageable pageable);

}
