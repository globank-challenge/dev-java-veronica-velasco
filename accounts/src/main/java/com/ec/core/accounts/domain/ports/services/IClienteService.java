package com.ec.core.accounts.domain.ports.services;

import com.ec.core.accounts.domain.models.dtos.ClienteDto;

public interface IClienteService {
	
	ClienteDto obtenerClientePorIdentificacion(String identificacion);
	
	ClienteDto guardarCliente(ClienteDto clienteDto);
	
	ClienteDto actualizarCliente(String id, ClienteDto clienteDto);
	
	void eliminarCliente(String identificacion);

}
