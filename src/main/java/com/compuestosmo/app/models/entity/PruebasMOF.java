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

@Entity
@Table(name="pruebas")
public class PruebasMOF implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "prueba_id")
	private Long id;
	
	@Column(name = "nombre_prueba")
	private String nombrePrueba;
	
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
	@JoinColumn(name = "expediente_id")
	private ExpedienteMOF expedientes;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

	public ExpedienteMOF getExpedientes() {
		return expedientes;
	}



	public void setExpedientes(ExpedienteMOF expedientes) {
		this.expedientes = expedientes;
	}

	private static final long serialVersionUID = 1L;

}
