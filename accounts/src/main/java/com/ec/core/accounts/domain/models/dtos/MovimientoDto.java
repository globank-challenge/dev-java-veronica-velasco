package com.ec.core.accounts.domain.models.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovimientoDto {

	private long id;
	private Date fecha;
	private String tipoMovimiento;
	private BigDecimal valor;
	private BigDecimal saldo;
	private CuentaDto cuentaDto;
	
}
