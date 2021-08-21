package com.compuestosmo.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.Investigador;
import com.compuestosmo.app.models.entity.PermisosExpediente;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IInvestigadoresService;
import com.compuestosmo.app.models.service.IPermisosExpedientesService;
import com.compuestosmo.app.models.service.IRoleService;
import com.compuestosmo.app.models.service.IUsuarioService;
import com.compuestosmo.app.models.util.MailSenderService;
import com.compuestosmo.app.models.util.PageRender;

@Controller
@RequestMapping("/PersonalAutorizado")
public class AdminController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRoleService roleService;

	@Autowired(required = true)
	private MailSenderService mailService;

	@Autowired
	private IPermisosExpedientesService permisosE;

	@Autowired
	private IInvestigadoresService investigadorS;

	@Autowired
	private IPermisosExpedientesService permisoS;

	@RequestMapping(value = "peticionesExpedientes", method = RequestMethod.GET)
	public String verExpedientesUsuarios(Model model) {
		model.addAttribute("titulo", "Solicitudes de usuarios para modificar expedientes.");
		model.addAttribute("peticion", permisoS.findAllEnabled());
		return "PersonalAutorizado/peticionesExpedientes";
	}

	@RequestMapping(value = "listarRoles", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Permisos de Usuario");
		return "PersonalAutorizado/listarRoles";
	}

	@RequestMapping(value = "/verPersonalAutorizado", method = RequestMethod.GET)
	public String verPersonalAutorizado(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Role> usuarios = usuarioService.findUsuarioByRole("ROLE_ADMIN", pageRequest);
		PageRender<Role> pageRender = new PageRender<Role>("/PersonalAutorizado/verPersonalAutorizado", usuarios);

		model.addAttribute("titulo", "Personal Autorizado");
		model.addAttribute("role", usuarios);
		model.addAttribute("page", pageRender);
		return "PersonalAutorizado/verRol";
	}

	@RequestMapping(value = "/verUsuariosRegistrados", method = RequestMethod.GET)
	public String verUsuariosRegistrados(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Role> usuarios = usuarioService.findUsuarioByRole("ROLE_USER1", pageRequest);
		PageRender<Role> pageRender = new PageRender<Role>("/PersonalAutorizado/verUsuariosRegistrados", usuarios);

		model.addAttribute("titulo", "Usuarios Registrados");
		model.addAttribute("role", usuarios);
		model.addAttribute("page", pageRender);

		return "PersonalAutorizado/verRol";
	}

	@RequestMapping(value = "/verInvestigadores", method = RequestMethod.GET)
	public String verInvestigadores(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Role> usuarios = usuarioService.findUsuarioByRole("ROLE_USER2", pageRequest);
		PageRender<Role> pageRender = new PageRender<Role>("/PersonalAutorizado/verInvestigadores", usuarios);

		model.addAttribute("titulo", "Investigadores");
		model.addAttribute("role", usuarios);
		model.addAttribute("page", pageRender);

		return "PersonalAutorizado/verRol";
	}

	@RequestMapping(value = "/verDirectoresTesis", method = RequestMethod.GET)
	public String verDirectoresTesis(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Role> usuarios = usuarioService.findUsuarioByRole("ROLE_USER3", pageRequest);
		PageRender<Role> pageRender = new PageRender<Role>("/PersonalAutorizado/verDirectoresTesis", usuarios);

		model.addAttribute("titulo", "Directores de Tesis");
		model.addAttribute("role", usuarios);
		model.addAttribute("page", pageRender);
		return "PersonalAutorizado/verRol";
	}

	@RequestMapping(value = "/verUsuariosInhabilitados", method = RequestMethod.GET)
	public String verUsuariosInhabilitados(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Role> usuarios = usuarioService.findUsuarioByRole("ROLE_USER4", pageRequest);
		PageRender<Role> pageRender = new PageRender<Role>("/PersonalAutorizado/verUsuariosInhabilitados", usuarios);

		model.addAttribute("titulo", "Usuarios Inhabilitados");
		model.addAttribute("role", usuarios);
		model.addAttribute("page", pageRender);
		return "PersonalAutorizado/verRol";
	}

	@RequestMapping(value = "/formUsuario/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Usuario usuario = null;

		if (id > 0) {
			usuario = usuarioService.findOne(id);
			model.put("usuario", usuario);
		} else {
			return "redirect:/PersonalAutorizado/listarRoles";
		}

		List<String> permisosUsuario = new ArrayList<>();
		permisosUsuario.add("Usuario Registrado");
		permisosUsuario.add("Investigador");
		permisosUsuario.add("Director de Tesis");
		permisosUsuario.add("Personal Autorizado");
		permisosUsuario.add("Usuario Inhabilitado");

		model.put("permisos", permisosUsuario);
		model.put("titulo", "Editar Rol de Usuario");

		return "PersonalAutorizado/formUsuario";

	}

	@PostMapping(value = "formUsuario")
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status)
			throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Rol de Usuario");
			return "formUsuario";
		}

		Role roles = usuario.getRoles();

		List<Role> role = roleService.findAll();
		Long idRoleUsuario;
		Role roleUsuario = null;
		for (int i = 0; i < role.size(); i++) {
			if (role.get(i).getUsers().getId().equals(usuario.getId())) {
				idRoleUsuario = usuario.getId();
				roleUsuario = roleService.findOne(idRoleUsuario);
				break;
			}
		}

		switch (usuario.getRoles().getAuthorityName()) {

		case "Usuario Registrado":
			roles.setAuthorityName("Usuario Registrado");
			roles.setAuthority("ROLE_USER1");
			break;

		case "Investigador":
			roles.setAuthorityName("Investigador");
			roles.setAuthority("ROLE_USER2");
			List<Investigador> investigadores = investigadorS.findall();
			Investigador investigador = new Investigador();

			if (roleUsuario != null) {
				investigadorS.save(investigador);
				investigadorS.saveUsuarioRole(roleUsuario);
				investigadores.add(investigador);

			} else {
				return "redirect:/PersonalAutorizado/listarRoles";
			}

			break;

		case "Director de Tesis":
			roles.setAuthorityName("Director de Tesis");
			roles.setAuthority("ROLE_USER3");
			break;

		case "Personal Autorizado":
			roles.setAuthorityName("Personal Autorizado");
			roles.setAuthority("ROLE_ADMIN");
			break;

		case "Usuario Inhabilitado":
			roles.setAuthorityName("Usuario Inhabilitado");
			roles.setAuthority("ROLE_USER4");
			usuario.setEnabled(false);
			break;

		default:
			break;
		}

		mailService.sendEmail(usuario.getEmail(), "Cambios de permisos en el sistema BD-LNCAE",
				"Tu permiso actual es: " + usuario.getRoles().getAuthorityName(), usuario);
		usuarioService.save(usuario);
		status.setComplete();

		return "redirect:/PersonalAutorizado/listarRoles";
	}

	@RequestMapping(value = "/eliminarUsuario/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			usuarioService.delete(id);
		}

		return "redirect:/PersonalAutorizado/listarRoles";
	}

	@RequestMapping(value = "/inhabilitarUsuario/{id}")
	public String inhabilitarUsuario(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			Usuario usuario = usuarioService.findOne(id);
			if (usuario != null) {
				Role permisoUsuario = usuario.getRoles();
				permisoUsuario.setAuthority("ROLE_USER4");
				permisoUsuario.setAuthorityName("Usuario Inhabilitado");
				usuario.setEnabled(false);
			}
			usuarioService.save(usuario);
		}
		
		return "redirect:/PersonalAutorizado/listarRoles";
	}

	@RequestMapping(value = "/autorizarPermiso/{id_permiso}")
	public String autorizarExpediente(@PathVariable(value = "id_permiso") Long idPermiso) throws Exception {

		PermisosExpediente permisoSolicitado = permisoS.findOne(idPermiso);

		ExpedienteMOF expedientemof = permisoSolicitado.getExpedientes();
		Usuario usuario = permisoSolicitado.getUsers();

		// Asignar Expediente al usuario que lo solicitó
		permisoSolicitado.setPermiso(true);
		permisosE.saveUsuario(usuario);

		usuarioService.save(usuario);

		mailService.sendEmail(usuario.getEmail(), "SOLICITUD PARA EDITAR EXPEDIENTE",
				"Se ha aprobado tu solicitud para editar el expediente de " + expedientemof.getNombreUsuario()
						+ " del compuesto: " + expedientemof.getMof().getNombreCompuesto(),
				usuario);
		return "redirect:/PersonalAutorizado/listarRoles";
	}
}
