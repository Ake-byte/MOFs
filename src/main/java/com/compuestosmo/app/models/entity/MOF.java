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

@Entity
@Table(name = "mof")
public class MOF implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mof_id")
	private Long id;

	@NotEmpty
	@Column(name = "nombre_compuesto")
	private String nombreCompuesto;

	// Directores
	@Column(name = "director1")
	private String director1;
	
	@Column(name = "director2")
	private String director2;
	
	// Investigador Responsable
	@Column(name = "investigador")
	private String investigador;
	
	//@NotEmpty
	private String formula;

	// Archivo - CIF
	private String cif;
	
	//Archivo - PDF Tesis
	private String tesisPDF;
	
	//Archivo - PDF Artículo
	private String articuloPDF;
	
	//@NotEmpty
	private String aplicaciones; // Propósito/Aplicaciones del MOF

	//@NotEmpty
	@Column(name = "metodo_sintesis")
	private String metodoSintesis;

	//@NotEmpty
	private String articulo;

	//@NotEmpty
	@Column(name = "titulo_tesis")
	private String tituloTesis;

	//@NotEmpty
	@Column(name = "url_tesis")
	private String url;

	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	@PrePersist
	public void prePersist() {
		fecha = new  Date(); 
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clasificacion")
	private ClasificacionMOF clasificacionmof;

	@OneToMany(mappedBy = "mof", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExpedienteMOF> expedientesMOF;

	public MOF() {
		expedientesMOF = new ArrayList<ExpedienteMOF>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompuesto() {
		return nombreCompuesto;
	}

	public void setNombreCompuesto(String nombreCompuesto) {
		this.nombreCompuesto = nombreCompuesto;
	}
	

	public ClasificacionMOF getClasificacionmof() {
		return clasificacionmof;
	}

	public void setClasificacionmof(ClasificacionMOF clasificacionmof) {
		this.clasificacionmof = clasificacionmof;
	}
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(String aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public String getMetodoSintesis() {
		return metodoSintesis;
	}

	public void setMetodoSintesis(String metodoSintesis) {
		this.metodoSintesis = metodoSintesis;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getTituloTesis() {
		return tituloTesis;
	}

	public void setTituloTesis(String tituloTesis) {
		this.tituloTesis = tituloTesis;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getInvestigador() {
		return investigador;
	}

	public void setInvestigador(String investigador) {
		this.investigador = investigador;
	}

	public List<ExpedienteMOF> getExpedientesMOF() {
		return expedientesMOF;
	}

	public void setExpedientesMOF(List<ExpedienteMOF> expedientesMOF) {
		this.expedientesMOF = expedientesMOF;
	}
	
	public void addExpedientes(ExpedienteMOF expedienteMOF) {
		expedientesMOF.add(expedienteMOF);
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getTesisPDF() {
		return tesisPDF;
	}

	public void setTesisPDF(String tesisPDF) {
		this.tesisPDF = tesisPDF;
	}

	public String getArticuloPDF() {
		return articuloPDF;
	}

	public void setArticuloPDF(String articuloPDF) {
		this.articuloPDF = articuloPDF;
	}

	private static final long serialVersionUID = 1L;

}
