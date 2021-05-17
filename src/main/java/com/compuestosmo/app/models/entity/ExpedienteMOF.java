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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="expedientes")
public class ExpedienteMOF implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "expediente_id")
	private Long id;
	
	//Investigadores Responsables
	@Column(name = "nombre_expediente")
	private String nombreExpediente;
	
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
	private List<DetallesExpedienteMOF> detallesExpediente;
	
	public ExpedienteMOF() {
		detallesExpediente = new ArrayList<DetallesExpedienteMOF>();
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombreExpediente() {
		return nombreExpediente;
	}

	public void setNombreExpediente(String nombreExpediente) {
		this.nombreExpediente = nombreExpediente;
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


	


	public List<DetallesExpedienteMOF> getDetallesExpediente() {
		return detallesExpediente;
	}

	public void setDetallesExpediente(List<DetallesExpedienteMOF> detallesExpediente) {
		this.detallesExpediente = detallesExpediente;
	}
	
	public void addDetalles(DetallesExpedienteMOF detalleExpediente) {
		detallesExpediente.add(detalleExpediente);
	}

	private static final long serialVersionUID = 1L;

}
