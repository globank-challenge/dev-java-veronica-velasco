package com.ec.core.accounts.infrastructure.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.core.accounts.domain.models.dtos.MovimientoDto;
import com.ec.core.accounts.domain.ports.services.IMovimientoService;
import com.ec.core.accounts.infrastructure.rest.output.FormatoMensaje;
import com.ec.core.accounts.infrastructure.rest.output.FormatoSalida;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1/admin")
public class MovimientoController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

	
	private IMovimientoService iMovimientoService;
	
	public MovimientoController(IMovimientoService movimientoService) {
		this.iMovimientoService = movimientoService;
	}

	@PostMapping("/movimientos")
	@Operation(description = "Crear movimientos")
	public ResponseEntity<FormatoSalida<MovimientoDto>> guardarMovimiento(@RequestBody MovimientoDto movimientoDto) {
		HttpStatus status = HttpStatus.CREATED;
		MovimientoDto movimientoDtoGuardado = iMovimientoService.guardarMovimiento(movimientoDto);
		FormatoSalida<MovimientoDto> output = new FormatoSalida<>();
		output.setData(movimientoDtoGuardado);
		output.setMessages(List.of(new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase())));
		return new ResponseEntity<>(output, status);
	}

}
