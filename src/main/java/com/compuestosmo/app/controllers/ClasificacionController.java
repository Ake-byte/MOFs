package com.compuestosmo.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.compuestosmo.app.models.dao.IClasificacionMOFDAO;
import com.compuestosmo.app.models.entity.ClasificacionMOF;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.service.IClasificacionMOFService;

@Controller
@RequestMapping("/Clasificacion")
public class ClasificacionController {

	@Autowired
	private IClasificacionMOFDAO clasificacionMOFdao;
	
	@Autowired
	private IClasificacionMOFService clasificacionMOFService;
	
	@RequestMapping(value="listadoClasificacionMateriales", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Clasificación de Materiales");
		model.addAttribute("clasificacion", clasificacionMOFdao.findAll());
		return "Clasificacion/listadoClasificacionMateriales";
	}
	
	@GetMapping(value="/verClasificacion/{id}")
	public String verClasificacion(@PathVariable(value="id") Long id, Map<String, Object> model) {
		ClasificacionMOF clasificacion = clasificacionMOFService.findOne(id);
		
		if(clasificacion == null) {
			return "redirect:/Clasificacion/listadoClasificacionMateriales";
		}
		
		model.put("clasificacion", clasificacion);
		model.put("titulo", "Clasificación MOF: " + clasificacion.getNombreClasificacion());
		
		return "Clasificacion/verClasificacion";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="formClasificacion")
	public String crearClasificacion(Map<String, Object> model) {
		ClasificacionMOF clasificacionmof = new ClasificacionMOF();
		
		model.put("clasificacionmof", clasificacionmof);
		model.put("titulo", "Formulario Clasificación");
		return "Clasificacion/formClasificacion";
	}
	
	@RequestMapping(value="/formClasificacion/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		ClasificacionMOF clasificacionmof = null;
		
		if(id>0) {
			clasificacionmof = clasificacionMOFService.findOne(id);
		}else {
			return "redirect:/Clasificacion/listadoClasificacionMateriales";
		}
		
		model.put("clasificacionmof", clasificacionmof);
		model.put("titulo", "Editar Clasificación MOF");
		
		return "Clasificacion/formClasificacion";
		
	}
	
	@PostMapping(value="/formClasificacion")
	public String guardar(@Valid ClasificacionMOF clasificacionmof, BindingResult result, Model model,SessionStatus status) {
		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Clasificación MOF");
			return "Clasificacion/formClasificacion";
		}

		
		clasificacionMOFService.save(clasificacionmof);
		status.setComplete();
		
		return "redirect:listadoClasificacionMateriales";
	}
	
	@RequestMapping(value="eliminarClasificacion/{id}")
	public String eliminar(@PathVariable(value="id") Long id){
		
		if(id>0) {
			clasificacionMOFService.delete(id);
		}
		
		return "redirect:/Clasificacion/listadoClasificacionMateriales";
	}

	
}
