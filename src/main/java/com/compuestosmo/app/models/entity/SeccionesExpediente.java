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
import javax.persistence.Table;

@Entity
@Table(name="secciones_expedientes")
public class SeccionesExpediente implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "seccion_expediente_id")
	private Long id;

	@Column(name = "nombre_seccion")
	private String nombreSeccion;
	
	@Column(name = "descripcion_seccion")
	private String descripcionSeccion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "expediente_id")
	private ExpedienteMOF expedientes;
	
	@OneToMany(mappedBy = "secciones_expedientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PruebasMOF> pruebasMOF;
	
	public SeccionesExpediente() {
		pruebasMOF = new ArrayList<PruebasMOF>();
	}
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombreSeccion() {
		return nombreSeccion;
	}



	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}



	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}



	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}



	public List<PruebasMOF> getPruebasMOF() {
		return pruebasMOF;
	}



	public void setPruebasMOF(List<PruebasMOF> pruebasMOF) {
		this.pruebasMOF = pruebasMOF;
	}

	
	public void addPruebas(PruebasMOF pruebaMOF) {
		pruebasMOF.add(pruebaMOF);
	}


	
	public ExpedienteMOF getExpedientes() {
		return expedientes;
	}



	public void setExpedientes(ExpedienteMOF expedientes) {
		this.expedientes = expedientes;
	}



	private static final long serialVersionUID = 1L;

}
