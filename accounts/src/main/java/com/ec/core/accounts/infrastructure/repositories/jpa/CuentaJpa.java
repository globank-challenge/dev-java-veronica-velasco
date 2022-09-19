package com.ec.core.accounts.infrastructure.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ec.core.accounts.domain.models.Cuenta;

public interface CuentaJpa extends JpaRepository<Cuenta, Long> {
	public Cuenta findByNumeroCuenta(String numeroCuenta);

	@Query("SELECT cuenta from Cuenta as cuenta where cuenta.cliente.identificacion = :identificacion")
	public List<Cuenta> obtenerCuentasPorIdentificacion(String identificacion);

}
