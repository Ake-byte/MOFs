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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.compuestosmo.app.models.dao.IExpedienteMOFDAO;
import com.compuestosmo.app.models.dao.IMOFDAO;
import com.compuestosmo.app.models.entity.ClasificacionMOF;
import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IMOFService;
import com.compuestosmo.app.models.service.IUploadFileService;

@Controller("/expedientemof")
//@RequestMapping("/expedientemof")
@SessionAttributes("expedientemof")
public class ExpedienteController {

	@Autowired
	private IMOFDAO mofdao;

	@Autowired
	private IMOFService mofService;

	@Autowired
	private IExpedienteMOFDAO expedientedao;

	@Autowired
	private IExpedienteMOFService expedienteService;

	@Autowired
	private IUploadFileService uploadFileService;

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
		model.put("titulo", expedienteMOF.getNombrePrueba());

		return "expedienteMaterial";
	}

	@GetMapping("/formPrueba/{id}")
	public String crearPrueba(@PathVariable(value="id") Long mofId, Map<String, Object> model) {
		
		
		MOF mof = mofService.findOne(mofId);
		if(mof == null) {
			return "redirect:/listarMateriales";
		}
		
		ExpedienteMOF expedienteMOF = new ExpedienteMOF();
		expedienteMOF.setMof(mof);
		
		model.put("expedientemof", expedienteMOF);
		model.put("titulo", "Formulario Prueba");
		return "formPrueba";
	}

	@RequestMapping(value = "/formPrueba/{idMOF}/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		ExpedienteMOF expedienteMOF = null;
		if (id > 0) {
			expedienteMOF = expedienteService.findOne(id);
		} else {
			return "redirect:/listarExpedientes";
		}
		model.put("expedientemof", expedienteMOF);
		model.put("titulo", "Editar Prueba");
		return "formPrueba";
	}

	@PostMapping(value = "formPrueba")
	public String guardar(@Valid ExpedienteMOF expedientemof,
			BindingResult result, Model model, @RequestParam("file") MultipartFile foto,
			SessionStatus status) {
			//

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Prueba");
			return "formPrueba";
		}

		if (!foto.isEmpty()) {

			if (expedientemof.getId() != null && expedientemof.getId() > 0 && expedientemof.getImagen() != null
					&& expedientemof.getImagen().length() > 0) {

				uploadFileService.delete(expedientemof.getImagen());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			expedientemof.setImagen(uniqueFilename);
		} else {
			expedientemof.setImagen("");
		}

		//expedienteService.save(expedientemof);
		// status.setComplete();

		//expedientedao.save(expedientemof);
		
		
		
		/*MOF mof = mofService.findOne(id);
		if(mof == null) {
			return "redirect:/listarMateriales";
		}
		
		ExpedienteMOF expedienteMOF = new ExpedienteMOF();
		expedienteMOF.setMof(mof);
		*/
		mofService.saveExpediente(expedientemof);
		
		status.setComplete();
		
		return "redirect:/fichaMaterial/" + expedientemof.getMof().getId();
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

	@RequestMapping(value = "/eliminarPrueba/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			ExpedienteMOF expediente = expedienteService.findOne(id);
			expedienteService.delete(id);

			if (expediente.getImagen() != null) {
				uploadFileService.delete(expediente.getImagen());
			}

		}
		return "redirect:/listarExpedientes";
	}
}
