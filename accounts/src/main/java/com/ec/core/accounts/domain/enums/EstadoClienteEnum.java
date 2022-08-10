package com.ec.core.accounts.domain.enums;

public enum EstadoClienteEnum {
	
	A("ACTIVO"), I("INACTIVO");

	private String valor;

	private EstadoClienteEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static EstadoClienteEnum obtenerEnumeracion(String valor) {
		for (EstadoClienteEnum item : EstadoClienteEnum.values()) {
			if (item.valor.equals(valor)) {
				return item;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return valor;
	}

}
