package com.compuestosmo.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.Usuario;

public interface IUsuarioDAO extends PagingAndSortingRepository<Usuario, Long>{
	
	public Usuario findByEmail(String email);
	
	@Query("select u from Usuario u where u.nombre like %?1%")
	public List<Usuario> findByNombreU(String term);
	
}
