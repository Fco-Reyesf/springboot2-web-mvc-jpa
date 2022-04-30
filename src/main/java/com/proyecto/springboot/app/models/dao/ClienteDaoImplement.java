package com.proyecto.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	@Autowired
	private EntityManager em;
	
	/*
	 * transactional, solo de lectura.
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) {
		if (cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente); // actualiza
		}
		else {
			em.persist(cliente); // persisten los datos
		}
	}
	
	@Override
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findById(id));
	}

}
