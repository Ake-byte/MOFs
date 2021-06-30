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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	//@NotEmpty
	@Column(name = "nombre")
	private String nombre;
	
	//@NotEmpty
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;
	
	//@NotEmpty
	@Column(name = "apellido_materno")
	private String apellidoMaterno;
	
	//@NotEmpty
	@Column(name = "email", unique=true)
	private String email;	
	
	//@NotEmpty
	@Column(name = "director1")
	private String director1;
	
	@Column(name = "director2")
	private String director2;
	
	@Column(name = "pwd", length=100)
	private String password;
	
	private Boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Role> roles;
	
	//@Column(name = "nombre_rol")
	//private String nombreRol;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_roles")
	private RolesUsuarios roles_usuarios;
	
	//EXPEDIENTE
	@OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExpedienteMOF> expedientesUMOF;
	
	public Usuario() {
		roles = new ArrayList<Role>();
		expedientesUMOF = new ArrayList<ExpedienteMOF>();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDirector1() {
		return director1;
	}

	public void setDirector1(String director1) {
		this.director1 = director1;
	}

	public String getDirector2() {
		return director2;
	}

	public void setDirector2(String director2) {
		this.director2 = director2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRoles(Role role) {
		roles.add(role);
	}
	
	
	public RolesUsuarios getRoles_usuarios() {
		return roles_usuarios;
	}

	public void setRoles_usuarios(RolesUsuarios roles_usuarios) {
		this.roles_usuarios = roles_usuarios;
	}


	public List<ExpedienteMOF> getExpedientesUMOF() {
		return expedientesUMOF;
	}


	public void setExpedientesUMOF(List<ExpedienteMOF> expedientesUMOF) {
		this.expedientesUMOF = expedientesUMOF;
	}

	public void addExpedientes(ExpedienteMOF expedienteMOF) {
		expedientesUMOF.add(expedienteMOF);
	}


	private static final long serialVersionUID = 1L;
}
