package com.proyecto.springboot.app.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.springboot.app.models.entity.Cliente;
import com.proyecto.springboot.app.models.service.IClienteService;
import com.proyecto.springboot.app.utils.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteservice;
	
	private final String CARPETA_IMAGENES = "uploads";
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/uploads/{filename:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> verImagen (@PathVariable String filename){
		Path pathFoto = Paths.get(CARPETA_IMAGENES).resolve(filename).toAbsolutePath();
		log.info("pathFoto: " + pathFoto);
		Resource recurso = null;
		try {
			recurso = new UrlResource(pathFoto.toUri());
			if (!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Errors: no se puede cargar la imagen: " + pathFoto.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"").body(recurso);
	} 
	
	@RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash ) {
		Cliente cliente = clienteservice.findById(id);
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle del cliente : " + cliente.getNombre());
		return "ver";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteservice.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	} 
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String llenar(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Formulario creacion cliente");
		model.put("cliente", cliente);
		return "form";
	}
	/*
	 *  -- bindingresult siempre va junto al objeto que es validado.
	 *  -- En guardar no se pasa el cliente a listar cuando encuentra un error, este lo envia por debajo
	 * siempre y cuando se llaman de la misma manera, si no se llama igual ocupar @modelatributte("nombre-objeto") 
	 */
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Listado de clientes");
			return "form";
		}
		if (!foto.isEmpty()) {
			if(cliente.getId() != null 
					&& cliente.getId() > 0 
					&& cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {
				Path rootPath = Paths.get(CARPETA_IMAGENES).resolve(cliente.getFoto()).toAbsolutePath();
				File imagen = rootPath.toFile();
				if (imagen.exists() && imagen.canRead()) {
					imagen.delete();
				}
			}
			String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename() ;
			Path directorioPath = Paths.get(CARPETA_IMAGENES).resolve(uniqueFilename);
			Path rootAbsolutPath = directorioPath.toAbsolutePath();
			log.info("directorioPath: " + directorioPath );
			log.info("rootAbsolutPath: " + rootAbsolutPath );
			try {
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				flash.addFlashAttribute("info","se ha subido correctamente" + uniqueFilename);
				cliente.setFoto(uniqueFilename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con exito" : "Cliente creado con exito";
		clienteservice.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success",mensajeFlash);
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id , Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteservice.findById(id);
			if (cliente == null) {
				flash.addFlashAttribute("danger","Cliente no existe");
				return "redirect:/listar";
			}
		}
		else {
			flash.addFlashAttribute("danger","Error en el id, no puede ser 0");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Cliente encontrado");
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar( @PathVariable(value = "id") Long id , RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = clienteservice.findById(id);
			clienteservice.delete(id);
			flash.addFlashAttribute("success","Cliente eliminado con exito");
			Path rootPath = Paths.get(CARPETA_IMAGENES).resolve(cliente.getFoto()).toAbsolutePath();
			File foto = rootPath.toFile();
			if (foto.exists() && foto.canRead()) {
				if (foto.delete()) {
					flash.addFlashAttribute("info","Foto: " + cliente.getFoto() + " se ha eliminado");
				}
			}
			
		}
		return "redirect:/listar";
	}
	
}
