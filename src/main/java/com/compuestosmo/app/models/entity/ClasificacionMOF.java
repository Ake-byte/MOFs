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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clasificacionmof", schema = "public")
public class ClasificacionMOF implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_clasificacion")
	private Long id;
	
	@Column(name = "nombre_clasificacion")
	private String nombreClasificacion;
	
	@Column(name = "descripcion_clasificacion")
	private String descripcionClasificacion;
	
	@OneToMany(mappedBy="clasificacionmof" ,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<MOF> mofs;
	
	public ClasificacionMOF() {
		mofs = new ArrayList<MOF>();
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombreClasificacion() {
		return nombreClasificacion;
	}



	public void setNombreClasificacion(String nombreClasificacion) {
		this.nombreClasificacion = nombreClasificacion;
	}



	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}



	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}

	

	public List<MOF> getMofs() {
		return mofs;
	}



	public void setMofs(List<MOF> mofs) {
		this.mofs = mofs;
	}
	
	public void addMofs(MOF mof) {
		mofs.add(mof);
	}



	private static final long serialVersionUID = 1L;

}
