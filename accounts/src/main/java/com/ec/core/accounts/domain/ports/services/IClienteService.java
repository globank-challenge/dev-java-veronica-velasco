package com.ec.core.accounts.domain.ports.services;

import com.ec.core.accounts.domain.models.Cliente;
import com.ec.core.accounts.domain.models.dtos.ClienteDto;
import com.ec.core.accounts.infrastructure.config.exception.NotFoundException;

public interface IClienteService {
	
	ClienteDto obtenerClientePorIdentificacion(String identificacion) throws NotFoundException;
	
	ClienteDto guardarCliente(ClienteDto clienteDto);
	
	ClienteDto actualizarCliente(String id, ClienteDto clienteDto);
	
	void eliminarCliente(String identificacion);
	
	ClienteDto obtenerDtoFromCliente(Cliente cliente);
	
	public Cliente obtenerClienteFromDto(ClienteDto clienteDto);

}
