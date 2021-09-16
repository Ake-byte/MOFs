package com.compuestosmo.app.models.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.SeccionesExpediente;
import com.compuestosmo.app.models.service.IPruebasMOFService;
import com.compuestosmo.app.models.service.ISeccionesExpedienteService;
import com.compuestosmo.app.models.service.IUploadFileService;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("verSecciones")
public class ViewExpedientePDF extends AbstractPdfView{
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private ISeccionesExpedienteService seccionesService;
	
	@Autowired
	private IPruebasMOFService pruebasService;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ExpedienteMOF em = (ExpedienteMOF) model.get("expedientemof");
		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Datos del Expediente");
		tabla.addCell("Autor: " + em.getNombreUsuario());
		tabla.addCell("Nombre del Compuesto: " + em.getMof().getNombreCompuesto());
		tabla.addCell("Director de Tesis 1: " + em.getMof().getDirector1());
		tabla.addCell("Director de Tesis 2: " + em.getMof().getDirector2());
		
		List<SeccionesExpediente> seccionesEnElExpediente = seccionesService.findSeccionesByExpedienteId(em.getId());
		for(SeccionesExpediente se : seccionesEnElExpediente) {
			tabla.addCell("Nombre de la Sección: " + se.getNombreSeccion());
			tabla.addCell("Descripción de la Sección: " + se.getDescripcionSeccion());
			List<PruebasMOF> pruebasEnLaSeccion = pruebasService.findPruebasBySeccionId(se.getId());
			for (PruebasMOF pm : pruebasEnLaSeccion) {
				tabla.addCell("Nombre de la Prueba: " + pm.getNombrePrueba());
				tabla.addCell("Descripción de la Prueba: " + pm.getDescripcionPrueba());
				if (pm.getImagen() != null && pm.getImagen().length() > 1) {

					String filename = pm.getImagen();
					byte[] documentInBytes = uploadFileService.mostrar(filename);
					Image image = Image.getInstance(documentInBytes);
					tabla.addCell(image);

				}
			}
		}
		document.add(tabla);
	}

}
