package com.compuestosmo.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="expedientes", schema = "public")
public class ExpedienteMOF implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "expediente_id")
	private Long id;
	
	//Investigadores Responsables
	//@NotEmpty
	@Column(name = "nombre_prueba")
	private String nombrePrueba;
	
	//@NotEmpty
	@Column(name = "descripcion_prueba")
	private String descripcionPrueba;
	
	private String imagen;
	
	
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
	
	

	public String getNombrePrueba() {
		return nombrePrueba;
	}


	public void setNombrePrueba(String nombrePrueba) {
		this.nombrePrueba = nombrePrueba;
	}


	public String getDescripcionPrueba() {
		return descripcionPrueba;
	}


	public void setDescripcionPrueba(String descripcionPrueba) {
		this.descripcionPrueba = descripcionPrueba;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	private static final long serialVersionUID = 1L;

}