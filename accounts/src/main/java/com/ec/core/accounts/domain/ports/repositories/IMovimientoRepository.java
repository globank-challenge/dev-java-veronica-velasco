package com.ec.core.accounts.domain.ports.repositories;

import com.ec.core.accounts.domain.models.Movimiento;

public interface IMovimientoRepository {

	Movimiento guardarMovimiento(Movimiento movimiento);

}
