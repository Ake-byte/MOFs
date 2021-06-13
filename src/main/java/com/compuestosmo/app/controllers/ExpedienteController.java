package com.compuestosmo.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IMOFService;
import com.compuestosmo.app.models.service.IUploadFileService;

@Controller("/expedientemof")
@SessionAttributes("expedientemof")
public class ExpedienteController {


	@Autowired
	private IMOFService mofService;


	@Autowired
	private IExpedienteMOFService expedienteService;


	@GetMapping(value = "/listarExpedientes/{id}")
	public String verClasificacion(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		// ClasificacionMOF clasificacion = clasificacionMOFService.findOne(id);
		MOF mof = mofService.findOne(id);

		// ExpedienteMOF expediente = new ExpedienteMOF();

		if (mof == null) {
			return "redirect:/listadoClasificacionMateriales";
		}

		model.put("mof", mof);
		model.put("titulo", "Expediente MOF: " + mof.getNombreCompuesto());

		return "listarExpedientes";
	}

	@GetMapping(value = "/expedienteMaterial/{id}")
	public String verExpediente(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		ExpedienteMOF expedienteMOF = expedienteService.findOne(id);

		if (expedienteMOF == null) {
			return "redirect:/listarExpedientes";
		}

		model.put("expedientemof", expedienteMOF);
		model.put("titulo", expedienteMOF.getNombreSeccion());

		return "expedienteMaterial";
	}

	@GetMapping("/formSeccion/{id}")
	public String crearSeccion(@PathVariable(value="id") Long mofId, Map<String, Object> model) {
		
		
		MOF mof = mofService.findOne(mofId);
		if(mof == null) {
			return "redirect:/listarMateriales";
		}
		
		ExpedienteMOF expedienteMOF = new ExpedienteMOF();
		expedienteMOF.setMof(mof);
		
		model.put("expedientemof", expedienteMOF);
		model.put("titulo", "Formulario Sección");
		return "formSeccion";
	}

	@RequestMapping(value = "/formSeccion/{idMOF}/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		ExpedienteMOF expedienteMOF = null;
		if (id > 0) {
			expedienteMOF = expedienteService.findOne(id);
			if(expedienteMOF == null) {
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
	public String guardar(@Valid ExpedienteMOF expedientemof,
			BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
			//

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de una nueva Sección");
			return "formSeccion";
		}

		String mensajeFlash = (expedientemof.getId() != null)? "Se han editado datos en el expediente." : "Se ha agregado una sección al expediente";
		mofService.saveExpediente(expedientemof);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/fichaMaterial/" + expedientemof.getMof().getId();
	}


	@RequestMapping(value = "/eliminarSeccion/{idMOF}/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, @PathVariable(value = "idMOF") Long idMOF, RedirectAttributes flash) {
		if (id > 0) {
			expedienteService.delete(id);
			flash.addFlashAttribute("success", "La sección se ha eliminado del expediente.");

		}
		MOF mof = mofService.findOne(idMOF);
		return "redirect:/fichaMaterial/" + mof.getId();
	}
}
