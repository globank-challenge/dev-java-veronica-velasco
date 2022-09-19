package com.ec.core.accounts.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ec.core.accounts.domain.models.Movimiento;
import com.ec.core.accounts.domain.ports.repositories.IMovimientoRepository;
import com.ec.core.accounts.infrastructure.repositories.jpa.MovimientoJpa;

@Repository
public class MovimientoRepository implements IMovimientoRepository {
	
	@Autowired
	MovimientoJpa movimientoJpa;

	@Override
	public Movimiento guardarMovimiento(Movimiento movimiento) {
		return movimientoJpa.save(movimiento);
	}


}
