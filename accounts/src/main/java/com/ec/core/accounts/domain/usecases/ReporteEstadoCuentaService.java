package com.ec.core.accounts.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;

import com.ec.core.accounts.domain.ports.services.ICuentaService;
import com.ec.core.accounts.domain.ports.services.IReporteEstadoCuentaService;

public class ReporteEstadoCuentaService implements IReporteEstadoCuentaService {
	
	@Autowired
	ICuentaService icuentaService;

	@Override
	public void generarReporteEstadosCuenta(String identificacion) {
		
	}

}
