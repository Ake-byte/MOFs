package com.compuestosmo.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.entity.PermisosExpediente;
import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.SeccionesExpediente;
import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IMOFService;
import com.compuestosmo.app.models.service.IPermisosExpedientesService;
import com.compuestosmo.app.models.service.IPruebasMOFService;
import com.compuestosmo.app.models.service.ISeccionesExpedienteService;
import com.compuestosmo.app.models.service.IUsuarioService;
import com.compuestosmo.app.models.util.PageRender;

@Controller("/expedientemof")
@SessionAttributes("expedientemof")
public class ExpedienteController {

	@Autowired
	private IMOFService mofService;

	@Autowired
	private IPruebasMOFService pruebasService;

	@Autowired
	private IExpedienteMOFService expedienteService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ISeccionesExpedienteService seccionEService;

	@Autowired
	private IPermisosExpedientesService permisosE;

	@GetMapping(value = "/listarExpedientes/{id}")
	public String verClasificacion(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		MOF mof = mofService.findOne(id);

		if (mof == null) {
			return "redirect:/listadoClasificacionMateriales";
		}

		model.put("mof", mof);
		model.put("titulo", "Expedientes en: " + mof.getNombreCompuesto());

		return "listarExpedientes";
	}

	@GetMapping(value = "/expedienteMaterial/{id}")
	public String verExpediente(@RequestParam(name = "page", defaultValue = "0") int page,
			@PathVariable(value = "id") Long id, Map<String, Object> model) {

		SeccionesExpediente seccionE = seccionEService.findOne(id);

		Long idExpediente = seccionE.getId();

		Pageable pageRequest = PageRequest.of(page, 4);
		Page<PruebasMOF> prueba = pruebasService.findPruebasById(idExpediente, pageRequest);
		PageRender<PruebasMOF> pageRender = new PageRender<PruebasMOF>("/expedienteMaterial/" + id, prueba);

		SeccionesExpediente expedienteMOF = seccionEService.findOne(id);
		if (expedienteMOF == null) {
			return "redirect:/listarExpedientes";
		}

		((Model) model).addAttribute("prueba", prueba);
		((Model) model).addAttribute("expedientemof", expedienteMOF);
		((Model) model).addAttribute("page", pageRender);

		return "expedienteMaterial";
	}

	@GetMapping(value = "/verSecciones/{id}")
	public String verSeccion(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		ExpedienteMOF expedienteMOF = expedienteService.findOne(id);

		if (expedienteMOF == null) {
			return "redirect:/listarExpedientes";
		}

		MOF mof = expedienteMOF.getMof();

		model.put("expedientemof", expedienteMOF);
		model.put("nombreCompuesto", mof.getNombreCompuesto());
		model.put("autor", expedienteMOF.getNombreUsuario());

		return "verSecciones";
	}

	@GetMapping("/formExpediente/{id}")
	public String crearExpediente(@PathVariable(value = "id") Long mofId, Map<String, Object> model,
			Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		MOF mof = mofService.findOne(mofId);
		if (mof == null) {
			return "redirect:/listarMateriales";
		}
		
		// Se agrega a la tabla expedientes
		
		ExpedienteMOF expedienteMOF = new ExpedienteMOF();
		expedienteMOF.setMof(mof);

		String nombreUsuario = usuario.getNombre() + " " + usuario.getApellidoPaterno() + " "
				+ usuario.getApellidoMaterno();
		expedienteMOF.setNombreUsuario(nombreUsuario);

		model.put("expedientemof", expedienteMOF);
		model.put("titulo", "Formulario Expediente");
		
		return "formExpediente";
	}

	@GetMapping("/formSeccion/{id}")
	public String crearSeccion(@PathVariable(value = "id") Long expedienteId, Map<String, Object> model,
			RedirectAttributes flash, Authentication authentication, HttpServletRequest request) {

		SeccionesExpediente se = new SeccionesExpediente();

		ExpedienteMOF expedienteMOF = expedienteService.findOne(expedienteId);
		if (expedienteMOF == null) {
			return "redirect:/listarMateriales";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		List<PermisosExpediente> expedientesUsuario = usuario.getPermisosExpediente();

		if (expedientesUsuario.isEmpty()) {
			flash.addFlashAttribute("error",
					"No tienes permisos para modificar este expediente. Solicita permiso para editar.");
			return "redirect:/listarExpedientes/" + expedienteMOF.getMof().getId();
		} else {
			for (int i = 0; i < expedientesUsuario.size(); i++) {
				if (expedientesUsuario.get(i).getExpedientes().equals(expedienteMOF)) {
					if(expedientesUsuario.get(i).getPermiso().equals(true)) {
						se.setExpedientes(expedienteMOF);
						
						model.put("seccionE", se);
						model.put("titulo", "Formulario Sección");

					}
					else {
						flash.addFlashAttribute("error", "No tienes permisos para modificar este expediente.");
						return "redirect:/listarExpedientes/" + expedienteMOF.getMof().getId();
					}
				} 
			}
		}

		return "formSeccion";
	}

	@RequestMapping(value = "/formSeccion/{idMOF}/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		ExpedienteMOF expedienteMOF = null;
		if (id > 0) {
			expedienteMOF = expedienteService.findOne(id);
			if (expedienteMOF == null) {
				flash.addFlashAttribute("error", "No existe este MOF en base de datos");
				return "redirect:/listarExpedientes";
			}
		} else {

			return "redirect:/listarExpedientes";
		}
		model.put("expedientemof", expedienteMOF);
		model.put("titulo", "Editar Sección");
		return "formSeccion";
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
		expedienteService.saveSeccion(seccionE);

		Long idExpediente = seccionE.getExpedientes().getId();
		ExpedienteMOF expedientemof = expedienteService.findOne(idExpediente);

		expedientemof.setNombreUltimoUsuario(usuario.getNombre() + ' ' + usuario.getApellidoPaterno() + ' ' + usuario.getApellidoMaterno());
		expedienteService.save(expedientemof);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/verSecciones/" + expedientemof.getId();
	}

	@PostMapping(value = "formExpediente")
	public String guardarExpediente(@Valid ExpedienteMOF expedientemof,
			BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status,  Authentication authentication, HttpServletRequest request) {
			//

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Nuevo Expediente");
			return "formSeccion";
		}

		String mensajeFlash = (expedientemof.getId() != null)? "Se ha agregado un expediente." : "Se ha agregado agregado un expediente";
		mofService.saveExpediente(expedientemof);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		
		Usuario usuario = usuarioService.findByEmail(auth.getName());

		
		//Se agrega a la tabla de expedientes que se le pueden asignar a los usuarios
		//Funciona de la misma manera que con los roles de usuario
		PermisosExpediente permisoExpediente = new PermisosExpediente();
		permisoExpediente.setUsers(usuario);
		permisoExpediente.setExpedientes(expedientemof);
		permisoExpediente.setPermiso(true);
		
		permisosE.saveUsuario(usuario);
		permisosE.save(permisoExpediente);
		permisosE.saveExpediente(expedientemof);
		
		
		//Se realiza lo anterior para todo el usuario con permiso 'Personal Autorizado'
		List<Role> personalAutorizado = usuarioService.findByRolUsuario("ROLE_ADMIN");
		for(int i = 0; i < personalAutorizado.size(); i++) {
			PermisosExpediente permisoExpedientePA = new PermisosExpediente();
			permisoExpedientePA.setUsers(personalAutorizado.get(i).getUsers());
			permisoExpedientePA.setExpedientes(expedientemof);
			permisoExpedientePA.setPermiso(true);
			
			permisosE.saveUsuario(personalAutorizado.get(i).getUsers());
			permisosE.save(permisoExpedientePA);
			permisosE.saveExpediente(expedientemof);
		}
		
		expedientemof.setNombreUltimoUsuario(usuario.getNombre() + ' ' + usuario.getApellidoPaterno() + ' ' + usuario.getApellidoMaterno());
		expedienteService.save(expedientemof);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listarExpedientes/" + expedientemof.getMof().getId();
	}

	@RequestMapping(value = "/eliminarSeccion/{idMOF}/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, @PathVariable(value = "idMOF") Long idMOF,
			RedirectAttributes flash) {
		if (id > 0) {
			expedienteService.delete(id);
			flash.addFlashAttribute("success", "La sección se ha eliminado del expediente.");

		}
		MOF mof = mofService.findOne(idMOF);
		return "redirect:/fichaMaterial/" + mof.getId();
	}

	@RequestMapping(value = "/solicitarPermiso/{idExpediente}")
	public String solicitarPermiso(@PathVariable(value = "idExpediente") Long idExpediente, RedirectAttributes flash,
			Authentication authentication, HttpServletRequest request) {

		ExpedienteMOF expedienteMOF = expedienteService.findOne(idExpediente);

		if (idExpediente != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			Usuario usuario = usuarioService.findByEmail(auth.getName());
			
			List<PermisosExpediente> permisosDelUsuario = usuario.getPermisosExpediente();
			if(permisosDelUsuario.isEmpty()) {
				PermisosExpediente nuevoPermiso = new PermisosExpediente();
				nuevoPermiso.setPermiso(false);
				nuevoPermiso.setUsers(usuario);
				nuevoPermiso.setExpedientes(expedienteMOF);
				permisosE.save(nuevoPermiso);
				permisosE.saveExpediente(expedienteMOF);
				permisosE.saveUsuario(usuario);
				flash.addFlashAttribute("info", "Se está procesando tu solicitud para modificar este expediente.");
				return "redirect:/listarExpedientes/" + expedienteMOF.getMof().getId();
			}
			else {
				for(int i = 0; i < permisosDelUsuario.size(); i++) {
					if(permisosDelUsuario.get(i).getExpedientes().equals(expedienteMOF) ) {
						if(permisosDelUsuario.get(i).getPermiso().equals(false)) {
							flash.addFlashAttribute("info", "Se está procesando tu solicitud para modificar este expediente.");
							return "redirect:/listarExpedientes/" + expedienteMOF.getMof().getId();
						}
						else {
							flash.addFlashAttribute("info", "Ya puedes modificar este expediente, no es necesario que solicites permiso nuevamente.");
							return "redirect:/listarExpedientes/" + expedienteMOF.getMof().getId();
						}
					}
				}
			}
			
		}

		return "redirect:/listarExpedientes/" + expedienteMOF.getMof().getId();
	}
	
}
