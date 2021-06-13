package com.compuestosmo.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.compuestosmo.app.models.entity.Role;

public interface IRoleDAO extends CrudRepository<Role, Long>{

}
