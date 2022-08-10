package com.ec.core.accounts.infrastructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.core.accounts.domain.models.Cuenta;

public interface CuentaJpa extends JpaRepository<Cuenta, Long> {
	public Cuenta findByNumeroCuenta(String numeroCuenta);
}
