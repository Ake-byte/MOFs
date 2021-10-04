package com.compuestosmo.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compuestosmo.app.models.dao.IClasificacionMOFDAO;
import com.compuestosmo.app.models.dao.IMOFDAO;
import com.compuestosmo.app.models.entity.ClasificacionMOF;
import com.compuestosmo.app.models.entity.Directores;
import com.compuestosmo.app.models.entity.MOF;
import com.compuestosmo.app.models.service.IDirectoresService;
import com.compuestosmo.app.models.service.IMOFService;
import com.compuestosmo.app.models.service.IUploadFileService;
import com.compuestosmo.app.models.service.IUsuarioService;
import com.compuestosmo.app.models.util.PageRender;

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
	private IDirectoresService directorService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/listarMateriales")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 10);

		Page<MOF> materiales = mofService.findAll(pageRequest);

		PageRender<MOF> pageRender = new PageRender<MOF>("/CompuestoMOF/listarMateriales", materiales);

		model.addAttribute("titulo", "Listado de Materiales");
		model.addAttribute("materiales", materiales);
		model.addAttribute("mof", new MOF());
		model.addAttribute("page", pageRender);

		return "CompuestoMOF/listarMateriales";
	}

	@GetMapping(value = "/fichaMaterial/{id}")
	public String verFicha(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		MOF mof = mofService.findOne(id);

		if (mof == null) {
			return "redirect:/CompuestoMOF/listarMateriales";
		}

		model.put("mof", mof);
		model.put("titulo", "Portada: " + mof.getNombreCompuesto());

		return "CompuestoMOF/fichaMaterial";
	}

	@RequestMapping(value = "/formMaterial")
	public String crearFicha(Map<String, Object> model) {
		MOF mof = new MOF();

		Iterable<ClasificacionMOF> clasificacion = clasificaciondao.findAll();
		((Model) model).addAttribute("clasificacion", clasificacion);

		List<Directores> director1 = directorService.findall();
		List<Directores> director2 = directorService.findall();

		((Model) model).addAttribute("director1", director1);
		((Model) model).addAttribute("director2", director2);

		model.put("mof", mof);
		model.put("titulo", "Formulario MOF");
		return "CompuestoMOF/formMaterial";
	}

	@RequestMapping(value = "/formMaterial/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		MOF mof = null;

		Iterable<ClasificacionMOF> clasificacion = clasificaciondao.findAll();
		((Model) model).addAttribute("clasificacion", clasificacion);

		if (id > 0) {
			mof = mofService.findOne(id);
		} else {
			return "redirect:/listarMateriales";
		}

		List<Directores> director1 = directorService.findall();
		List<Directores> director2 = directorService.findall();

		((Model) model).addAttribute("director1", director1);
		((Model) model).addAttribute("director2", director2);

		model.put("mof", mof);
		model.put("titulo", "Editar MOF");

		return "CompuestoMOF/formMaterial";

	}

	@PostMapping(value = "/formMaterial")
	public String guardar(@Valid MOF mof, BindingResult result, Model model,
			@RequestParam("files") MultipartFile[] files, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario MOF");
			return "CompuestoMOF/formMaterial";
		}


		for (int i = 0; i < files.length; i++) {

			if (!files[0].isEmpty()) {
				if (mof.getId() != null && mof.getId() > 0 && mof.getCif() != null && mof.getCif().length() > 0) {
					uploadFileService.delete(mof.getCif());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(files[0]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				mof.setCif(uniqueFilename);
			} else {
				mof.setCif("");
			}

			if (!files[1].isEmpty()) {
				if (mof.getId() != null && mof.getId() > 0 && mof.getArticuloPDF() != null
						&& mof.getArticuloPDF().length() > 0) {
					uploadFileService.delete(mof.getArticuloPDF());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(files[1]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				mof.setArticuloPDF(uniqueFilename);
			} else {
				mof.setArticuloPDF("");
			}

			if (!files[2].isEmpty()) {
				if (mof.getId() != null && mof.getId() > 0 && mof.getTesisPDF() != null
						&& mof.getArticuloPDF().length() > 0) {
					uploadFileService.delete(mof.getTesisPDF());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(files[2]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				mof.setTesisPDF(uniqueFilename);
			} else {
				mof.setTesisPDF("");
			}

		}
		mofdao.save(mof);
		status.setComplete();

		return "redirect:listarMateriales";
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verCIF(@PathVariable String filename) {

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

	@GetMapping(value = "/descargarCIF/{id}")
	public ResponseEntity<Resource> descargarCIF(@PathVariable Long id, HttpServletRequest request) {

		MOF mof = mofService.findOne(id);

		String filename = mof.getCif();
		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(recurso.getFile().getAbsolutePath());
		} catch (IOException ex) {
			// logger.info("Could not determine file type.");
		}
		// Fallback to the default content type if type could not be determined
		if (contentType == null) {

			contentType = "application/octet-stream";

		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}

	@GetMapping(value = "/descargarArticulo/{id}")
	public ResponseEntity<Resource> descargarPDFArticulo(@PathVariable Long id, HttpServletRequest request) {

		MOF mof = mofService.findOne(id);

		String filename = mof.getArticuloPDF();
		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(recurso.getFile().getAbsolutePath());
		} catch (IOException ex) {
			// logger.info("Could not determine file type.");
		}
		// Fallback to the default content type if type could not be determined
		if (contentType == null) {

			contentType = "application/octet-stream";

		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}

	@GetMapping(value = "/descargarTesis/{id}")
	public ResponseEntity<Resource> descargarPDFTesis(@PathVariable Long id, HttpServletRequest request) {

		MOF mof = mofService.findOne(id);

		String filename = mof.getTesisPDF();
		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(recurso.getFile().getAbsolutePath());
		} catch (IOException ex) {
			// logger.info("Could not determine file type.");
		}
		// Fallback to the default content type if type could not be determined
		if (contentType == null) {

			contentType = "application/octet-stream";

		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}

	@RequestMapping(value = "eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			mofService.delete(id);
		}

		return "redirect:/CompuestoMOF/listarMateriales";
	}

	@GetMapping("/buscar")
	public String buscarMOF(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("term") String term, @ModelAttribute("mof") MOF mof, Model model,
			RedirectAttributes flash) {

		if (term != null) {
			List<MOF> mofs = mofService.findByTerm(term);

			if (mofs.isEmpty()) {
				flash.addFlashAttribute("error", "No se encontraron elementos que coincidieran con: '" + term + "'.");
				return "redirect:/CompuestoMOF/listarMateriales";
			}

			model.addAttribute("titulo", "Búsqueda: " + term);
			model.addAttribute("materiales", mofs);

			return "CompuestoMOF/ResultadosBusquedaMOF";
		}

		return "CompuestoMOF/listarMateriales";

	}
}
