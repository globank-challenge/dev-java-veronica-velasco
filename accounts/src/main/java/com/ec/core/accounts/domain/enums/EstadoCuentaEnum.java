package com.ec.core.accounts.domain.enums;

public enum EstadoCuentaEnum {

	A("ACTIVO"), I("INACTIVO");

	private String valor;

	private EstadoCuentaEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static EstadoCuentaEnum obtenerEnumeracion(String valor) {
		for (EstadoCuentaEnum item : EstadoCuentaEnum.values()) {
			if (item.valor.equals(valor)) {
				return item;
			}
		}
		return null;
	}
}
