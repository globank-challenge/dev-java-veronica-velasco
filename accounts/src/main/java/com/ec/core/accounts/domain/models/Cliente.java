package com.ec.core.accounts.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ec.core.accounts.domain.enums.EstadoClienteEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cliente extends Persona{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7968968793551918446L;
	
	@Column(name = "cliente_id")
	private String clienteId;
	@Column(name = "contrasenia")
	private String contrasenia;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoClienteEnum estado;
	
	@Builder
	public Cliente(long id, String nombre, String genero, Integer edad, String identificacion, String direccion,
			String telefono,String clienteId,String contrasenia,EstadoClienteEnum estado) {
		super(id, nombre, genero, edad, identificacion, direccion, telefono);
		this.clienteId=clienteId;
		this.contrasenia=contrasenia;
		this.estado=estado;
	}
	

}
