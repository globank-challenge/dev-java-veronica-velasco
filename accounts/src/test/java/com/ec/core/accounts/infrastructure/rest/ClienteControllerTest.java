package com.ec.core.accounts.infrastructure.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.ec.core.accounts.domain.models.dtos.ClienteDto;

@SpringBootTest
public class ClienteControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port+"/cuentasBanco";
	}
	@Test
	public void testGetClientePorIdentificacion() {
		ClienteDto cliente = restTemplate.getForObject(getRootUrl() + "/clientes/1234", ClienteDto.class);
		System.out.println(cliente.getNombre());
		assertNotNull(cliente);
	}

}
