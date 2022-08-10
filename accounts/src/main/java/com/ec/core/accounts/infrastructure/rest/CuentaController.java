package com.ec.core.accounts.infrastructure.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.core.accounts.domain.models.dtos.CuentaDto;
import com.ec.core.accounts.domain.ports.services.ICuentaService;
import com.ec.core.accounts.infrastructure.rest.output.FormatoMensaje;
import com.ec.core.accounts.infrastructure.rest.output.FormatoSalida;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1/admin")
public class CuentaController {
	
	private final ICuentaService iCuentaService;
	
	private static final Logger log = LoggerFactory.getLogger(CuentaController.class);


	@Autowired
	public CuentaController(ICuentaService cuentaService) {
		this.iCuentaService = cuentaService;
	}

	@Operation(description = "Obtener la cuenta por el numero de cuenta")
	@GetMapping(value = "/cuenta/{numeroCuenta}")
	public ResponseEntity<FormatoSalida<CuentaDto>> obtenerCuentaPorNumero(@PathVariable("numeroCuenta") String numeroCuenta) {
		HttpStatus status = HttpStatus.OK;
		CuentaDto cuentaDto = null;
		try {
			cuentaDto = iCuentaService.obtenerCuenta(numeroCuenta);
			if (cuentaDto == null) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (IllegalArgumentException iex) {
			log.error(iex.getMessage(), iex);
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			cuentaDto = null;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		FormatoSalida<CuentaDto> output = new FormatoSalida<>();
		output.setData(cuentaDto);
		output.setMessages(List.of(new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase())));
		return new ResponseEntity<>(output, status);
	}

}
