package com.ec.core.accounts.domain.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ec.core.accounts.domain.enums.TipoCuentaEnum;
import com.ec.core.accounts.domain.enums.EstadoCuentaEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8511889340436718117L;

	@Id
	@GeneratedValue
	private long id;
	@Column(name = "NUMERO_CUENTA")
	private String numeroCuenta;
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_CUENTA")
	private TipoCuentaEnum tipoCuenta;
	@Column(name = "SALDO_INICIAL")
	private BigDecimal saldoInicial;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoCuentaEnum estado;
	@ManyToOne
	@JoinColumn(name = "CODIGO_CLIENTE")
	private Cliente cliente;

}
