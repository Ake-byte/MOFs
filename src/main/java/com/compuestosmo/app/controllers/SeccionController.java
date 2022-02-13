package com.compuestosmo.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.compuestosmo.app.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IMOFService;
import com.compuestosmo.app.models.service.ISeccionesExpedienteService;
import com.compuestosmo.app.models.service.IUsuarioService;

@Controller
@RequestMapping("/SeccionesExpediente")
public class SeccionController {
	
	@Autowired
	private IExpedienteMOFService expedienteService;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IMOFService mofService;
	
	@Autowired
	private ISeccionesExpedienteService seccionService;
	
	@GetMapping(value = "/verSecciones/{id}")
	public String verSeccion(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		ExpedienteMOF expedienteMOF = expedienteService.findOne(id);

		if (expedienteMOF == null) {
			return "redirect:/listarExpedientes";
		}

		MOF mof = expedienteMOF.getMof();

		model.put("expedientemof", expedienteMOF);
		model.put("mof", mof);

		return "SeccionesExpediente/verSecciones";
	}
	
	@GetMapping("/formSeccion/{id}")
	public String crearSeccion(@PathVariable(value = "id") Long expedienteId, Map<String, Object> model,
			RedirectAttributes flash, Authentication authentication, HttpServletRequest request) {

		ExpedienteMOF expedienteMOF = expedienteService.findOne(expedienteId);
		if (expedienteMOF == null) {
			return "redirect:/listarMateriales";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		List<PermisosExpediente> permisosDelUsuario = usuario.getPermisosExpediente();

		PermisosExpediente pe = permisosDelUsuario.stream()
				.filter(permiso -> expedienteMOF.getId().equals(permiso.getExpedientes().getId()))
				.findAny()
				.orElse(null);
		
		if(pe != null) {
			
			if(pe.getPermiso().equals(false)) {
				flash.addFlashAttribute("info", "Se está procesando tu solicitud para modificar este expediente.");
				return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
			}
			else {
				SeccionesExpediente se = new SeccionesExpediente();
				se.setExpedientes(expedienteMOF);
				
				model.put("seccionE", se);
				model.put("titulo", "Formulario Sección");
				
				return "SeccionesExpediente/formSeccion";
			}			
		}
		else {
			flash.addFlashAttribute("info", "No puedes editar este expediente, solicita permiso para editar.");
			return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
		}		
	}

	@RequestMapping(value = "/formSeccion/{idExpediente}/{id}")
	public String editar(@PathVariable(value = "idExpediente") Long idExpediente,
			@PathVariable(value = "id") Long id, Map<String, Object> model, Authentication authentication, RedirectAttributes flash) {
		
		SeccionesExpediente seccion = null;
		
		ExpedienteMOF expedienteMOF = expedienteService.findOne(idExpediente);
		
		if (id > 0) {
			seccion = seccionService.findOne(id);
			if (seccion == null) {
				flash.addFlashAttribute("error", "No existe este registro dentro de la base de datos");
				return "redirect:/index";
			}
			
		} else {
			flash.addFlashAttribute("error", "No existe este registro dentro de la base de datos");
			return "redirect:/index";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		List<PermisosExpediente> permisosDelUsuario = usuario.getPermisosExpediente();

		PermisosExpediente pe = permisosDelUsuario.stream()
				.filter(permiso -> expedienteMOF.getId().equals(permiso.getExpedientes().getId()))
				.findAny()
				.orElse(null);
		
		if(pe != null) {
			
			if(pe.getPermiso().equals(false)) {
				flash.addFlashAttribute("info", "Se está procesando tu solicitud para modificar este expediente.");
				return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
			}
			else {
				model.put("seccionE", seccion);
				model.put("titulo", "Editar Sección");
				
				return "SeccionesExpediente/formSeccion";
			}			
		}
		else {
			flash.addFlashAttribute("info", "No puedes editar este expediente, solicita permiso para editar.");
			return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
		}
	}

	@PostMapping(value = "formSeccion")
	public String guardarSeccion(@Valid SeccionesExpediente seccionE, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status, Authentication authentication, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Usuario usuario = usuarioService.findByEmail(auth.getName());

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de una nueva Sección");
			return "formSeccion";
		}
		
		String mensajeFlash = (seccionE.getId() != null) ? "Se han editado datos en el expediente."
				: "Se ha agregado una sección al expediente";
		List<PruebasMOF> pruebasMOFS = new ArrayList<>();
		seccionE.setPruebasMOF(pruebasMOFS);

		seccionService.save(seccionE);

		ExpedienteMOF expedienteMOF = seccionE.getExpedientes();
		//List<SeccionesExpediente> seccionesExpediente = expedienteMOF.getSeccionesExpediente();
		//seccionesExpediente.add(seccionE);
		
		expedienteService.saveSeccion(seccionE);

		expedienteMOF.setNombreUltimoUsuario(usuario.getNombre() + ' ' + usuario.getApellidoPaterno() + ' ' + usuario.getApellidoMaterno());
		expedienteService.save(expedienteMOF);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/SeccionesExpediente/verSecciones/" + expedienteMOF.getId();
	}

	
	@RequestMapping(value = "/eliminarSeccion/{idMOF}/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, @PathVariable(value = "idMOF") Long idMOF,
			RedirectAttributes flash) {
		if (id > 0) {
			expedienteService.delete(id);
			flash.addFlashAttribute("success", "La sección se ha eliminado del expediente.");

		}
		MOF mof = mofService.findOne(idMOF);
		return "redirect:/CompuestoMOF/fichaMaterial/" + mof.getId();
	}


}
