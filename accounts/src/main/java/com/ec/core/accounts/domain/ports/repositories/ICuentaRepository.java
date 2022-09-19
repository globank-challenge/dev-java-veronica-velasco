package com.ec.core.accounts.domain.ports.repositories;

import java.util.List;

import com.ec.core.accounts.domain.models.Cuenta;

public interface ICuentaRepository {

	Cuenta obtenerCuentaPorNumero(String numeroCuenta);

	Cuenta guardarCuenta(Cuenta cuenta);

	void eliminarCuenta(Cuenta cuenta);
	
	List<Cuenta> obtenerCuentaPorIdentificacion(String identificacion);
	
}
