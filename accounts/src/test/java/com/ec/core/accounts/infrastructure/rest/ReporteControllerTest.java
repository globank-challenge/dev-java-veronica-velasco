package com.ec.core.accounts.infrastructure.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ec.core.accounts.domain.ports.services.ICuentaService;

@WebMvcTest(controllers = ReporteController.class)
@ExtendWith(SpringExtension.class)
public class ReporteControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ICuentaService iCuentaService;

	@Test
	public void obtenerInformacionCliente() throws Exception {
		mockMvc.perform(get("/v1/estados-cuenta?identificacion=1234")).andExpect(status().is2xxSuccessful());
	}

}
