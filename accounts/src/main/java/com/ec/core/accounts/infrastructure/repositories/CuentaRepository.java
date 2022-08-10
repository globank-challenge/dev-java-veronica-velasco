package com.ec.core.accounts.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ec.core.accounts.domain.models.Cuenta;
import com.ec.core.accounts.domain.ports.repositories.ICuentaRepository;
import com.ec.core.accounts.infrastructure.repositories.jpa.CuentaJpa;

@Repository
public class CuentaRepository implements ICuentaRepository {
	
	@Autowired
	CuentaJpa cuentaJpa;

	@Override
	public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
		return cuentaJpa.findByNumeroCuenta(numeroCuenta);
	}

	@Override
	public Cuenta guardarCliente(Cuenta cuenta) {
		return cuentaJpa.save(cuenta);
	}

	@Override
	public void eliminarCuenta(Cuenta cuenta) {
		cuentaJpa.delete(cuenta);

	}

}
