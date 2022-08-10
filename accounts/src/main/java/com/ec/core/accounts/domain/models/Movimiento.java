package com.ec.core.accounts.domain.models;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ec.core.accounts.domain.enums.TipoMovimientoEnum;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Movimiento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7610443235863116985L;
	@Id
	private long id;
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "TIPO_MOVIMIENTO")
	private TipoMovimientoEnum tipoMovimiento;
	@Column(name = "valor")
	private BigDecimal valor;
	@Column(name = "saldo")
	private BigDecimal saldo;
	@ManyToOne
	@JoinColumn(name = "CODIGO_CUENTA")
	private Cuenta cuenta;
}
