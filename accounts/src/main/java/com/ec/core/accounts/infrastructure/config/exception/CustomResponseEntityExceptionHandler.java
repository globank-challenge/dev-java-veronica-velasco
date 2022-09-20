package com.ec.core.accounts.infrastructure.config.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<DetalleError> handleAllException(Exception ex, WebRequest request) throws Exception {
		DetalleError detalleError = new DetalleError(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<DetalleError>(detalleError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<DetalleError> handleClienteNotFoundException(Exception ex, WebRequest request) throws Exception {
		DetalleError detalleError = new DetalleError(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<DetalleError>(detalleError, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ErrorSaldoException.class)
	public final ResponseEntity<DetalleError> handleErrorSaldoException(Exception ex, WebRequest request) throws Exception {
		DetalleError detalleError = new DetalleError(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<DetalleError>(detalleError, HttpStatus.BAD_REQUEST);
	}

}
