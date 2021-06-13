package com.compuestosmo.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="authorities", uniqueConstraints= {@UniqueConstraint(columnNames = { "user_id", "authority" })})
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "authority")
    private String authority;
    
    @Column(name = "id_usuario")
	private Long idUsuario;
    
    //FK - Role Usuario
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private Usuario users;
    
    //FK - Role Lista de Roles
    /*
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_roles")
	private RolesUsuarios roles;
	*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsers() {
		return users;
	}

	public void setUsers(Usuario users) {
		this.users = users;
	}
	/*
	public RolesUsuarios getRoles() {
		return roles;
	}

	public void setRoles(RolesUsuarios roles) {
		this.roles = roles;
	}
	*/

	

}
