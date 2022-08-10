package com.ec.core.accounts.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.core.accounts.domain.models.Cliente;

public interface ClienteJpa extends JpaRepository<Cliente, Long>{
	
	public Cliente findByIdentificacion(String identificacion);

}
