package com.compuestosmo.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="permisos")
public class PermisosExpediente implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_permiso")
	private Long id;

	@JoinColumn(name = "permiso")
	private Boolean permiso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private Usuario users;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expediente_id")
    private ExpedienteMOF expedientes;
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	



	public Boolean getPermiso() {
		return permiso;
	}



	public void setPermiso(Boolean permiso) {
		this.permiso = permiso;
	}



	public Usuario getUsers() {
		return users;
	}



	public void setUsers(Usuario users) {
		this.users = users;
	}

	

	public ExpedienteMOF getExpedientes() {
		return expedientes;
	}



	public void setExpedientes(ExpedienteMOF expedientes) {
		this.expedientes = expedientes;
	}



	private static final long serialVersionUID = 1L;

}
