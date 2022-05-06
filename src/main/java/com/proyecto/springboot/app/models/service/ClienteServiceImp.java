package com.proyecto.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.models.dao.IClienteDao;
import com.proyecto.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImp implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.save(cliente);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	} 
	
}
