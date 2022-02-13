package com.compuestosmo.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.PermisosExpediente;
import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.SeccionesExpediente;
import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IPruebasMOFService;
import com.compuestosmo.app.models.service.ISeccionesExpedienteService;
import com.compuestosmo.app.models.service.IUploadFileService;
import com.compuestosmo.app.models.service.IUsuarioService;
import com.compuestosmo.app.models.util.PageRender;

@Controller("/pruebamof")
@RequestMapping("/PruebasAplicadas")
@SessionAttributes("pruebamof")
public class PruebaController {
	
	@Autowired
	private IExpedienteMOFService expedienteService;
	
	@Autowired
	private IPruebasMOFService pruebaService;

	@Autowired
	private ISeccionesExpedienteService seccionesEService;

	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping(value = "/expedienteMaterial/{id}")
	public String verExpediente(@RequestParam(name = "page", defaultValue = "0") int page,
			@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		SeccionesExpediente seccionE = seccionesEService.findOne(id);

		Long idExpediente = seccionE.getId();

		Pageable pageRequest = PageRequest.of(page, 4);
		Page<PruebasMOF> prueba = pruebaService.findPruebasById(idExpediente, pageRequest);
		PageRender<PruebasMOF> pageRender = new PageRender<PruebasMOF>("/PruebasAplicadas/expedienteMaterial/" + id, prueba);

		SeccionesExpediente expedienteMOF = seccionesEService.findOne(id);
		if (expedienteMOF == null) {
			flash.addFlashAttribute("error", "No existe el Expediente en la base de datos");
			return "redirect:/index";
		}

		((Model) model).addAttribute("prueba", prueba);
		((Model) model).addAttribute("expedientemof", expedienteMOF);
		((Model) model).addAttribute("page", pageRender);

		return "PruebasAplicadas/expedienteMaterial";
	}

	
	@GetMapping("/formPrueba/{id}")
	public String crearPrueba(@PathVariable(value = "id") Long seccionId, Map<String, Object> model,
			RedirectAttributes flash, Authentication authentication, HttpServletRequest request) {

		SeccionesExpediente seccionesE = seccionesEService.findOne(seccionId);
		if (seccionesE == null) {
			flash.addFlashAttribute("error", "No existe este registro en la base de datos.");
			return "redirect:/index";
		}

		ExpedienteMOF expedienteMOF = seccionesE.getExpedientes();

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
				PruebasMOF pruebaMOF = new PruebasMOF();
				pruebaMOF.setSecciones_expedientes(seccionesE);

				model.put("pruebamof", pruebaMOF);
				model.put("titulo", "Formulario Prueba");
				
				return "PruebasAplicadas/formPrueba";
			}			
		}
		else {
			flash.addFlashAttribute("info", "No puedes editar este expediente, solicita permiso para editar.");
			return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
		}
	}

	// Pasar Authentication authentication en el método para obtener el nombre del
	// usuario que ha ingresado
	// para guardar el registro de quién hizo la última modificación
	@RequestMapping(value = "/formPrueba/{idExpediente}/{idSeccion}/{id}")
	public String editar(@PathVariable(value = "idExpediente") Long idExpediente,
						 @PathVariable(value = "idSeccion") Long idSeccion,
			@PathVariable(value = "id") Long id, Map<String, Object> model, Authentication authentication, RedirectAttributes flash) {
		PruebasMOF pruebasMOF = pruebaService.findOne(id);
		SeccionesExpediente seccionExpediente = seccionesEService.findOne(idSeccion);
		ExpedienteMOF expedienteMOF = expedienteService.findOne(idExpediente);
		/*if (id > 0) {
			pruebasMOF = pruebaService.findOne(id);
			if (pruebasMOF == null) {
				flash.addFlashAttribute("error", "No existe este MOF en base de datos");
				return "redirect:/index";
			}
		} else {
			flash.addFlashAttribute("error", "No existe la prueba dentro del expediente.");
			return "redirect:/index";
		}*/
		
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
				model.put("pruebamof", pruebasMOF);
				model.put("file", "/PruebasAplicadas/uploads/" + pruebasMOF.getImagen());
				model.put("titulo", "Editar Prueba");
				
				return "PruebasAplicadas/formPrueba";
			}			
		}
		else {	
			flash.addFlashAttribute("info", "No puedes editar este expediente, solicita permiso para editar.");
			return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
		}
	}

	@PostMapping(value = "formPrueba")
	public String guardar(@Valid PruebasMOF pruebamof, BindingResult result, Model model, RedirectAttributes flash,
			@RequestParam("file") MultipartFile foto, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Prueba");
			return "formPrueba";
		}

		String uniqueFilename = null;
		if (!foto.isEmpty()) {

			if (pruebamof.getId() != null && pruebamof.getId() > 0 && pruebamof.getImagen() != null
					&& pruebamof.getImagen().length() > 0) {

				uploadFileService.delete(pruebamof.getImagen());
			}
			
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			pruebamof.setImagen(uniqueFilename);
		} else {
			if(pruebamof.getImagen() == null) {
				pruebamof.setImagen("");
			}
		}
		String mensajeFlash = (pruebamof.getId() != null) ? "Se han editado datos de las pruebas."
				: "Se ha agregado la prueba al expediente";
		
		
		
		Long idSeccion = pruebamof.getSecciones_expedientes().getId();
		SeccionesExpediente seccionesExpediente= seccionesEService.findOne(idSeccion);
		
		pruebamof.setSecciones_expedientes(seccionesExpediente);

		seccionesEService.savePrueba(pruebamof);
		pruebaService.save(pruebamof);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/PruebasAplicadas/expedienteMaterial/" + idSeccion;
	}

	@GetMapping(value = "/uploads/{filename:.+}")
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

	@RequestMapping(value = "/eliminarPrueba/{idMOF}/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, @PathVariable(value = "idMOF") Long idMOF,
			RedirectAttributes flash) {
		PruebasMOF prueba = pruebaService.findOne(id);
		if (id > 0) {

			pruebaService.delete(id);
			flash.addFlashAttribute("success", "Se ha eliminado la prueba del expediente.");
			if (prueba.getImagen() != null) {
				uploadFileService.delete(prueba.getImagen());
			}

		}

		SeccionesExpediente seccionesE = prueba.getSecciones_expedientes();
		return "redirect:/PruebasAplicadas/expedienteMaterial/" + seccionesE.getId();
	}

	@GetMapping(value = "/verPrueba/{id}")
	public String verPDF(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
		PruebasMOF prueba = pruebaService.findOne(id);
		
		
		model.addAttribute("prueba", prueba);
		
		return "PruebasAplicadas/verPrueba";
	}

}
