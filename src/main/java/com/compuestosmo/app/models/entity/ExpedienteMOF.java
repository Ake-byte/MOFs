package com.compuestosmo.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="expedientes")
public class ExpedienteMOF implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "expediente_id")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	@PrePersist
	public void prePersist() {
		fecha = new  Date(); 
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "mof_id")
	private MOF mof;
	
	//CREADOR - AUTOR DEL EXPEDIENTE
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	//ULTIMO USUARIO QUE REALIZÓ MODIFICACIONES EN EL EXPEDIENTE INVESTIGADOR QUE MODIFICÓ
	@Column(name = "ultimo_usuario")
	private String nombreUltimoUsuario;
	
	@OneToMany(mappedBy = "expedientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SeccionesExpediente> seccionesExpediente;
	
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "expediente_id")
    private List<PermisosExpediente> permisosExpediente;
	
	public ExpedienteMOF() {
		seccionesExpediente = new ArrayList<SeccionesExpediente>();
		permisosExpediente = new ArrayList<PermisosExpediente>();
	}
	
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	

	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public MOF getMof() {
		return mof;
	}


	public void setMof(MOF mof) {
		this.mof = mof;
	}
	
	
	public List<SeccionesExpediente> getSeccionesExpediente() {
		return seccionesExpediente;
	}


	public void setSeccionesExpediente(List<SeccionesExpediente> seccionesExpediente) {
		this.seccionesExpediente = seccionesExpediente;
	}


	public void addSeccion(SeccionesExpediente seccionExpediente) {
		seccionesExpediente.add(seccionExpediente);
	}
	
	

	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	
	
	public String getNombreUltimoUsuario() {
		return nombreUltimoUsuario;
	}



	public void setNombreUltimoUsuario(String nombreUltimoUsuario) {
		this.nombreUltimoUsuario = nombreUltimoUsuario;
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
	private static final long serialVersionUID = 1L;

}