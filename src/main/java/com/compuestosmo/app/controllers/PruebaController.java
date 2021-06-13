package com.compuestosmo.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IPruebasMOFService;
import com.compuestosmo.app.models.service.IUploadFileService;

@Controller("/pruebasmof")
public class PruebaController {

	@Autowired 
	private IPruebasMOFService pruebaService;
	
	@Autowired
	private IExpedienteMOFService expedienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/formPrueba/{id}")
	public String crearPrueba(@PathVariable(value="id") Long seccionId, Map<String, Object> model) {
		
		
		ExpedienteMOF expedientemof = expedienteService.findOne(seccionId);
		if(expedientemof == null) {
			return "redirect:/listarMateriales";
		}
		
		PruebasMOF pruebaMOF = new PruebasMOF();
		pruebaMOF.setExpedientes(expedientemof);
		
		model.put("pruebamof", pruebaMOF);
		model.put("titulo", "Formulario Prueba");
		return "formPrueba";
	}

	//Pasar Authentication authentication en el método para obtener el nombre del usuario que ha ingresado
	//para guardar el registro de quién hizo la última modificación
	@RequestMapping(value = "/formPrueba/{idMOF}/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		PruebasMOF pruebasMOF = null;
		if (id > 0) {
			pruebasMOF = pruebaService.findOne(id);
			if(pruebasMOF == null) {
				flash.addFlashAttribute("error", "No existe este MOF en base de datos");
				return "redirect:/index";
			}
		} else {
			
			return "redirect:/index";
		}
		model.put("pruebamof", pruebasMOF);
		model.put("titulo", "Editar Prueba");
		return "formPrueba";
	}
	
	@PostMapping(value = "formPrueba")
	public String guardar(@Valid PruebasMOF pruebamof,
			BindingResult result, Model model, RedirectAttributes flash,@RequestParam("file") MultipartFile foto,
			SessionStatus status) {
			//

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Prueba");
			return "formPrueba";
		}

		if (!foto.isEmpty()) {

			if (pruebamof.getId() != null && pruebamof.getId() > 0 && pruebamof.getImagen() != null
					&& pruebamof.getImagen().length() > 0) {

				uploadFileService.delete(pruebamof.getImagen());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pruebamof.setImagen(uniqueFilename);
		} else {
			pruebamof.setImagen("");
		}
		String mensajeFlash = (pruebamof.getId() != null)? "Se han editado datos de las pruebas." : "Se ha agregado la prueba al expediente";
		expedienteService.savePrueba(pruebamof);
		
		Long idExpediente = pruebamof.getExpedientes().getId();
		
		ExpedienteMOF expedientemof = null;
		
		expedientemof = expedienteService.findOne(idExpediente);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		//return "redirect:/fichaMaterial/" + pruebamof.getExpedientes().getId();
		return "redirect:/expedienteMaterial/" + expedientemof.getId();
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
	public String eliminar(@PathVariable(value = "id") Long id, @PathVariable(value = "idMOF") Long idMOF, RedirectAttributes flash) {
		PruebasMOF prueba = pruebaService.findOne(id);
		if (id > 0) {
			
			pruebaService.delete(id);
			flash.addFlashAttribute("success", "Se ha eliminado la prueba del expediente.");
			if (prueba.getImagen() != null) {
				uploadFileService.delete(prueba.getImagen());
			}

		}
		
		ExpedienteMOF expedientemof = prueba.getExpedientes();
		//MOF mof = expedientemof.getMof();
		//ExpedienteMOF expedientemof = expedienteService.findOne();
		return "redirect:/expedienteMaterial/" + expedientemof.getId(); 
	}
}
