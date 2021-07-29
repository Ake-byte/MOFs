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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="investigadores")
public class Investigador implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "investigador_id")
	private Long id;
	
	@Column(name = "director1")
	private String director1;
	
	@Column(name = "director2")
	private String director2;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "role_id")
    private Role roles;
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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


	









	public Role getRoles() {
		return roles;
	}



	public void setRoles(Role roles) {
		this.roles = roles;
	}












	private static final long serialVersionUID = 1L;
}
