package com.ec.core.accounts.domain.enums;

public enum TipoMovimientoEnum {

	RETIRO("RETIRO"),
	DEPOSITO("DEPOSITO");
	
	private String valor;
	
	private TipoMovimientoEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static TipoMovimientoEnum obtenerEnumeracion(String valor) {
		for (TipoMovimientoEnum item : TipoMovimientoEnum.values()) {
			if(item.valor.equals(valor)) {
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
