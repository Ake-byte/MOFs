package com.compuestosmo.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compuestosmo.app.models.dao.IClasificacionMOFDAO;
import com.compuestosmo.app.models.entity.ClasificacionMOF;
import com.compuestosmo.app.models.service.IClasificacionMOFService;

@Controller("/clasificacion")
public class ClasificacionController {

	@Autowired
	private IClasificacionMOFDAO clasificacionMOFdao;
	
	@Autowired
	private IClasificacionMOFService clasificacionMOFService;
	
	@RequestMapping(value="listadoClasificacionMateriales", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Clasificación de Materiales");
		model.addAttribute("clasificacion", clasificacionMOFdao.findAll());
		return "listadoClasificacionMateriales";
	}
	
	@GetMapping(value="/verClasificacion/{id}")
	public String verClasificacion(@PathVariable(value="id") Long id, Map<String, Object> model) {
		ClasificacionMOF clasificacion = clasificacionMOFService.findOne(id);
		
		if(clasificacion == null) {
			return "redirect:/listadoClasificacionMateriales";
		}
		
		model.put("clasificacion", clasificacion);
		model.put("titulo", "Clasificación MOF: " + clasificacion.getNombreClasificacion());
		
		return "verClasificacion";
	}
	
}
