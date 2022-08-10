package com.ec.core.accounts.domain.models.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CuentaDto {

	private long id;
	private String numeroCuenta;
	private String tipoCuenta;
	private BigDecimal saldoInicial;
	private String estado;
	private ClienteDto clienteDto;
	
}
