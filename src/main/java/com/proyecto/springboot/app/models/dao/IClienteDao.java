package com.proyecto.springboot.app.models.dao;

import java.util.List;

import com.proyecto.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findById(Long id);
	
	public void delete(Long id);
	
}
