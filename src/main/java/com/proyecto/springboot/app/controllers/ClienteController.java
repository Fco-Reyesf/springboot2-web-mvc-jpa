package com.proyecto.springboot.app.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.springboot.app.models.entity.Cliente;
import com.proyecto.springboot.app.models.service.IClienteService;
import com.proyecto.springboot.app.utils.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteservice;
	
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Listado de clientes");
			return "form";
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
			clienteservice.delete(id);
			flash.addFlashAttribute("success","Cliente eliminado con exito");
		}
		return "redirect:/listar";
	}
	
}
