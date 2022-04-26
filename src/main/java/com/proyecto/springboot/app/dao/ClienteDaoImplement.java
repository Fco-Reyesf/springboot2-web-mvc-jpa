package com.proyecto.springboot.app.dao;

import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.models.entity.Cliente;


/**
 * 
 * repository, marca persistencia de los datos, marca como un componente de spring
 * marcar los detalles de los errores
 *
 */

@Repository("clienteDaoJPA")
public class ClienteDaoImplement implements IClienteDao {

	/*
	 * maneja el ciclo de vida, persistencia de los datos.
	 * las actualiza, elimina, consulta, operaciones a la bd
	 * pero como objetos, las consultas van a la clase entity, no a la tabla de la BD
	 */
	private EntityManager em;
	
	/*
	 * transactional, solo de lectura.
	 */
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

}