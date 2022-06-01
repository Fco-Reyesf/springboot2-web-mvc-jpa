package com.proyecto.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.models.dao.IClienteDao;
import com.proyecto.springboot.app.models.dao.IFacturaDao;
import com.proyecto.springboot.app.models.dao.IproductoDao;
import com.proyecto.springboot.app.models.entity.Cliente;
import com.proyecto.springboot.app.models.entity.Factura;
import com.proyecto.springboot.app.models.entity.Producto;

@Service
public class ClienteServiceImp implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IproductoDao productoDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
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

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%" + term + "%");
	}

	
	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		// TODO Auto-generated method stub
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}
	
}
