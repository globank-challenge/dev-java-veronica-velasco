package com.ec.core.accounts.domain.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteDto {

	private long id;
	private String clienteId;
	private String contrasenia;
	private String estado;
	private String nombre;
	private String genero;
	private Integer edad;
	private String identificacion;
	private String telefono;
	private String direccion;

}
