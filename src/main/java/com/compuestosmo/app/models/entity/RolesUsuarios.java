package com.compuestosmo.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles_usuarios")
public class RolesUsuarios implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_roles")
	private Long id;

	// @NotEmpty
	@Column(name = "nombre_rol")
	private String nombreRol;

	// @NotEmpty
	@Column(name = "descripcion_rol")
	private String descripcionRol;
	
	@Column(name = "nombre_bd")
	private String nombreBD;
	
	@OneToMany(mappedBy="roles_usuarios" ,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//private List<Role> roles;
	private List<Usuario> usuarios;
	
	public RolesUsuarios() {
		usuarios = new ArrayList<Usuario>();
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombreRol() {
		return nombreRol;
	}



	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}



	public String getDescripcionRol() {
		return descripcionRol;
	}



	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	
	

	

	public String getNombreBD() {
		return nombreBD;
	}



	public void setNombreBD(String nombreBD) {
		this.nombreBD = nombreBD;
	}



	public List<Usuario> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}



	public void addUsuarios(Usuario usuario) {
		usuarios.add(usuario);
	}

	private static final long serialVersionUID = 1L;

}
