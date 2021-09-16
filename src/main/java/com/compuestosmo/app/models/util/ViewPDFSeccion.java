package com.compuestosmo.app.models.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.SeccionesExpediente;
import com.compuestosmo.app.models.service.IPruebasMOFService;
import com.compuestosmo.app.models.service.IUploadFileService;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("expedienteMaterial")
public class ViewPDFSeccion extends AbstractPdfView {

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private IPruebasMOFService pruebasService;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		SeccionesExpediente se = (SeccionesExpediente) model.get("expedientemof");

		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Datos de la Sección");
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

		document.add(tabla);
	}

}
