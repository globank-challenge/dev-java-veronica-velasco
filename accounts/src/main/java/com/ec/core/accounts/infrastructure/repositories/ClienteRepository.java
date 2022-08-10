package com.ec.core.accounts.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ec.core.accounts.domain.models.Cliente;
import com.ec.core.accounts.domain.ports.repositories.IClienteRepository;
import com.ec.core.accounts.infrastructure.repositories.jpa.ClienteJpa;

@Repository
public class ClienteRepository implements IClienteRepository {

	@Autowired
	ClienteJpa clienteJpa;

	@Override
	public Cliente findByIdentificacion(String identificacion) {
		return clienteJpa.findByIdentificacion(identificacion);
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		return clienteJpa.save(cliente);
	}

	@Override
	public void eliminarUsuario(Cliente cliente) {
		clienteJpa.delete(cliente);
	}
}
