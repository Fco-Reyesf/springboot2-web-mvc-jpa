package com.proyecto.springboot.app.dao;

import java.util.List;

import com.proyecto.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
}
