package com.compuestosmo.app.controllers;

import java.net.MalformedURLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compuestosmo.app.models.dao.IExpedienteMOFDAO;
import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IUploadFileService;

@Controller("/expediente")
public class ExpedienteController {

	@Autowired
	private IExpedienteMOFDAO expedientedao;
	
	@Autowired
	private IExpedienteMOFService expedienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@RequestMapping(value="listarExpedientes", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Expediente");
		model.addAttribute("expedientemof", expedientedao.findAll());
		return "listarExpedientes";
	}
	
	@GetMapping(value="/expedienteMaterial/{id}")
	public String verExpediente(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		ExpedienteMOF expedienteMOF = expedienteService.findOne(id);
		
		if(expedienteMOF == null) {
			return "redirect:/listarExpedientes";
		}
		
		model.put("expedientemof", expedienteMOF);
		model.put("titulo", expedienteMOF.getNombreExpediente());
		
		return "expedienteMaterial";
	}
	
	@RequestMapping(value="/formPrueba")
	public String crearPrueba(Map<String, Object> model) {
		ExpedienteMOF expedienteMOF = new ExpedienteMOF();
		model.put("expedientemof", expedienteMOF);
		model.put("titulo", "Formulario Prueba");
		return "formPrueba";
	}
	
	@RequestMapping(value="/formPrueba", method=RequestMethod.POST)
	public String guardar(ExpedienteMOF expedientemof) {
		expedientedao.save(expedientemof);
		return "redirect:listarExpedientes";
	}
	
	@GetMapping(value = "/uploadsG/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
}
