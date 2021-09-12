package com.compuestosmo.app.models.service;

import java.util.List;

import com.compuestosmo.app.models.entity.Investigador;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;

public interface IRoleService {

public List<Role> findAll();

public void save(Role role);

public void saveUsuario(Usuario usuario);

public void saveInvestigador(Investigador investigador);

public Role findOne(Long id);

public void delete(Long id);

public Role findRoleByUserId(Long id);
}
