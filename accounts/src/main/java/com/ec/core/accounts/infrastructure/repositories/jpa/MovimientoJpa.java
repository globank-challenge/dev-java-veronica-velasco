package com.ec.core.accounts.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.core.accounts.domain.models.Movimiento;

public interface MovimientoJpa extends JpaRepository<Movimiento, Long> {
	
}
