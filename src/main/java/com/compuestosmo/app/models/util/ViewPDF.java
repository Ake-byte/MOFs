package com.compuestosmo.app.models.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.service.IPruebasMOFService;
import com.compuestosmo.app.models.service.IUploadFileService;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("verPrueba")
public class ViewPDF extends AbstractPdfView {

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private IPruebasMOFService pruebaService;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		PruebasMOF prueba = (PruebasMOF) model.get("prueba");

		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Datos de la prueba");
		tabla.addCell(prueba.getNombrePrueba());
		tabla.addCell(prueba.getDescripcionPrueba());

		if (prueba.getImagen() != null) {
			if (prueba.getImagen().length() > 1) {
				String filename = prueba.getImagen();
				byte[] documentInBytes = uploadFileService.mostrar(filename);
				Image image = Image.getInstance(documentInBytes);
				tabla.addCell(image);
			}
		}
		document.add(tabla);

	}

}
