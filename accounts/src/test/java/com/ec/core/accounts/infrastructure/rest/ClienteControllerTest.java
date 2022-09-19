package com.ec.core.accounts.infrastructure.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ec.core.accounts.domain.enums.EstadoClienteEnum;
import com.ec.core.accounts.domain.models.Cliente;
import com.ec.core.accounts.domain.models.dtos.ClienteDto;
import com.ec.core.accounts.domain.ports.services.IClienteService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

	@LocalServerPort
	private int port;

	@InjectMocks
	ClienteController clienteContoller;

	@MockBean
	IClienteService iClienteService;

	HttpHeaders headers = new HttpHeaders();
	TestRestTemplate testRestTemplate = new TestRestTemplate();

	ClienteDto mockClienteDto = new ClienteDto(1L, "1", "", "", "", "", 1, "1234", "", "");
	Cliente mockCliente = new Cliente(1, "Veronica", "femenino", 10, "1234", "Alangasi", "0999999", "1", "1234",
			EstadoClienteEnum.A);

	@Test
	public void testObtenerClientePorIdentificacion() {
		Mockito.when(iClienteService.obtenerClientePorIdentificacion(Mockito.anyString())).thenReturn(mockClienteDto);
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(obtenerApi("/cuentasBanco/v1/admin/clientes/1234"),
				HttpMethod.GET, entity, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testGuardarCliente() {
		HttpEntity<ClienteDto> entity = new HttpEntity<>(mockClienteDto, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(obtenerApi("/cuentasBanco/v1/admin/clientes"),
				HttpMethod.POST, entity, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	private String obtenerApi(String uri) {
		return "http://localhost:" + port + uri;
	}

}
