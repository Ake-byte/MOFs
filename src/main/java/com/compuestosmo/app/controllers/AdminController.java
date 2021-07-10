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
import org.springframework.web.bind.support.SessionStatus;

import com.compuestosmo.app.models.dao.IDirectoresDAO;
import com.compuestosmo.app.models.dao.IUsuarioDAO;
import com.compuestosmo.app.models.entity.ClasificacionMOF;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.RolesUsuarios;
import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IRoleService;
import com.compuestosmo.app.models.service.IRolesUsuarioService;
import com.compuestosmo.app.models.service.IUsuarioService;
import com.compuestosmo.app.models.util.MailSenderService;

@Controller
public class AdminController {

	@Autowired 
	private IUsuarioDAO usuariodao;
	
	@Autowired 
	private IUsuarioService usuarioService;
	
	@Autowired 
	private IDirectoresDAO directoresdao;
	
	@Autowired
	private IRoleService rolesService;
	
	@Autowired
	private IRolesUsuarioService roleService;
	
	@Autowired(required = true)
    private MailSenderService mailService;

	
	@RequestMapping(value="listadoUsuarios", method=RequestMethod.GET)
	public String listarUsuarios(Model model) {
		model.addAttribute("titulo", "Usuarios registrados");
		model.addAttribute("usuario", usuariodao.findAll());
		return "listadoUsuarios";
	}
	
	@RequestMapping(value="/PersonalAutorizado/listarRoles", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Permisos de Usuario");
		model.addAttribute("role", roleService.findAll());
		return "listarRoles";
	}
	
	@GetMapping(value="/PersonalAutorizado/verRol/{id}")
	public String verClasificacion(@PathVariable(value="id") Long id, Map<String, Object> model) {
		RolesUsuarios rolesUsuarios = roleService.findOne(id);
		
		if(rolesUsuarios == null) {
			return "redirect:/index";
		}
		
		model.put("rol", rolesUsuarios);
		model.put("titulo", "Usuarios con Rol: " + rolesUsuarios.getNombreRol());
		
		return "verRol";
	}
	
	@RequestMapping(value="/PersonalAutorizado/formUsuario/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Usuario usuario = null;
		
		Iterable<RolesUsuarios> rol = roleService.findAll();
		((Model) model).addAttribute("rol", rol);
		
		if(id>0) {
			usuario = usuarioService.findOne(id);
		}else {
			return "redirect:/listarRoles";
		}
		
		model.put("usuario", usuario);
		model.put("titulo", "Editar Rol de Usuario");
		
		return "formUsuario";
		
	}
	
	@PostMapping(value="/PersonalAutorizado/formUsuario")
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model,SessionStatus status) throws Exception {
		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Editar Rol de Usuario");
			return "formUsuario";
		}
		
		List<Role> role = usuario.getRoles();
		//Role newrole = new Role();
		List<RolesUsuarios> nombreRol = roleService.findAll();
		RolesUsuarios rolUsuario = usuario.getRoles_usuarios();
		
		
		for(int i = 0; i < role.size(); i++) {
			if(role.get(i).getAuthority().equals("ROLE_USER1")) {
				//role.remove(i);
				Role newrole = role.get(i);
				for(int j = 0; j < nombreRol.size(); j++) {
					if(nombreRol.get(j).equals(rolUsuario)){
						//newrole.setAuthority("ROLE_USER2");
						newrole.setAuthority(nombreRol.get(j).getNombreBD());
						newrole.setIdUsuario(usuario.getId());
					}
				}
				role.set(i, newrole);
				//role.add(newrole);
			}
			/*else {
				Role newrole = new Role();
				for(int j = 0; j < nombreRol.size(); j++) {
					if(nombreRol.get(j).equals(rolUsuario)){
						//newrole.setAuthority("ROLE_USER2");
						newrole.setAuthority(nombreRol.get(j).getNombreBD());
						newrole.setIdUsuario(usuario.getId());
					}
				}
				//role.set(i, newrole);
				role.add(newrole);
			}*/
		}
		rolUsuario = usuario.getRoles_usuarios();
		mailService.sendEmail(usuario.getEmail(), "Cambios de permisos en el sistema BD-LNCAE", "Tu permiso actual es: " + rolUsuario.getNombreRol(), usuario);
		usuarioService.save(usuario);
		status.setComplete();
		
		return "redirect:listarRoles";
	}
	
	@RequestMapping(value="/eliminarUsuario/{id}")
	public String eliminar(@PathVariable(value="id") Long id){
		
		if(id>0) {
			usuarioService.delete(id);
		}
		
		return "redirect:/listarRoles";
	}

}
