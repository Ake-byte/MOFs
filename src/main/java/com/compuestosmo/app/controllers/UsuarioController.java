package com.compuestosmo.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.service.IUsuarioService;

@Controller
//@RequestMapping("/Usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping(value="/buscar-mof/{term}", produces= {"application/json"})
	public @ResponseBody List<MOF> buscar(@PathVariable String term) {
		return usuarioService.findByNombre(term);
		
	}
}
