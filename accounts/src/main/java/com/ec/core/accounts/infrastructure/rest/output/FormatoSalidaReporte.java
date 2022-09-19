package com.ec.core.accounts.infrastructure.rest.output;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormatoSalidaReporte<T> {
	
	List<T> data;
    List<FormatoMensaje> messages;
    
}
