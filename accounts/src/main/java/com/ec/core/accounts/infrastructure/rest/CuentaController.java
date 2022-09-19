package com.ec.core.accounts.infrastructure.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	public CuentaController(ICuentaService cuentaService) {
		this.iCuentaService = cuentaService;
	}

	@Operation(description = "Obtener la cuenta por el numero de cuenta")
	@GetMapping(value = "/cuentas/{numeroCuenta}")
	public ResponseEntity<FormatoSalida<CuentaDto>> obtenerCuentaPorNumero(
			@PathVariable("numeroCuenta") String numeroCuenta) {
		HttpStatus status = HttpStatus.OK;
		CuentaDto cuentaDto = null;
		try {
			cuentaDto = iCuentaService.obtenerCuenta(numeroCuenta);
		} catch (IllegalArgumentException iex) {
			log.error(iex.getMessage(), iex);
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			cuentaDto = null;
			status = HttpStatus.NOT_FOUND;
		}
		FormatoSalida<CuentaDto> output = new FormatoSalida<>();
		output.setData(cuentaDto);
		output.setMessages(List.of(new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase())));
		return new ResponseEntity<>(output, status);
	}

	@PostMapping("/cuentas")
	@Operation(description = "Crear cuenta")
	public ResponseEntity<FormatoSalida<CuentaDto>> guardarCuenta(@RequestBody CuentaDto cuentaDto) {
		HttpStatus status = HttpStatus.CREATED;
		CuentaDto cuentaDtoGuardada = iCuentaService.guardarCuenta(cuentaDto);
		FormatoSalida<CuentaDto> output = new FormatoSalida<>();
		output.setData(cuentaDtoGuardada);
		output.setMessages(List.of(new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase())));
		return new ResponseEntity<>(output, status);
	}

	@PutMapping("cuentas/{numeroCuenta}")
	@Operation(description = "Se actualiza una cuenta por el numero")
	public ResponseEntity<FormatoSalida<CuentaDto>> actualizarCliente(
			@PathVariable("numeroCuenta") @NotNull String numeroCuenta, @RequestBody CuentaDto cuentaDto) {
		HttpStatus status = HttpStatus.OK;
		CuentaDto cuentaActualizada = iCuentaService.actualizarCuenta(numeroCuenta, cuentaDto);
		FormatoMensaje message = new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase());
		FormatoSalida<CuentaDto> formatOutput = new FormatoSalida<>(cuentaActualizada, List.of(message));
		return new ResponseEntity<>(formatOutput, status);
	}
	
	@DeleteMapping("/cuentas/{numeroCuenta}")
	public ResponseEntity<FormatoSalida<CuentaDto>> eliminarCliente(@PathVariable("numeroCuenta") String numeroCuenta) {
		HttpStatus status = HttpStatus.NO_CONTENT;
		iCuentaService.eliminarCuenta(numeroCuenta);
		FormatoMensaje message = new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase());
		FormatoSalida<CuentaDto> formatOutput = new FormatoSalida<>(null, List.of(message));
        return new ResponseEntity<>(formatOutput, status);
	}

}
