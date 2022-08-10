package com.ec.core.accounts.domain.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9125856134878499021L;
	
	@Id
	protected long id;
	@Column(name = "nombre")
	protected String nombre;
	@Column(name = "genero")
	protected String genero;
	@Column(name = "edad")
	protected Integer edad;
	@Column(name = "identificacion")
	protected String identificacion;
	@Column(name = "direccion")
	protected String direccion;
	@Column(name = "telefono")
	protected String telefono;
	

}
