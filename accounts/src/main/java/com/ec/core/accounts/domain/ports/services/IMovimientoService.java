package com.ec.core.accounts.domain.ports.services;

import java.util.List;

import com.ec.core.accounts.domain.models.dtos.MovimientoDto;

public interface IMovimientoService {

	List<MovimientoDto> obtenerMovimientosPorCuenta(String numeroCuenta);

	MovimientoDto guardarMovimiento(MovimientoDto movimientoDto);

}
