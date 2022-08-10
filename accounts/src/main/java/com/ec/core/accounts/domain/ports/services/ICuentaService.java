package com.ec.core.accounts.domain.ports.services;

import com.ec.core.accounts.domain.models.dtos.CuentaDto;

public interface ICuentaService {

	CuentaDto obtenerCuenta(String numeroCuenta);

	CuentaDto guardarCuent(CuentaDto cuentaDto);

	CuentaDto actualizarCuenta(String numeroCuenta, CuentaDto cuentaDto);

	void eliminarCuenta(String numeroCuenta);

}
