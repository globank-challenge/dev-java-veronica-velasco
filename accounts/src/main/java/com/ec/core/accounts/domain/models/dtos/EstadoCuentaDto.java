package com.ec.core.accounts.domain.models.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoCuentaDto {

	private String numeroCuenta;
	private BigDecimal saldo;
	private Integer totalCreditos;
	private Integer totalDebitos;
}
