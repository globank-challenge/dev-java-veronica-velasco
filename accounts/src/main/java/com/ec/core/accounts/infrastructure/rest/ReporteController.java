package com.ec.core.accounts.infrastructure.rest;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ec.core.accounts.domain.models.dtos.EstadoCuentaDto;
import com.ec.core.accounts.domain.ports.services.ICuentaService;
import com.ec.core.accounts.infrastructure.rest.output.FormatoMensaje;
import com.ec.core.accounts.infrastructure.rest.output.FormatoSalidaReporte;

@RestController
@RequestMapping("/v1")
public class ReporteController {

	private static final Logger log = LoggerFactory.getLogger(ReporteController.class);

	private final ICuentaService iCuentaService;

	public ReporteController(ICuentaService iCuentaService) {
		this.iCuentaService = iCuentaService;
	}

	@GetMapping("/estados-cuenta")
	public ResponseEntity<FormatoSalidaReporte<List<EstadoCuentaDto>>> obtenerInformacionCliente(
			@RequestParam("identificacion") String identificacion) {
		HttpStatus status = HttpStatus.OK;
		List<EstadoCuentaDto> cuentasDto = null;
		try {
			cuentasDto = iCuentaService.obtenerCuentasPorIdentificacion(identificacion);
		} catch (IllegalArgumentException iex) {
			log.error(iex.getMessage(), iex);
			status = HttpStatus.BAD_REQUEST;
		}
		FormatoSalidaReporte<List<EstadoCuentaDto>> output = new FormatoSalidaReporte<>();
		output.setData(List.of(cuentasDto));
		output.setMessages(List.of(new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase())));
		return new ResponseEntity<>(output, status);

	}

}
