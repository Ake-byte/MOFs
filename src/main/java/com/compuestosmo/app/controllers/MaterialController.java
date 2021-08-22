package com.compuestosmo.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.compuestosmo.app.models.dao.IClasificacionMOFDAO;
import com.compuestosmo.app.models.dao.IMOFDAO;
import com.compuestosmo.app.models.entity.ClasificacionMOF;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IMOFService;
import com.compuestosmo.app.models.service.IUsuarioService;

@Controller
@RequestMapping("/CompuestoMOF")
public class MaterialController {
	
	@Autowired
	private IMOFDAO mofdao;

	@Autowired
	private IMOFService mofService;
	
	@Autowired
	private IClasificacionMOFDAO clasificaciondao;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value="listarMateriales", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Materiales");
		model.addAttribute("materiales", mofdao.findAll());
		return "CompuestoMOF/listarMateriales";
	}
	
	@GetMapping(value="/fichaMaterial/{id}")
	public String verFicha(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		MOF mof = mofService.findOne(id);
		
		if(mof == null) {
			return "redirect:/CompuestoMOF/listarMateriales";
		}
		
		model.put("mof", mof);
		model.put("titulo", "Portada: " + mof.getNombreCompuesto());
		
		return "CompuestoMOF/fichaMaterial";
	}
	
	@RequestMapping(value="formMaterial")
	public String crearFicha(Map<String, Object> model) {
		MOF mof = new MOF();
		
		Iterable<ClasificacionMOF> clasificacion = clasificaciondao.findAll();
		((Model) model).addAttribute("clasificacion", clasificacion);
		
		model.put("mof", mof);
		model.put("titulo", "Formulario MOF");
		return "CompuestoMOF/formMaterial";
	}

	
	@RequestMapping(value="/formMaterial/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		MOF mof = null;
		
		Iterable<ClasificacionMOF> clasificacion = clasificaciondao.findAll();
		((Model) model).addAttribute("clasificacion", clasificacion);
		
		if(id>0) {
			mof = mofService.findOne(id);
		}else {
			return "redirect:/listarMateriales";
		}
		
		model.put("mof", mof);
		model.put("titulo", "Editar MOF");
		
		return "CompuestoMOF/formMaterial";
		
	}
	
	@PostMapping(value="/formMaterial")
	public String guardar(@Valid MOF mof, BindingResult result, Model model,SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario MOF");
			return "formMaterial";
		}
		
		mofdao.save(mof);
		status.setComplete();
		
		return "redirect:listarMateriales";
	}
	
	@RequestMapping(value="eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id){
		
		if(id>0) {
			mofService.delete(id);
		}
		
		return "redirect:/CompuestoMOF/listarMateriales";
	}
	
	@GetMapping(value = "/buscar-investigador/{term}", produces = { "application/json" })
	public @ResponseBody List<Usuario> cargarUsuarios(@PathVariable String term) {
		return usuarioService.findByNombreU(term);
	}
}
