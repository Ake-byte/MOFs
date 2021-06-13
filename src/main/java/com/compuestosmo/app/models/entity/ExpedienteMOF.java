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
	
	//Investigadores Responsables

	@Column(name = "nombre_seccion")
	private String nombreSeccion;
	
	@Column(name = "descripcion_seccion")
	private String descripcionSeccion;
	
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
	
	@OneToMany(mappedBy = "expedientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PruebasMOF> pruebasMOF;
	
	public ExpedienteMOF() {
		pruebasMOF = new ArrayList<PruebasMOF>();
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

	private static final long serialVersionUID = 1L;

}