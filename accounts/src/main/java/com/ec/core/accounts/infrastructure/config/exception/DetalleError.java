package com.ec.core.accounts.infrastructure.config.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetalleError {

	private LocalDateTime fechaError;
	private String mensaje;
	private String detalle;

}
