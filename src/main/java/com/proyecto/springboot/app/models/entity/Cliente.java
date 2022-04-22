package com.proyecto.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// clase pojo que esta mapeada a una entidad

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	// siempre serializar para transformar
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	private String apellido;
	private String mail;
	private Date createAt;
}
