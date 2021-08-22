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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="roles", uniqueConstraints= {@UniqueConstraint(columnNames = { "user_id", "authority" })})
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "authority")
    private String authority;
    
    @Column(name = "authority_name")
    private String authorityName;
    
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private Usuario users;
    
    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Investigador> investigadores;
    
    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Directores> directores;
    
    public Role() {
    	investigadores = new ArrayList<Investigador>();
    	directores = new ArrayList<Directores>();
    }
    
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

	public Usuario getUsers() {
		return users;
	}

	public void setUsers(Usuario users) {
		this.users = users;
	}

	public List<Investigador> getInvestigadores() {
		return investigadores;
	}

	public void setInvestigadores(List<Investigador> investigadores) {
		this.investigadores = investigadores;
	}
	
	public List<Directores> getDirectores() {
		return directores;
	}

	public void setDirectores(List<Directores> directores) {
		this.directores = directores;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

}
