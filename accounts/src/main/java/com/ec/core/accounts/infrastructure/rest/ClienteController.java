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

import com.ec.core.accounts.domain.models.dtos.ClienteDto;
import com.ec.core.accounts.domain.ports.services.IClienteService;
import com.ec.core.accounts.infrastructure.rest.output.FormatoMensaje;
import com.ec.core.accounts.infrastructure.rest.output.FormatoSalida;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1/admin")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	private final IClienteService iClienteService;

	public ClienteController(IClienteService clienteService) {
		this.iClienteService = clienteService;
	}

	@GetMapping("/clientes/{identificacion}")
	public ResponseEntity<FormatoSalida<ClienteDto>> obtenerClientes(
			@PathVariable("identificacion") String identificacion) {
		HttpStatus status = HttpStatus.OK;
		ClienteDto clienteDto = null;
		try {
			clienteDto = iClienteService.obtenerClientePorIdentificacion(identificacion);
			if (clienteDto == null) {
				status = HttpStatus.NOT_FOUND;
			}
		} catch (IllegalArgumentException iex) {
			log.error(iex.getMessage(), iex);
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			clienteDto = null;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		FormatoSalida<ClienteDto> output = new FormatoSalida<>();
		output.setData(clienteDto);
		output.setMessages(List.of(new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase())));
		return new ResponseEntity<>(output, status);

	}

	@PostMapping("/clientes")
	@Operation(description = "Crear cliente")
	public ResponseEntity<FormatoSalida<ClienteDto>> guardarCliente(@RequestBody ClienteDto clienteDto) {
		HttpStatus status = HttpStatus.CREATED;
		ClienteDto clienteDtoGuardado = iClienteService.guardarCliente(clienteDto);
		FormatoSalida<ClienteDto> output = new FormatoSalida<>();
		output.setData(clienteDtoGuardado);
		output.setMessages(List.of(new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase())));
		return new ResponseEntity<>(output, status);
	}
	
	@PutMapping("clientes/{identificacion}")
    @Operation(description = "Se actualiza un cliente por su id")
    public ResponseEntity<FormatoSalida<ClienteDto>> actualizarCliente(@PathVariable("identificacion") @NotNull String identificacion,
            @RequestBody ClienteDto clienteDto) {
        HttpStatus status = HttpStatus.OK;
        ClienteDto clienteActualizado = iClienteService.actualizarCliente(identificacion, clienteDto);
        FormatoMensaje message = new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase());
        FormatoSalida<ClienteDto> formatOutput = new FormatoSalida<>(clienteActualizado, List.of(message));
        return new ResponseEntity<>(formatOutput, status);
    }
	
	@DeleteMapping("/clientes/{identificacion}")
	public ResponseEntity<FormatoSalida<ClienteDto>> eliminarCliente(@PathVariable("identificacion") String identificacion) {
		HttpStatus status = HttpStatus.NO_CONTENT;
		iClienteService.eliminarCliente(identificacion);
		FormatoMensaje message = new FormatoMensaje(String.valueOf(status.value()), status.getReasonPhrase());
		FormatoSalida<ClienteDto> formatOutput = new FormatoSalida<>(null, List.of(message));
        return new ResponseEntity<>(formatOutput, status);
	}

}
