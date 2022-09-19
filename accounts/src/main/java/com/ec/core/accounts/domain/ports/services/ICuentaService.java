package com.ec.core.accounts.domain.ports.services;

import java.util.List;

import com.ec.core.accounts.domain.models.Cuenta;
import com.ec.core.accounts.domain.models.dtos.CuentaDto;
import com.ec.core.accounts.domain.models.dtos.EstadoCuentaDto;

public interface ICuentaService {

	CuentaDto obtenerCuenta(String numeroCuenta);

	CuentaDto guardarCuenta(CuentaDto cuentaDto);

	CuentaDto actualizarCuenta(String numeroCuenta, CuentaDto cuentaDto);

	void eliminarCuenta(String numeroCuenta);
	
	Cuenta obtenerCuentaFromDto(CuentaDto cuentaDto);
	
	CuentaDto obtenerCuentaDtoFromCuenta(Cuenta cuenta);
	
	List<EstadoCuentaDto> obtenerCuentasPorIdentificacion(String identificacion);

}
