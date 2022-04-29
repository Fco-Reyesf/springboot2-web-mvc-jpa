package com.proyecto.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proyecto.springboot.app.dao.IClienteDao;
import com.proyecto.springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Listado de clientes");
			return "form";
		}
		clienteDao.save(cliente);
		return "redirect:listar";
	}
	
}
