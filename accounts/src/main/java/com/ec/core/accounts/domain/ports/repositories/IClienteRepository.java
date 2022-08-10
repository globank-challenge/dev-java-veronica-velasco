package com.ec.core.accounts.domain.ports.repositories;

import com.ec.core.accounts.domain.models.Cliente;


public interface IClienteRepository {
	
	Cliente findByIdentificacion(String identificacion);
	
	Cliente guardarCliente(Cliente cliente);
	
	void eliminarUsuario(Cliente cliente);
}
