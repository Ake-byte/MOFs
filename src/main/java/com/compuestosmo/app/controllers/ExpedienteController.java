package com.compuestosmo.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compuestosmo.app.models.entity.ExpedienteMOF;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.entity.PermisosExpediente;
import com.compuestosmo.app.models.entity.PruebasMOF;
import com.compuestosmo.app.models.entity.Role;
import com.compuestosmo.app.models.entity.SeccionesExpediente;
import com.compuestosmo.app.models.entity.Usuario;
import com.compuestosmo.app.models.service.IExpedienteMOFService;
import com.compuestosmo.app.models.service.IMOFService;
import com.compuestosmo.app.models.service.IPermisosExpedientesService;
import com.compuestosmo.app.models.service.IPruebasMOFService;
import com.compuestosmo.app.models.service.ISeccionesExpedienteService;
import com.compuestosmo.app.models.service.IUsuarioService;
import com.compuestosmo.app.models.util.PageRender;

@Controller("/expedientemof")
@RequestMapping("/Expediente")
@SessionAttributes("expedientemof")
public class ExpedienteController {

    @Autowired
    private IMOFService mofService;

    @Autowired
    private IExpedienteMOFService expedienteService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPermisosExpedientesService permisosE;

    @GetMapping(value = "/listarExpedientes/{id}")
    public String verListadoExpedientes(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        MOF mof = mofService.findOne(id);

        if (mof == null) {
            return "redirect:/Expediente/listarExpedientes";
        }

        model.put("mof", mof);
        model.put("titulo", "Expedientes en: " + mof.getNombreCompuesto());

        return "Expediente/listarExpedientes";
    }

    @GetMapping("/formExpediente/{id}")
    public String crearExpediente(@PathVariable(value = "id") Long mofId, Map<String, Object> model,
                                  Authentication authentication, HttpServletRequest request, RedirectAttributes flash) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = usuarioService.findByEmail(auth.getName());

        String autorExpediente = usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno();


        MOF mof = mofService.findOne(mofId);
        if (mof == null) {
            flash.addFlashAttribute("error", "No existe ese registro en la Base de Datos");
            return "redirect:/index";
        }

        List<ExpedienteMOF> expedienteMOFS = mof.getExpedientesMOF();
        ExpedienteMOF expedienteMOF = expedienteMOFS.stream().filter(e -> e.getNombreUsuario().equals(autorExpediente)).findAny().orElse(null);
        if (expedienteMOF != null) {
            flash.addFlashAttribute("info", "Ya cuentas con un expediente para el compuesto: " + mof.getNombreCompuesto());
            return "redirect:/Expediente/listarExpedientes/" + mof.getId();
        } else {
            // Se agrega a la tabla expedientes

            expedienteMOF = new ExpedienteMOF();
            expedienteMOF.setMof(mof);

            String nombreUsuario = usuario.getNombre() + " " + usuario.getApellidoPaterno() + " "
                    + usuario.getApellidoMaterno();
            expedienteMOF.setNombreUsuario(nombreUsuario);

            model.put("expedientemof", expedienteMOF);
            model.put("titulo", "Formulario Expediente");
        }


        return "Expediente/formExpediente";
    }

    @PostMapping(value = "formExpediente")
    public String guardarExpediente(@Valid ExpedienteMOF expedientemof,
                                    BindingResult result, Model model, RedirectAttributes flash,
                                    SessionStatus status, Authentication authentication, HttpServletRequest request) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario Nuevo Expediente");
            return "Expediente/formExpediente/";
        }

        String mensajeFlash = (expedientemof.getId() != null) ? "Se ha agregado un expediente." : "Se ha agregado agregado un expediente";
        mofService.saveExpediente(expedientemof);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = usuarioService.findByEmail(auth.getName());

        //Se agrega a la tabla de expedientes que se le pueden asignar a los usuarios
        //Funciona de la misma manera que con los roles de usuario
        PermisosExpediente permisoExpediente = new PermisosExpediente();
        permisoExpediente.setUsers(usuario);
        permisoExpediente.setExpedientes(expedientemof);
        permisoExpediente.setPermiso(true);

        permisosE.saveUsuario(usuario);
        permisosE.save(permisoExpediente);
        permisosE.saveExpediente(expedientemof);

        List<Role> personalAutorizado = usuarioService.findByRolUsuario("ROLE_ADMIN");
        for (int i = 0; i < personalAutorizado.size(); i++) {
            PermisosExpediente permisoExpedientePA = new PermisosExpediente();
            permisoExpedientePA.setUsers(personalAutorizado.get(i).getUsers());
            permisoExpedientePA.setExpedientes(expedientemof);
            permisoExpedientePA.setPermiso(true);

            permisosE.saveUsuario(personalAutorizado.get(i).getUsers());
            permisosE.save(permisoExpedientePA);
            permisosE.saveExpediente(expedientemof);
        }

        expedientemof.setNombreUltimoUsuario(usuario.getNombre() + ' ' + usuario.getApellidoPaterno() + ' ' + usuario.getApellidoMaterno());
        expedienteService.save(expedientemof);

        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/Expediente/listarExpedientes/" + expedientemof.getMof().getId();
    }

    @RequestMapping(value = "/solicitarPermiso/{idExpediente}")
    public String solicitarPermiso(@PathVariable(value = "idExpediente") Long idExpediente, RedirectAttributes flash,
                                   Authentication authentication, HttpServletRequest request) {

        ExpedienteMOF expedienteMOF = expedienteService.findOne(idExpediente);

        if (idExpediente != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            Usuario usuario = usuarioService.findByEmail(auth.getName());

            List<PermisosExpediente> permisosDelUsuario = usuario.getPermisosExpediente();

            PermisosExpediente pe = permisosDelUsuario.stream()
                    .filter(permiso -> expedienteMOF.getId().equals(permiso.getExpedientes().getId()))
                    .findAny()
                    .orElse(null);

            if (pe != null) {

                if (pe.getPermiso().equals(false)) {
                    flash.addFlashAttribute("info", "Se está procesando tu solicitud para modificar este expediente.");
                    return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
                } else {
                    flash.addFlashAttribute("info", "Ya puedes modificar este expediente, no es necesario que solicites permiso nuevamente.");
                    return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
                }
            } else {
                PermisosExpediente nuevoPermiso = new PermisosExpediente();
                nuevoPermiso.setPermiso(false);
                nuevoPermiso.setUsers(usuario);
                nuevoPermiso.setExpedientes(expedienteMOF);

                permisosE.save(nuevoPermiso);
                permisosE.saveExpediente(expedienteMOF);
                permisosE.saveUsuario(usuario);

                usuario.addPermisoExpediente(nuevoPermiso);

                usuarioService.save(usuario);

                flash.addFlashAttribute("info", "Se está procesando tu solicitud para modificar este expediente.");
                return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
            }
        }

        return "redirect:/Expediente/listarExpedientes/" + expedienteMOF.getMof().getId();
    }

}
