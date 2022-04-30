package com.proyecto.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.proyecto.springboot.app.models.entity.Cliente;
import com.proyecto.springboot.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteservice;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteservice.findAll());
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Listado de clientes");
			return "form";
		}
		clienteservice.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id , Map<String, Object> model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteservice.findById(id);
		}
		else {
			return "redirect:listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Cliente encontrado");
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar( @PathVariable(value = "id") Long id ) {
		if (id > 0) {
			clienteservice.delete(id);
		}
		return "redirect:/listar";
	}
	
}
