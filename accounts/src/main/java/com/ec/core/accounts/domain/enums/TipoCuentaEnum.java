package com.ec.core.accounts.domain.enums;

public enum TipoCuentaEnum {

	CORRIENTE("CTE"), AHORROS("AHO");

	private String valor;

	private TipoCuentaEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static TipoCuentaEnum obtenerEnumeracion(String valor) {
		for (TipoCuentaEnum item : TipoCuentaEnum.values()) {
			if (item.valor.equals(valor)) {
				return item;
			}
		}
		return null;
	}
}
