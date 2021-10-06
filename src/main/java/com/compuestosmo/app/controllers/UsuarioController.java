package com.compuestosmo.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compuestosmo.app.models.entity.Directores;
import com.compuestosmo.app.models.entity.Investigador;
import com.compuestosmo.app.models.entity.PermisosExpediente;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IDirectoresService;
import com.compuestosmo.app.models.service.IInvestigadoresService;
import com.compuestosmo.app.models.service.IPermisosExpedientesService;
import com.compuestosmo.app.models.service.IRoleService;
import com.compuestosmo.app.models.service.IUsuarioService;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IInvestigadoresService investigadorService;

	@Autowired
	private IDirectoresService directorService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPermisosExpedientesService permisosEService;

	@GetMapping(value = "/perfil")
	public String verPerfil(Model model, Authentication authentication, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = usuarioService.findByEmail(auth.getName());

		Role role = roleService.findOne(usuario.getRoles().getId());
		
		
		Investigador investigador = investigadorService.findByRoleID(role.getId());
		
		List<PermisosExpediente> permisosDelUsuario = usuario.getPermisosExpediente();

		model.addAttribute("usuario", usuario);
		model.addAttribute("investigador", investigador);
		model.addAttribute("permisosDelUsuario", permisosDelUsuario);
		model.addAttribute("titulo", "Datos de Usuario");
		return "Usuario/perfil";
	}

	@GetMapping(value = "/editarDatos/{id}")
	public String editarDatos(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {

		Usuario usuario = usuarioService.findOne(idUsuario);

		((Model) model).addAttribute("usuario", usuario);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Usuario/editarPerfil";
	}

	@PostMapping(value = "/editarDatos")
	public String guardarDatos(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "formMaterial";
		}

		usuarioService.save(usuario);
		status.setComplete();

		return "redirect:perfil";
	}
	
	@GetMapping(value = "/editarDatosSistema/{id}")
	public String editarDatosSistema(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {

		Investigador investigador = investigadorService.findOne(idUsuario);

		List<Directores> director1 = directorService.findall();
		List<Directores> director2 = directorService.findall();

		((Model) model).addAttribute("investigador", investigador);
		((Model) model).addAttribute("director1", director1);
		((Model) model).addAttribute("director2", director2);
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Usuario/editarDatosSistema";
	}

	@PostMapping(value = "/editarDatosSistema")
	public String guardarDatosSistema(@Valid Investigador investigador, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos de Sistema");
			return "formMaterial";
		}

		investigadorService.save(investigador);

		return "redirect:perfil";
	}
	
	@GetMapping(value = "/changeEmail/{id}")
	public String changeEmail(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {

		Usuario usuario = usuarioService.findOne(idUsuario);

		((Model) model).addAttribute("id", usuario.getId());
		((Model) model).addAttribute("titulo", "Editar Correo Electrónico");
		return "Usuario/newEmail";
	}

	@PostMapping(value = "/changeEmail")
	public String saveNewEmail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("email") String email, @ModelAttribute("id") Long id, BindingResult result,
			Model model, RedirectAttributes flash, SessionStatus status) {
		
		Usuario usuario = usuarioService.findOne(id);
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Correo Electrónico");
			return "formMaterial";
		}

		usuario.setEmail(email);
		usuarioService.save(usuario);

		return "login";
	}

	@GetMapping(value = "/changePassword/{id}")
	public String changePassword(@PathVariable(value = "id") Long idUsuario, Map<String, Object> model) {

		Usuario usuario = usuarioService.findOne(idUsuario);

		((Model) model).addAttribute("id", usuario.getId());
		((Model) model).addAttribute("titulo", "Editar Datos");
		return "Usuario/changePasswordForm";
	}

	@PostMapping(value = "/changePassword")
	public String saveNewPassword(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("password") String newPassword, @ModelAttribute("id") Long id, BindingResult result,
			Model model, RedirectAttributes flash, SessionStatus status) {
		
		Usuario usuario = usuarioService.findOne(id);
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Datos");
			return "formMaterial";
		}

		
		String bcryptPassword = passwordEncoder.encode(newPassword);
		usuario.setPassword(bcryptPassword);

		usuarioService.save(usuario);

		return "login";
	}

}
