package com.compuestosmo.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NotEmpty
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@NotEmpty
	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@NotEmpty
	@Email
	@Column(name = "email", unique=true)
	private String email;

	@NotEmpty
	//@Size(min = 6, max = 8)
	@Column(name = "pwd", length=100)
	private String password;
	
	private Boolean enabled;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    //private List<Role> roles;
	private Role roles;
	
	//PERMISOS A EXPEDIENTES QUE TIENE EL USUARIO
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PermisosExpediente> permisosExpediente;
	
	@Column(name = "reset_password_token")
    private String resetPasswordToken;
	
	public Usuario() {
		//roles = new ArrayList<Role>();
		permisosExpediente = new ArrayList<PermisosExpediente>();
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

	public Role getRoles() {
		return roles;
	}


	public void setRoles(Role roles) {
		this.roles = roles;
	}
		

	public List<PermisosExpediente> getPermisosExpediente() {
		return permisosExpediente;
	}


	public void setPermisosExpediente(List<PermisosExpediente> permisosExpediente) {
		this.permisosExpediente = permisosExpediente;
	}

	public void addPermisoExpediente(PermisosExpediente permisoExpediente) {
		permisosExpediente.add(permisoExpediente);
	}

	
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}


	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}


	private static final long serialVersionUID = 1L;
}
