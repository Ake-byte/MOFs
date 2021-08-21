package com.compuestosmo.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IUsuarioService;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;

	/*@GetMapping(value="/buscar-mof/{term}", produces= {"application/json"})
	public @ResponseBody List<MOF> buscar(@PathVariable String term) {
		return usuarioService.findByNombre(term);
		
	}*/
	
	@GetMapping(value="/perfil")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		List<Usuario> usuarios = usuarioService.findall();
		Usuario usuario = null;

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getEmail().equals(auth.getName())) {
				usuario = usuarios.get(i);
				break;
			}
		}

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Datos de Usuario");
		return "Usuario/perfil";
	}
	
	@GetMapping(value="/editarDatos/{id}")
	public String editarDatos(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {
		Usuario usuario = usuarioService.findOne(idUsuario);

		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Usuario/editarPerfil";
	}
	
	@PostMapping(value="/guardarDatos/{id}")
	public String guardarDatos(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {
		Usuario usuario = usuarioService.findOne(idUsuario);

		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Usuario/editarPerfil";
	}
}
