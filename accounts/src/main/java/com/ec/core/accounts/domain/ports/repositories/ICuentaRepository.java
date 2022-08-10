package com.ec.core.accounts.domain.ports.repositories;

import com.ec.core.accounts.domain.models.Cuenta;

public interface ICuentaRepository {

	Cuenta obtenerCuentaPorNumero(String numeroCuenta);

	Cuenta guardarCliente(Cuenta cuenta);

	void eliminarCuenta(Cuenta cuenta);
}
